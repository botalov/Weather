package com.sixfingers.botalov.gg.Cities.View.Adapter;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;

public interface ICityViewHolder {
    void bind(CityEntity cityEntity);
    void showLoader();
    void hideLoader();
    void updateTemp(double temp);
}
