package com.sixfingers.botalov.gg.Cities.View;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;

import java.util.ArrayList;

public interface ICitiesView {
    String CITY_ARG = "CITY_ARG";
    void updateView(ArrayList<CityEntity> cities);
    void selectCity();
}
