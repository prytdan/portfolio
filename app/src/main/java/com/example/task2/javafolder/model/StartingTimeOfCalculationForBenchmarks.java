package com.example.task2.javafolder.model;

public enum StartingTimeOfCalculationForBenchmarks {
    STARTING_TIME(-1);

    StartingTimeOfCalculationForBenchmarks(long time) {
        this.time = time;
    }

    public final long time;

}
