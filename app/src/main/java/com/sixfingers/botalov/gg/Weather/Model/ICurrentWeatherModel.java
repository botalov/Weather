package com.sixfingers.botalov.gg.Weather.Model;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.WeatherOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.Model.WeatherEntity;

import io.reactivex.Observable;

public interface ICurrentWeatherModel {
    Observable<WeatherEntity> getCurrentWeatherObservable(double lat, double lon);
    void saveWeatherOnDataBase(WeatherOnDataBaseEntity weather);
    WeatherOnDataBaseEntity getWeatherFromDataBase(String city);
    CityEntity getSelectedCity();
}
