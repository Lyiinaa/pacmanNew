package com.pacman.app.engine;

import com.pacman.app.engine.PowerModeObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PowerModeTimer {

    private final List<com.pacman.app.engine.PowerModeObserver> observers = new ArrayList<>();
    private Timer timer;

    public void addObserver(com.pacman.app.engine.PowerModeObserver observer) {
        observers.add(observer);
    }

    public void start(int seconds) {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notifyObservers();
            }
        }, seconds * 1000L);
    }

    private void notifyObservers() {
        observers.forEach(PowerModeObserver::onPowerModeEnd);
    }
}
