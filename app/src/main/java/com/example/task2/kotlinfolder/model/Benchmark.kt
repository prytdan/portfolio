package com.example.task2.kotlinfolder.model

data class Benchmark(
    val name: BenchmarksNames,
    val timeOfCalculation: Long? = null,
    val stateOfCalculation: StateOfCalculation = StateOfCalculation.TERMINATED,
    val timeOfCalculationToString: String = "N/A"
)