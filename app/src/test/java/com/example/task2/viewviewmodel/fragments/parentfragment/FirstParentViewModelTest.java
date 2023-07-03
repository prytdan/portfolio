package com.example.task2.viewviewmodel.fragments.parentfragment;

import static org.junit.Assert.*;

import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.BenchmarksNames;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.collections.CollectionsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class FirstParentViewModelTest {
    private ParentViewModel parentViewModel;
    private Benchmark benchmark;
    private List<Benchmark> manuallyCreatedList;


    @Before
    public void setup() {
        parentViewModel = new CollectionsViewModel();
        benchmark = new Benchmark(
                BenchmarksNames.ADD_BEGIN_ARRAYLIST, 0, StateOfCalculation.TERMINATED);
        manuallyCreatedList = new ArrayList<>(List.of(benchmark));
    }

    @Test
    public void testCreateListOfFlowablesSize() {
        List<Benchmark> manuallyCreatedList = new ArrayList<>(List.of(benchmark));
        List<Flowable<Benchmark>> testedList = parentViewModel.createListOfFlowables(manuallyCreatedList);
        assertEquals(manuallyCreatedList.size(), testedList.size());
    }

    @Test
    public void testCreateDisposable() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        List<Flowable<Benchmark>> flowableList = new ArrayList<>();
        flowableList.add(Flowable.create((FlowableOnSubscribe<Benchmark>) emitter -> {
                    benchmark.setTimeOfCalculations(25);
                    emitter.onNext(benchmark);
                }
                , BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()));
        parentViewModel.createDisposable(flowableList, manuallyCreatedList);
        assertEquals(benchmark, manuallyCreatedList.get(0));
    }
}