package com.sixfingers.botalov.gg.Cities.Model;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;

import java.util.ArrayList;

public interface ICitiesModel {
    ArrayList<CityEntity> getAllCities();

    void setSelectedCity(CityEntity city);
}
