package com.example.task2.javafolder.viewviewmodel.fragments.parentfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.BenchmarksNames;
import com.example.task2.javafolder.model.StartingTimeOfCalculationForBenchmarks;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class ParentViewModel extends BaseViewModel {

    private final MutableLiveData<List<Benchmark>> mutableLiveDataForListOfBenchmarks = new MutableLiveData<>();

    private final MutableLiveData<StateOfCalculation> mutableLiveDataForStateOfCalculation = new MutableLiveData<>();

    protected LiveData<List<Benchmark>> liveDataForListOfBenchmarks = mutableLiveDataForListOfBenchmarks;

    protected LiveData<StateOfCalculation> liveDataForStateOfCalculation = mutableLiveDataForStateOfCalculation;

    private Disposable disposable;

    public abstract List<Benchmark> getListOfBenchmarks();

    public abstract long measureTimeOfCalculations(int amountOfItems, BenchmarksNames benchmarksName);

    public void setBenchmarks() {
        mutableLiveDataForListOfBenchmarks.setValue(getListOfBenchmarks());
    }

    public void startCalculations() {
        List<Flowable<Benchmark>> listOfFlowables = createListOfFlowables();
        createDisposableFromFlowables(listOfFlowables);
    }

    public void stopCalculations() {
        stopAnimationOfProgressBars();
        disposeDisposable();
    }

    private List<Flowable<Benchmark>> createListOfFlowables() {
        List<Flowable<Benchmark>> calculationsFlowable = new ArrayList<>();
        for (int i = 0; i < getListOfBenchmarks().size(); i++) {
            final int benchmarksId = i;
            calculationsFlowable.add(Flowable.create((FlowableOnSubscribe<Benchmark>) emitter -> {
                        Benchmark benchmarkBeforeCalculation = createBenchmarkBeforeCalculation(benchmarksId);
                        emitter.onNext(benchmarkBeforeCalculation);
                        Benchmark benchmarkAfterCalculation = createBenchmarkAfterCalculation(benchmarksId);
                        emitter.onNext(benchmarkAfterCalculation);
                        emitter.onComplete();
                    }
                    , BackpressureStrategy.LATEST).subscribeOn(Schedulers.newThread()));
        }
        return calculationsFlowable;
    }

    private Benchmark createBenchmarkBeforeCalculation(int benchmarksId) {
        BenchmarksNames currentBenchmarkName = getListOfBenchmarks().get(benchmarksId).getName();
        return new Benchmark(currentBenchmarkName,
                StartingTimeOfCalculationForBenchmarks.STARTING_TIME.time, StateOfCalculation.CALCULATING);
    }

    private Benchmark createBenchmarkAfterCalculation(int benchmarksId) {
        BenchmarksNames currentBenchmarkName = getListOfBenchmarks().get(benchmarksId).getName();
        long result = measureTimeOfCalculations(repository.getUsersInput(), currentBenchmarkName);
        Benchmark benchmarkAfterCalculation = new Benchmark(currentBenchmarkName, result, StateOfCalculation.TERMINATED);
        benchmarkAfterCalculation.setRepresentationOfCalculationsTime(Long.toString(result));
        return benchmarkAfterCalculation;
    }

    private void createDisposableFromFlowables(List<Flowable<Benchmark>> listOfFlowables) {
        disposeDisposable();
        mutableLiveDataForStateOfCalculation.setValue(StateOfCalculation.CALCULATING);
        if (disposable == null || disposable.isDisposed()) {
            disposable = Flowable.merge(listOfFlowables)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSubscriber<Benchmark>() {
                        @Override
                        public void onNext(Benchmark benchmark) {
                            getListOfBenchmarks().set(benchmark.getName().benchmarksId, benchmark);
                            mutableLiveDataForListOfBenchmarks.setValue(getListOfBenchmarks());
                        }

                        @Override
                        public void onError(Throwable t) {
                            disposeDisposable();
                        }

                        @Override
                        public void onComplete() {
                            disposeDisposable();
                        }
                    });
        }
    }

    private void disposeDisposable() {
        mutableLiveDataForStateOfCalculation.setValue(StateOfCalculation.TERMINATED);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void stopAnimationOfProgressBars() {
        getListOfBenchmarks().replaceAll(benchmark -> new Benchmark(benchmark.getName(),
                benchmark.getTimeOfCalculations(), StateOfCalculation.TERMINATED));
        mutableLiveDataForListOfBenchmarks.setValue(getListOfBenchmarks());
    }
}

