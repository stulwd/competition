package com.challenge;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class InputBean {
    public Shift shift;
    public RoboRate roboRate;

    @Override
    public String toString() {
        return "InputBean{" +
                "shift=" + shift +
                ", roboRate=" + roboRate +
                '}';
    }
}

class Shift {
    public LocalDateTime start;
    public LocalDateTime end;

    @Override
    public String toString() {
        return "Shift{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

class RoboRate {
    public RateInfo standardDay;
    public RateInfo standardNight;
    public RateInfo extraDay;
    public RateInfo extraNight;

    @Override
    public String toString() {
        return "RoboRate{" +
                "standardDay=" + standardDay +
                ", standardNight=" + standardNight +
                ", extraDay=" + extraDay +
                ", extraNight=" + extraNight +
                '}';
    }
}

class RateInfo {
    public LocalTime start;
    public LocalTime end;
    public int value;

    @Override
    public String toString() {
        return "RateInfo{" +
                "start=" + start +
                ", end=" + end +
                ", value=" + value +
                '}';
    }
}