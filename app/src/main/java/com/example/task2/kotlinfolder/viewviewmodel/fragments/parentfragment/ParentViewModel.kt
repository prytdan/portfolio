package com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task2.kotlinfolder.model.Benchmark
import com.example.task2.kotlinfolder.model.BenchmarksNames
import com.example.task2.kotlinfolder.model.StateOfCalculation
import com.example.task2.kotlinfolder.readOnly
import com.example.task2.kotlinfolder.repo.Repository
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ParentViewModel(repository: Repository) : BaseViewModel(repository) {

    private val mutableLiveDataForListOfBenchmarks = MutableLiveData<List<Benchmark>>()

    val liveDataForListOfBenchmarks = mutableLiveDataForListOfBenchmarks.readOnly()

    private val mutableLiveDataForStateOfCalculation = MutableLiveData<StateOfCalculation>()

    val liveDataForStateOfCalculation = mutableLiveDataForStateOfCalculation.readOnly()

    private var calculationJob: Job? = null

    protected var listOfBenchmarks: MutableList<Benchmark> = mutableListOf()

    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

    abstract fun fillBenchmarksListWithStartValues()

    abstract fun getCalculationTimeForBenchmark(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long

    override fun onFirstAttach() {
        super.onFirstAttach()
        setBenchmarks()
    }

    private fun setBenchmarks() {
        fillBenchmarksListWithStartValues()
        mutableLiveDataForListOfBenchmarks.value = listOfBenchmarks
    }

    fun startCalculations() {
        mutableLiveDataForStateOfCalculation.value = StateOfCalculation.CALCULATING
        launchCoroutinesFromFlows()
    }

    fun stopCalculations() {
        mutableLiveDataForStateOfCalculation.value = StateOfCalculation.TERMINATED
        calculationJob?.cancelChildren()
        stopAnimationOfProgressBars()
    }


    private fun createBenchmarkBeforeCalculation(benchmarksId: Int): Benchmark {
        val currentBenchmarkName = listOfBenchmarks[benchmarksId].name
        return Benchmark(
            currentBenchmarkName,
            null,
            StateOfCalculation.CALCULATING
        )
    }

    private fun createBenchmarkAfterCalculation(benchmarksId: Int): Benchmark {
        val currentBenchmarkName = listOfBenchmarks[benchmarksId].name
        val result = getCalculationTimeForBenchmark(repository.usersInput, currentBenchmarkName)
        return Benchmark(
            currentBenchmarkName,
            result,
            StateOfCalculation.TERMINATED,
            result.toString()
        )
    }


    private fun launchCoroutinesFromFlows() {
        val jobs = mutableListOf<Job>()
        viewModelScope.launch(dispatcherIO) {
            for (i in 0 until listOfBenchmarks.size) {
                val job = launch {
                    listOfBenchmarks[i] = createBenchmarkBeforeCalculation(i)
                    withContext(dispatcherMain) {
                        mutableLiveDataForListOfBenchmarks.value = listOfBenchmarks
                    }
                    listOfBenchmarks[i] = createBenchmarkAfterCalculation(i)
                    withContext(dispatcherMain) {
                        mutableLiveDataForListOfBenchmarks.value = listOfBenchmarks
                    }
                }
                jobs.add(job)
            }
            jobs.joinAll()

            withContext(dispatcherMain) {
                mutableLiveDataForStateOfCalculation.value = StateOfCalculation.TERMINATED
            }
        }
    }


    private fun stopAnimationOfProgressBars() {
        listOfBenchmarks.replaceAll { benchmark ->
            Benchmark(
                benchmark.name,
                benchmark.timeOfCalculation,
                StateOfCalculation.TERMINATED,
                benchmark.timeOfCalculationToString
            )
        }
        mutableLiveDataForListOfBenchmarks.value = listOfBenchmarks
    }
}
