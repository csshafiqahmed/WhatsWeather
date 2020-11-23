package com.codestown.whatsweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Weatherapi {
    @GET("weather")
    Call<SerializableObjects> getweather(@Query("q") String cityName,
                                         @Query("appid") String apiKey);
}
