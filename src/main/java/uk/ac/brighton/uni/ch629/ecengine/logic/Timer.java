package uk.ac.brighton.uni.ch629.ecengine.logic;

public class Timer {
    long lastNano;

    public Timer() {
        lastNano = System.nanoTime();
    }

    public long getDeltaTimeNano() {
        long delta = System.nanoTime() - lastNano;
        lastNano = System.nanoTime();
        return delta;
    }

    public double getDeltaTimeMilli() {
        return getDeltaTimeNano() / 1000000d;
    }

    public double getDeltaTimeSeconds() {
        return getDeltaTimeMilli() / 1000d;
    }

    public double getFPS() {
        return 1d / getDeltaTimeSeconds();
    }
}