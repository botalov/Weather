package com.sixfingers.botalov.gg.AddCity.Presenter;

import com.google.android.gms.location.places.Place;
import com.sixfingers.botalov.gg.AddCity.Model.AddCityModel;
import com.sixfingers.botalov.gg.AddCity.Model.IAddCityModel;
import com.sixfingers.botalov.gg.AddCity.View.IAddCityView;
import com.sixfingers.botalov.gg.Base.Model.CityEntity;

import java.util.UUID;

public class AddCityPresenter implements IAddCityPresenter {
    private IAddCityView view;
    private IAddCityModel model;

    public AddCityPresenter(){
        this.model = new AddCityModel();
    }

    @Override
    public void attachView(IAddCityView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onSelectCity(Place place) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(UUID.randomUUID().toString());
        cityEntity.setLat(place.getLatLng().latitude);
        cityEntity.setLon(place.getLatLng().longitude);
        cityEntity.setName(place.getName().toString());
        cityEntity.setSelect(false);

        model.saveCityToDataBase(cityEntity);
        view.onFinishSearch();
    }
}
