package com.sixfingers.botalov.gg.Network.CurrenWeather;

import com.sixfingers.botalov.gg.Base.Model.WeatherEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICurrentWeatherNetwork {
    @GET("data/2.5/weather")
    Observable<WeatherEntity> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appId, @Query("units") String units);
}
