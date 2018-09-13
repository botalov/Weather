package com.sixfingers.botalov.gg.AddCity.Presenter;

import com.google.android.gms.location.places.Place;
import com.sixfingers.botalov.gg.AddCity.View.IAddCityView;

public interface IAddCityPresenter {
    void attachView(IAddCityView view);
    void detachView();
    void onSelectCity(Place place);

}
