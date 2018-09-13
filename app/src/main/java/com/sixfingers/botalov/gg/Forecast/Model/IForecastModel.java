package com.sixfingers.botalov.gg.Forecast.Model;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;

import io.reactivex.Observable;

public interface IForecastModel {
    Observable<ForecastEntity> getForecastThreeDaysWeatherObservable(double lat, double lon);
    ForecastThreeDaysOnDataBaseEntity getForecastThreeDaysFromDataBase(String city);
    CityEntity getSelectedCity();
    void saveForecastThreeDays(ForecastThreeDaysOnDataBaseEntity entity);
}
