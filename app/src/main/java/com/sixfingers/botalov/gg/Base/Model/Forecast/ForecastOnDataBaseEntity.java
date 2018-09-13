package com.sixfingers.botalov.gg.Base.Model.Forecast;

import java.util.Date;
import io.realm.RealmObject;

public class ForecastOnDataBaseEntity extends RealmObject {

    private String id;

    private Date date;

    private Double minTemp;
    private Double maxTemp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
