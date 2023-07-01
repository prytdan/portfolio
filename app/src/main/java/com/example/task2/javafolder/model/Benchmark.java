package com.example.task2.javafolder.model;

import java.util.Objects;

public class Benchmark {

    private final BenchmarksNames name;
    private long timeOfCalculations;
    private StateOfCalculation state;
    private String representationOfCalculationsTime = "N/A";

    public Benchmark(BenchmarksNames name, long timeOfCalculations, StateOfCalculation state) {
        this.name = name;
        this.timeOfCalculations = timeOfCalculations;
        this.state = state;
    }

    public BenchmarksNames getName() {
        return name;
    }

    public long getTimeOfCalculations() {
        return timeOfCalculations;
    }

    public void setTimeOfCalculations(long timeOfCalculations) {
        this.timeOfCalculations = timeOfCalculations;
    }

    public StateOfCalculation getState() {
        return state;
    }

    public void setState(StateOfCalculation state) {
        this.state = state;
    }

    public String getRepresentationOfCalculationsTime() {
        return representationOfCalculationsTime;
    }

    public void setRepresentationOfCalculationsTime(String representationOfCalculationsTime) {
        this.representationOfCalculationsTime = representationOfCalculationsTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Benchmark other = (Benchmark) obj;
        return Objects.equals(name, other.name) &&
                timeOfCalculations == other.timeOfCalculations &&
                Objects.equals(state, other.state);
    }

    @Override
    public String toString() {
        return "Benchmark{" +
                "name=" + name +
                ", timeOfCalculations=" + timeOfCalculations +
                ", state=" + state +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, timeOfCalculations, state);
    }
}
