package com.sixfingers.botalov.gg.Network.ForecastWeather;

import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IForecastWeatherNetwork {
    @GET("data/2.5/forecast")
    Observable<ForecastEntity> getForecastThreeDays(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appId);
}
