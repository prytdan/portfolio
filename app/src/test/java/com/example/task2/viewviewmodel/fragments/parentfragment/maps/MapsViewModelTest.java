package com.example.task2.viewviewmodel.fragments.parentfragment.maps;

import static org.junit.Assert.*;

import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.BenchmarksNames;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.maps.MapsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(MockitoJUnitRunner.class)
public class MapsViewModelTest {
    private MapsViewModel mapsViewModel;
    private List<Benchmark> list;
    private final int AMOUNT_OF_ITEMS = 100_000;

    @Before
    public void setup() {
        mapsViewModel = new MapsViewModel();
    }

    @Test
    public void setListOfBenchmarksToDataSetSizeTest() {
        list = new ArrayList<>();
        mapsViewModel.setListOfBenchmarksToDataSet(list);
        assertEquals(6, list.size());
    }

    @Test
    public void setListOfBenchmarksToDataSetElement0Test() {
        list = new ArrayList<>();
        mapsViewModel.setListOfBenchmarksToDataSet(list);
        Benchmark benchmark = new Benchmark
                (BenchmarksNames.ADD_NEW_TREEMAP, 0, StateOfCalculation.TERMINATED);
        assertEquals(benchmark, list.get(0));
    }

    @Test
    public void setListOfBenchmarksToDataSetElement3Test() {
        list = new ArrayList<>();
        mapsViewModel.setListOfBenchmarksToDataSet(list);
        Benchmark benchmark = new Benchmark
                (BenchmarksNames.SEARCH_KEY_HASHMAP, 0, StateOfCalculation.TERMINATED);
        assertEquals(benchmark, list.get(3));
    }

    @Test
    public void setListOfBenchmarksToDataSetElement5Test() {
        list = new ArrayList<>();
        mapsViewModel.setListOfBenchmarksToDataSet(list);
        Benchmark benchmark = new Benchmark
                (BenchmarksNames.REMOVE_HASHMAP, 0, StateOfCalculation.TERMINATED);
        assertEquals(benchmark, list.get(5));
    }

    @Test
    public void createMapSizeTest() {
        Map<Integer, Byte> testedMap = mapsViewModel.createMap(AMOUNT_OF_ITEMS);
        Map<Integer, Byte> expectedMap = IntStream.rangeClosed(0, AMOUNT_OF_ITEMS).boxed()
                .collect(Collectors.toMap(Function.identity(), i -> (byte) 1));
        assertEquals(expectedMap.size(), testedMap.size());
    }

    @Test
    public void createMapWithElementsTest1() {
        Map<Integer, Byte> testedMap = mapsViewModel.createMapWithElements(AMOUNT_OF_ITEMS, 0);
        Map<Integer, Byte> expectedMap = new HashMap<>();
        assertEquals(expectedMap instanceof HashMap, testedMap instanceof HashMap);
    }

    @Test
    public void createMapWithElementsTest2() {
        Map<Integer, Byte> testedMap = mapsViewModel.createMapWithElements(AMOUNT_OF_ITEMS, 1);
        Map<Integer, Byte> expectedMap = new TreeMap<>();
        assertEquals(expectedMap instanceof TreeMap, testedMap instanceof TreeMap);
    }
}