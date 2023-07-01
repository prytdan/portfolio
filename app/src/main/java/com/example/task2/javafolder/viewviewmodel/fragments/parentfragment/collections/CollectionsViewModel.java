package com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.collections;

import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.BenchmarksNames;
import com.example.task2.javafolder.model.StartingTimeOfCalculationForBenchmarks;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CollectionsViewModel extends ParentViewModel {
    private final byte LIST_ELEMENT = 1;
    private final ArrayList<Benchmark> list = new ArrayList<>();

    @Inject
    public CollectionsViewModel() {

    }

    @Override
    public List<Benchmark> getListOfBenchmarks() {
        long startingTime = StartingTimeOfCalculationForBenchmarks.STARTING_TIME.time;
        if (list.isEmpty()) {
            list.add(new Benchmark(BenchmarksNames.ADD_BEGIN_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_BEGIN_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_BEGIN_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_MIDDLE_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_MIDDLE_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_MIDDLE_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_END_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_END_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.ADD_END_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.SEARCH_VALUE_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.SEARCH_VALUE_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.SEARCH_VALUE_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_BEGIN_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_BEGIN_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_BEGIN_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_MIDDLE_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_MIDDLE_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_MIDDLE_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_END_ARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_END_LINKEDLIST, startingTime, StateOfCalculation.TERMINATED));
            list.add(new Benchmark(BenchmarksNames.REMOVE_END_COPYONWRITEARRAYLIST, startingTime, StateOfCalculation.TERMINATED));
        }
        return list;
    }

    @Override
    public long measureTimeOfCalculations(int amountOfItems, BenchmarksNames benchmarksName) {
        List<Byte> list = createListWithElements(amountOfItems, benchmarksName);
        final long time;
        switch (benchmarksName) {
            case ADD_BEGIN_ARRAYLIST:
            case ADD_BEGIN_LINKEDLIST:
            case ADD_BEGIN_COPYONWRITEARRAYLIST:
                time = addElementsToPositionInList(list, 0);
                break;
            case ADD_MIDDLE_ARRAYLIST:
            case ADD_MIDDLE_LINKEDLIST:
            case ADD_MIDDLE_COPYONWRITEARRAYLIST:
                time = addElementsToPositionInList(list, list.size() / 2);
                break;
            case ADD_END_ARRAYLIST:
            case ADD_END_LINKEDLIST:
            case ADD_END_COPYONWRITEARRAYLIST:
                time = addElementsToPositionInList(list, list.size() - 1);
                break;
            case SEARCH_VALUE_ARRAYLIST:
            case SEARCH_VALUE_LINKEDLIST:
            case SEARCH_VALUE_COPYONWRITEARRAYLIST:
                time = searchByValueInList(list);
                break;
            case REMOVE_BEGIN_ARRAYLIST:
            case REMOVE_BEGIN_LINKEDLIST:
            case REMOVE_BEGIN_COPYONWRITEARRAYLIST:
                time = removeElementsFromPositionInList(list, 0);
                break;
            case REMOVE_MIDDLE_ARRAYLIST:
            case REMOVE_MIDDLE_LINKEDLIST:
            case REMOVE_MIDDLE_COPYONWRITEARRAYLIST:
                time = removeElementsFromPositionInList(list, list.size() / 2);
                break;
            case REMOVE_END_ARRAYLIST:
            case REMOVE_END_LINKEDLIST:
            case REMOVE_END_COPYONWRITEARRAYLIST:
                time = removeElementsFromPositionInList(list, list.size() - 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid Benchmarks name");
        }
        return time;
    }

    private List<Byte> createListWithElements(int amountOfItems, BenchmarksNames benchmarksName) {
        List<Byte> list;
        switch (benchmarksName) {
            case ADD_BEGIN_ARRAYLIST:
            case ADD_MIDDLE_ARRAYLIST:
            case ADD_END_ARRAYLIST:
            case SEARCH_VALUE_ARRAYLIST:
            case REMOVE_BEGIN_ARRAYLIST:
            case REMOVE_MIDDLE_ARRAYLIST:
            case REMOVE_END_ARRAYLIST:
                list = new ArrayList<>(Collections.nCopies(amountOfItems, LIST_ELEMENT));
                break;
            case ADD_BEGIN_LINKEDLIST:
            case ADD_MIDDLE_LINKEDLIST:
            case ADD_END_LINKEDLIST:
            case SEARCH_VALUE_LINKEDLIST:
            case REMOVE_BEGIN_LINKEDLIST:
            case REMOVE_MIDDLE_LINKEDLIST:
            case REMOVE_END_LINKEDLIST:
                list = new LinkedList<>(Collections.nCopies(amountOfItems, LIST_ELEMENT));
                break;
            case ADD_BEGIN_COPYONWRITEARRAYLIST:
            case ADD_MIDDLE_COPYONWRITEARRAYLIST:
            case ADD_END_COPYONWRITEARRAYLIST:
            case SEARCH_VALUE_COPYONWRITEARRAYLIST:
            case REMOVE_BEGIN_COPYONWRITEARRAYLIST:
            case REMOVE_MIDDLE_COPYONWRITEARRAYLIST:
            case REMOVE_END_COPYONWRITEARRAYLIST:
                list = new CopyOnWriteArrayList<>(Collections.nCopies(amountOfItems, LIST_ELEMENT));
                break;
            default:
                throw new IllegalArgumentException("Invalid Benchmarks name");
        }
        return list;
    }

    private long addElementsToPositionInList(List<Byte> list, int position) {
        long start = System.currentTimeMillis();
        list.add(position, LIST_ELEMENT);
        long finish = System.currentTimeMillis();
        list.remove(list.size() - 1);
        return finish - start;
    }

    private long searchByValueInList(List<Byte> list) {
        int randomPosition = (int) Math.round(Math.random() * list.size());
        byte searchedItem = 2;
        list.add(randomPosition, searchedItem);
        long start = System.currentTimeMillis();
        list.contains(searchedItem);
        long finish = System.currentTimeMillis();
        list.remove(randomPosition);
        return finish - start;
    }

    private long removeElementsFromPositionInList(List<Byte> list, int position) {
        long start = System.currentTimeMillis();
        list.remove(position);
        long finish = System.currentTimeMillis();
        list.add(LIST_ELEMENT);
        return finish - start;
    }

}
