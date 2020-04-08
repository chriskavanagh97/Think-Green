package com.example.finalyearapp.Carbonfootprint;

public class LineChartresult {


    Long xValue;
    int yValue;

    public Long getxValue() {
        return xValue;
    }

    public void setxValue(Long xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public LineChartresult(Long xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;

    }

    public LineChartresult() {


    }
}

