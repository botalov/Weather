package com.sixfingers.botalov.gg.Base.Model.Forecast;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ForecastThreeDaysOnDataBaseEntity extends RealmObject {
    private String cityId;

    private RealmList<ForecastOnDataBaseEntity> forecastsList;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public RealmList<ForecastOnDataBaseEntity> getForecastsList() {
        return forecastsList;
    }

    public void setForecastsList(RealmList<ForecastOnDataBaseEntity> forecastsList) {
        this.forecastsList = forecastsList;
    }
}
