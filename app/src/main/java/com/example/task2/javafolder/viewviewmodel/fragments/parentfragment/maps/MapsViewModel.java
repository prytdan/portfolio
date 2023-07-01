package com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.maps;

import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.BenchmarksNames;
import com.example.task2.javafolder.model.StartingTimeOfCalculationForBenchmarks;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MapsViewModel extends ParentViewModel {
    private final byte MAP_ELEMENT = 1;
    private final ArrayList<Benchmark> list = new ArrayList<>();
    private final Random random = new Random();
    private final String randomString = generateRandomString(10);

    @Inject
    public MapsViewModel() {
    }

    @Override
    public List<Benchmark> getListOfBenchmarks() {
        long startingTime = StartingTimeOfCalculationForBenchmarks.STARTING_TIME.time;
        if (list.isEmpty()) {
            list.add(new Benchmark(BenchmarksNames.ADD_NEW_TREEMAP, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_NEW_HASHMAP, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.SEARCH_KEY_TREEMAP, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.SEARCH_KEY_HASHMAP, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_TREEMAP, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_HASHMAP, startingTime, StateOfCalculation.TERMINATED));
        }
        return list;
    }

    @Override
    public long measureTimeOfCalculations(int amountOfItems, BenchmarksNames benchmarksName) {
        Map<String, Byte> map = createMapWithElements(amountOfItems, benchmarksName);
        final long time;
        switch (benchmarksName) {
            case ADD_NEW_TREEMAP:
            case ADD_NEW_HASHMAP:
                time = addElementsToTheMap(map);
                break;
            case SEARCH_KEY_TREEMAP:
            case SEARCH_KEY_HASHMAP:
                time = searchByKeyInMap(map);
                break;
            case REMOVE_TREEMAP:
            case REMOVE_HASHMAP:
                time = removeElementsFromMap(map);
                break;
            default:
                throw new IllegalArgumentException("Invalid Benchmarks name");
        }
        return time;
    }

    private Map<String, Byte> createMapWithElements(int amountOfItems, BenchmarksNames benchmarksName) {
        Map<String, Byte> map;
        switch (benchmarksName) {
            case ADD_NEW_HASHMAP:
            case SEARCH_KEY_HASHMAP:
            case REMOVE_HASHMAP:
                map = new HashMap<>();
                break;
            case ADD_NEW_TREEMAP:
            case SEARCH_KEY_TREEMAP:
            case REMOVE_TREEMAP:
                map = new TreeMap<>();
                break;
            default:
                throw new IllegalArgumentException("Invalid Benchmarks name");
        }
        fillMapWithElements(map, amountOfItems);
        return map;
    }

    private void fillMapWithElements(Map<String, Byte> map, int amount) {
        String key;
        for (int i = 0; i < amount; i++) {
            key = generateRandomString(8);
            if (!map.containsKey(key)) {
                map.put(key, MAP_ELEMENT);

            }
        }
    }

    private String generateRandomString(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private long addElementsToTheMap(Map<String, Byte> map) {
        long start = System.currentTimeMillis();
        map.put(randomString, MAP_ELEMENT);
        long finish = System.currentTimeMillis();
        map.remove(randomString);
        return finish - start;
    }

    private long searchByKeyInMap(Map<String, Byte> map) {
        map.put(randomString, MAP_ELEMENT);
        long start = System.currentTimeMillis();
        boolean stringIsInMap = map.containsKey(randomString);
        long finish = System.currentTimeMillis();
        map.remove(randomString);
        return finish - start;
    }

    private long removeElementsFromMap(Map<String, Byte> map) {
        map.put(randomString, MAP_ELEMENT);
        long start = System.currentTimeMillis();
        map.remove(randomString);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
