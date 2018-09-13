package com.sixfingers.botalov.gg.AddCity.Model;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;

import io.realm.Realm;

public class AddCityModel implements IAddCityModel {
    @Override
    public void saveCityToDataBase(CityEntity city) {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            realm.insertOrUpdate(city);
        }
        finally {
            realm.commitTransaction();
        }
    }
}
