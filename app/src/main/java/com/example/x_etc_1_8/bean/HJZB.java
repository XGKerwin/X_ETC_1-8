package com.example.x_etc_1_8.bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class HJZB extends LitePalSupport {

    /**
     *     "temperature": 25,
     *     "humidity": 5,
     *     "illumination": 4300,
     *     "co2": 5490,
     *     "pm25": 43,
     *     "RESULT": "S"
     */

    private int temperature,humidity,illumination,co2,pm25;
    private int roadId;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
}
