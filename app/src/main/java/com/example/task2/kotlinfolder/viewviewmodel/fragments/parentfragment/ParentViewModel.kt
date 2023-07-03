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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ParentViewModel(repository: Repository) : BaseViewModel(repository) {

    private val mutableLiveDataForListOfBenchmarks = MutableLiveData<List<Benchmark>>()

    val liveDataForListOfBenchmarks = mutableLiveDataForListOfBenchmarks.readOnly()

    private val mutableLiveDataForStateOfCalculation = MutableLiveData<StateOfCalculation>()

    val liveDataForStateOfCalculation = mutableLiveDataForStateOfCalculation.readOnly()

    private var calculationJob: Job? = null

    private lateinit var listOfFlows: List<Flow<Benchmark>>

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
        listOfFlows = createListOfFlows()
        launchCoroutinesFromFlows()
    }

    fun stopCalculations() {
        mutableLiveDataForStateOfCalculation.value = StateOfCalculation.TERMINATED
        calculationJob?.cancelChildren()
        stopAnimationOfProgressBars()
    }

    private fun createListOfFlows(): List<Flow<Benchmark>> {
        val listOfFlows = ArrayList<Flow<Benchmark>>()
        for (benchmarksId in 0 until listOfBenchmarks.size) {
            listOfFlows.add(
                flow {
                    val benchmarkBeforeCalculation = createBenchmarkBeforeCalculation(benchmarksId)
                    emit(benchmarkBeforeCalculation)
                    val benchmarkAfterCalculation = createBenchmarkAfterCalculation(benchmarksId)
                    emit(benchmarkAfterCalculation)
                }
            )
        }
        return listOfFlows
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
        val benchmarkFlow = MutableSharedFlow<Benchmark>()
        calculationJob = viewModelScope.launch(dispatcherIO) {
            val deferredList = listOfFlows.map { flow ->
                async {
                    flow.collect { benchmark ->
                        withContext(dispatcherMain) {
                            listOfBenchmarks[benchmark.name.benchmarksId] = benchmark
                            mutableLiveDataForListOfBenchmarks.value = listOfBenchmarks
                        }
                        benchmarkFlow.emit(benchmark)
                    }
                }
            }
            deferredList.awaitAll()
            withContext(dispatcherMain) {
                mutableLiveDataForStateOfCalculation.value = StateOfCalculation.TERMINATED
            }
        }
        viewModelScope.launch(dispatcherMain) {
            benchmarkFlow.collect { benchmark ->
                listOfBenchmarks[benchmark.name.benchmarksId] = benchmark
                mutableLiveDataForListOfBenchmarks.value = listOfBenchmarks
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
