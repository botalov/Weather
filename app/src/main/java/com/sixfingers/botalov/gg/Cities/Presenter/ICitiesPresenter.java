package com.sixfingers.botalov.gg.Cities.Presenter;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Cities.View.ICitiesView;

public interface ICitiesPresenter {
    void attachView(ICitiesView view);
    void detachView();

    void onStart();
    void onGoToAddCity();

    void onSelectCity(CityEntity city);
}
