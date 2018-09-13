package com.sixfingers.botalov.gg.Base.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherOnDataBaseEntity extends RealmObject {

    @PrimaryKey
    private String cityId;
    private Date date;
    private Double temp;
    private String cloudsState;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getCloudsState() {
        return cloudsState;
    }

    public void setCloudsState(String cloudsState) {
        this.cloudsState = cloudsState;
    }
}
