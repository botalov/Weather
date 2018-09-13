package com.sixfingers.botalov.gg.Cities.Model;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;

import java.util.ArrayList;

import io.realm.Realm;

public class CitiesModel implements ICitiesModel {

    @Override
    public ArrayList<CityEntity> getAllCities(){
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            return new ArrayList<>(realm.where(CityEntity.class).findAll());
        }
        finally {
            realm.commitTransaction();
        }
    }

    @Override
    public void setSelectedCity(CityEntity city) {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            CityEntity oldSelectedCity = realm.where(CityEntity.class).equalTo("isSelect", true).findFirst();
            if(oldSelectedCity != null){
                oldSelectedCity.setSelect(false);
                realm.insertOrUpdate(oldSelectedCity);
            }
            city.setSelect(true);
            realm.insertOrUpdate(city);

        }
        finally {
            realm.commitTransaction();
        }
    }

}
