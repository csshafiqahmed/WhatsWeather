package com.codestown.whatsweather;

import com.google.gson.annotations.SerializedName;

public class SerializableObjects {
    @SerializedName("main")
    WeatherModal weatherModal;

    public WeatherModal getWeatherModal() {
        return weatherModal;
    }

    public void setWeatherModal(WeatherModal weatherModal) {
        this.weatherModal = weatherModal;
    }
}
