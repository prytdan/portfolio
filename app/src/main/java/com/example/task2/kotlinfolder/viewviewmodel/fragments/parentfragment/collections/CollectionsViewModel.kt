package com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.collections

import com.example.task2.kotlinfolder.model.Benchmark
import com.example.task2.kotlinfolder.model.BenchmarksNames
import com.example.task2.kotlinfolder.model.StateOfCalculation
import com.example.task2.kotlinfolder.repo.Repository
import com.example.task2.kotlinfolder.repo.RepositoryImpl
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.ParentViewModel
import java.util.Collections
import java.util.LinkedList
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.system.measureTimeMillis

class CollectionsViewModel(repository: Repository) : ParentViewModel(repository) {
    companion object {
        private const val LIST_ELEMENT: Byte = 1
    }

    override fun fillBenchmarksListWithStartValues() {
        if (listOfBenchmarks.isEmpty()) {
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_BEGIN_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_BEGIN_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_BEGIN_COPYONWRITEARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_MIDDLE_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_MIDDLE_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_MIDDLE_COPYONWRITEARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_END_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_END_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_END_COPYONWRITEARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.SEARCH_VALUE_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.SEARCH_VALUE_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.SEARCH_VALUE_COPYONWRITEARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_BEGIN_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_BEGIN_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_BEGIN_COPYONWRITEARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_MIDDLE_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_MIDDLE_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_MIDDLE_COPYONWRITEARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_END_ARRAYLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_END_LINKEDLIST))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_END_COPYONWRITEARRAYLIST))
        }
    }

    override fun getCalculationTimeForBenchmark(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long {
        val time: Long = when (benchmarkName) {
            BenchmarksNames.ADD_BEGIN_ARRAYLIST,
            BenchmarksNames.ADD_BEGIN_LINKEDLIST,
            BenchmarksNames.ADD_BEGIN_COPYONWRITEARRAYLIST -> addElementsToPositionInList(
                amountOfItems,
                benchmarkName,
                0
            )

            BenchmarksNames.ADD_MIDDLE_ARRAYLIST,
            BenchmarksNames.ADD_MIDDLE_LINKEDLIST,
            BenchmarksNames.ADD_MIDDLE_COPYONWRITEARRAYLIST -> addElementsToPositionInList(
                amountOfItems,
                benchmarkName,
                amountOfItems / 2
            )

            BenchmarksNames.ADD_END_ARRAYLIST,
            BenchmarksNames.ADD_END_LINKEDLIST,
            BenchmarksNames.ADD_END_COPYONWRITEARRAYLIST -> addElementsToPositionInList(
                amountOfItems,
                benchmarkName,
                amountOfItems - 1
            )

            BenchmarksNames.SEARCH_VALUE_ARRAYLIST,
            BenchmarksNames.SEARCH_VALUE_LINKEDLIST,
            BenchmarksNames.SEARCH_VALUE_COPYONWRITEARRAYLIST -> searchByValueInList(
                amountOfItems,
                benchmarkName
            )

            BenchmarksNames.REMOVE_BEGIN_ARRAYLIST,
            BenchmarksNames.REMOVE_BEGIN_LINKEDLIST,
            BenchmarksNames.REMOVE_BEGIN_COPYONWRITEARRAYLIST -> removeElementsFromPositionInList(
                amountOfItems,
                benchmarkName,
                0
            )

            BenchmarksNames.REMOVE_MIDDLE_ARRAYLIST,
            BenchmarksNames.REMOVE_MIDDLE_LINKEDLIST,
            BenchmarksNames.REMOVE_MIDDLE_COPYONWRITEARRAYLIST -> removeElementsFromPositionInList(
                amountOfItems,
                benchmarkName,
                amountOfItems / 2
            )

            BenchmarksNames.REMOVE_END_ARRAYLIST,
            BenchmarksNames.REMOVE_END_LINKEDLIST,
            BenchmarksNames.REMOVE_END_COPYONWRITEARRAYLIST -> removeElementsFromPositionInList(
                amountOfItems,
                benchmarkName,
                amountOfItems - 1
            )

            else -> throw IllegalArgumentException("Invalid Benchmarks name")
        }
        return time
    }

    private fun createListWithElements(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): MutableList<Byte> {

        val list: MutableList<Byte> = when (benchmarkName) {
            BenchmarksNames.ADD_BEGIN_ARRAYLIST,
            BenchmarksNames.ADD_MIDDLE_ARRAYLIST,
            BenchmarksNames.ADD_END_ARRAYLIST,
            BenchmarksNames.SEARCH_VALUE_ARRAYLIST,
            BenchmarksNames.REMOVE_BEGIN_ARRAYLIST,
            BenchmarksNames.REMOVE_MIDDLE_ARRAYLIST,
            BenchmarksNames.REMOVE_END_ARRAYLIST -> ArrayList(
                Collections.nCopies(
                    amountOfItems,
                    LIST_ELEMENT
                )
            )

            BenchmarksNames.ADD_BEGIN_LINKEDLIST,
            BenchmarksNames.ADD_MIDDLE_LINKEDLIST,
            BenchmarksNames.ADD_END_LINKEDLIST,
            BenchmarksNames.SEARCH_VALUE_LINKEDLIST,
            BenchmarksNames.REMOVE_BEGIN_LINKEDLIST,
            BenchmarksNames.REMOVE_MIDDLE_LINKEDLIST,
            BenchmarksNames.REMOVE_END_LINKEDLIST -> LinkedList(
                Collections.nCopies(
                    amountOfItems,
                    LIST_ELEMENT
                )
            )

            BenchmarksNames.ADD_BEGIN_COPYONWRITEARRAYLIST,
            BenchmarksNames.ADD_MIDDLE_COPYONWRITEARRAYLIST,
            BenchmarksNames.ADD_END_COPYONWRITEARRAYLIST,
            BenchmarksNames.SEARCH_VALUE_COPYONWRITEARRAYLIST,
            BenchmarksNames.REMOVE_BEGIN_COPYONWRITEARRAYLIST,
            BenchmarksNames.REMOVE_MIDDLE_COPYONWRITEARRAYLIST,
            BenchmarksNames.REMOVE_END_COPYONWRITEARRAYLIST -> CopyOnWriteArrayList(
                Collections.nCopies(
                    amountOfItems,
                    LIST_ELEMENT
                )
            )

            else -> throw IllegalArgumentException("Invalid Benchmarks name")
        }
        return list
    }

    private fun addElementsToPositionInList(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames, position: Int
    ): Long {
        val list: MutableList<Byte> = createListWithElements(amountOfItems, benchmarkName)
        val timeOfCalculation = measureTimeMillis { list.add(position, LIST_ELEMENT) }
        list.removeAt(list.size - 1)
        return timeOfCalculation
    }

    private fun searchByValueInList(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long {
        val list: MutableList<Byte> = createListWithElements(amountOfItems, benchmarkName)
        val randomPosition = (Math.random() * list.size).toInt()
        val searchedItem: Byte = 2
        list.add(randomPosition, searchedItem)
        val timeOfCalculation = measureTimeMillis { list.contains(searchedItem) }
        list.removeAt(randomPosition)
        return timeOfCalculation
    }

    private fun removeElementsFromPositionInList(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames, position: Int
    ): Long {
        val list: MutableList<Byte> = createListWithElements(amountOfItems, benchmarkName)
        val timeOfCalculation = measureTimeMillis { list.removeAt(position) }
        list.add(LIST_ELEMENT)
        return timeOfCalculation
    }

}