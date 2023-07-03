package com.example.task2.viewviewmodel.fragments.parentfragment.collections;

import static org.junit.Assert.*;

import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.BenchmarksNames;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.collections.CollectionsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsViewModelTest {
    private CollectionsViewModel collectionsViewModel;
    private List<Benchmark> list;
    private final int AMOUNT_OF_ITEMS = 100_000;

    @Before
    public void setup() {
        collectionsViewModel = new CollectionsViewModel();
    }

    @Test
    public void setListOfBenchmarksToDataSetSizeTest() {
        list = new ArrayList<>();
        collectionsViewModel.setListOfBenchmarksToDataSet(list);
        assertEquals(21, list.size());
    }

    @Test
    public void setListOfBenchmarksToDataSetElement0Test() {
        list = new ArrayList<>();
        collectionsViewModel.setListOfBenchmarksToDataSet(list);
        Benchmark benchmark = new Benchmark
                (BenchmarksNames.ADD_BEGIN_ARRAYLIST, 0, StateOfCalculation.TERMINATED);
        assertEquals(benchmark, list.get(0));
    }

    @Test
    public void setListOfBenchmarksToDataSetElement10Test() {
        list = new ArrayList<>();
        collectionsViewModel.setListOfBenchmarksToDataSet(list);
        Benchmark benchmark = new Benchmark
                (BenchmarksNames.SEARCH_VALUE_LINKEDLIST, 0, StateOfCalculation.TERMINATED);
        assertEquals(benchmark, list.get(10));
    }

    @Test
    public void setListOfBenchmarksToDataSetElement20Test() {
        list = new ArrayList<>();
        collectionsViewModel.setListOfBenchmarksToDataSet(list);
        Benchmark benchmark = new Benchmark
                (BenchmarksNames.REMOVE_END_COPYONWRITEARRAYLIST, 0, StateOfCalculation.TERMINATED);
        assertEquals(benchmark, list.get(20));
    }

    @Test
    public void createListWithElementsSizeTest() {
        List<Byte> testedList = collectionsViewModel.createListWithElements(AMOUNT_OF_ITEMS, 0);
        List<Byte> expectedList = new ArrayList<>(Collections.nCopies(AMOUNT_OF_ITEMS, (byte) 1));
        assertEquals(expectedList.size(), testedList.size());
    }

    @Test
    public void createListWithElementsCollectionTypeTest1() {
        List<Byte> testedList = collectionsViewModel.createListWithElements(AMOUNT_OF_ITEMS, 0);
        List<Byte> expectedList = new ArrayList<>();
        assertEquals(expectedList instanceof ArrayList, testedList instanceof ArrayList);
    }

    @Test
    public void createListWithElementsCollectionTypeTest2() {
        List<Byte> testedList = collectionsViewModel.createListWithElements(AMOUNT_OF_ITEMS, 1);
        List<Byte> expectedList = new LinkedList<>();
        assertEquals(expectedList instanceof LinkedList, testedList instanceof LinkedList);
    }

    @Test
    public void createListWithElementsCollectionTypeTest3() {
        List<Byte> testedList = collectionsViewModel.createListWithElements(AMOUNT_OF_ITEMS, 2);
        List<Byte> expectedList = new CopyOnWriteArrayList<>();
        assertEquals(expectedList instanceof CopyOnWriteArrayList, testedList instanceof CopyOnWriteArrayList);
    }



}