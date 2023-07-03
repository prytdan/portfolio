package com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.maps

import com.example.task2.kotlinfolder.model.Benchmark
import com.example.task2.kotlinfolder.model.BenchmarksNames
import com.example.task2.kotlinfolder.repo.Repository
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.ParentViewModel
import java.util.Random
import java.util.TreeMap
import kotlin.system.measureTimeMillis

class MapsViewModel(repository: Repository, private val random: Random) :
    ParentViewModel(repository) {
    companion object {
        private const val MAP_ELEMENT: Byte = 1
    }

    override fun fillBenchmarksListWithStartValues() {
        if (listOfBenchmarks.isEmpty()) {
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_NEW_TREEMAP))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.ADD_NEW_HASHMAP))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.SEARCH_KEY_TREEMAP))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.SEARCH_KEY_HASHMAP))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_TREEMAP))
            listOfBenchmarks.add(Benchmark(BenchmarksNames.REMOVE_HASHMAP))
        }
    }

    override fun getCalculationTimeForBenchmark(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long {
        val time: Long = when (benchmarkName) {
            BenchmarksNames.ADD_NEW_TREEMAP, BenchmarksNames.ADD_NEW_HASHMAP ->
                addElementsToTheMap(amountOfItems, benchmarkName)

            BenchmarksNames.SEARCH_KEY_TREEMAP, BenchmarksNames.SEARCH_KEY_HASHMAP ->
                searchByKeyInMap(amountOfItems, benchmarkName)

            BenchmarksNames.REMOVE_TREEMAP, BenchmarksNames.REMOVE_HASHMAP ->
                removeElementsFromMap(amountOfItems, benchmarkName)

            else ->
                throw IllegalArgumentException("Invalid Benchmarks name")
        }
        return time
    }

    private fun createMapWithElements(
        amountOfItems: Int,
        benchmarksName: BenchmarksNames
    ): MutableMap<String, Byte> {

        val map: MutableMap<String, Byte> = when (benchmarksName) {
            BenchmarksNames.ADD_NEW_HASHMAP,
            BenchmarksNames.SEARCH_KEY_HASHMAP,
            BenchmarksNames.REMOVE_HASHMAP ->
                HashMap()

            BenchmarksNames.ADD_NEW_TREEMAP,
            BenchmarksNames.SEARCH_KEY_TREEMAP,
            BenchmarksNames.REMOVE_TREEMAP ->
                TreeMap()

            else -> throw IllegalArgumentException("Invalid Benchmarks name")
        }
        fillMapWithElements(map, amountOfItems)
        return map
    }

    private fun fillMapWithElements(map: MutableMap<String, Byte>, amount: Int) {
        for (i in 0 until amount) {
            val key = generateRandomString(8)
            if (!map.containsKey(key)) {
                map[key] = MAP_ELEMENT
            }
        }
    }

    private fun generateRandomString(length: Int): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val sb = StringBuilder(length)

        for (i in 0 until length) {
            val randomIndex = random.nextInt(characters.length)
            val randomChar = characters[randomIndex]
            sb.append(randomChar)
        }
        return sb.toString()
    }

    private fun addElementsToTheMap(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long {
        val map = createMapWithElements(amountOfItems, benchmarkName)
        val randomString = generateRandomString(10)
        val timeOfCalculation = measureTimeMillis { map[randomString] = MAP_ELEMENT }
        map.remove(randomString)
        return timeOfCalculation
    }

    private fun searchByKeyInMap(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long {
        val map = createMapWithElements(amountOfItems, benchmarkName)
        val randomString = generateRandomString(10)
        map[randomString] = MAP_ELEMENT
        val timeOfCalculation = measureTimeMillis { map.containsKey(randomString) }
        map.remove(randomString)
        return timeOfCalculation
    }

    private fun removeElementsFromMap(
        amountOfItems: Int,
        benchmarkName: BenchmarksNames
    ): Long {
        val map = createMapWithElements(amountOfItems, benchmarkName)
        val randomString = generateRandomString(10)
        map[randomString] = MAP_ELEMENT
        return measureTimeMillis { map.remove(randomString) }
    }

}