package com.sixfingers.botalov.gg.Cities.Presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.sixfingers.botalov.gg.AddCity.View.AddCityActivity;
import com.sixfingers.botalov.gg.App.ApplicationGG;
import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Cities.Model.CitiesModel;
import com.sixfingers.botalov.gg.Cities.Model.ICitiesModel;
import com.sixfingers.botalov.gg.Cities.View.ICitiesView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class CitiesPresenter implements ICitiesPresenter {
    private ICitiesView view;
    private ICitiesModel model;

    @Override
    public void attachView(ICitiesView view) {
        this.view = view;
        this.model = new CitiesModel();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onStart() {
        ArrayList<CityEntity> cities = model.getAllCities();
        view.updateView(cities);

    }

    @Override
    public void onGoToAddCity() {
        Intent intent = new Intent(ApplicationGG.getAppContext(), AddCityActivity.class);
        //ApplicationGG.getAppContext().startActivity(intent);
        ((AppCompatActivity)view).startActivityForResult(intent, 1);
    }

    @Override
    public void onSelectCity(CityEntity city) {
        model.setSelectedCity(city);
        view.selectCity();
    }
}
