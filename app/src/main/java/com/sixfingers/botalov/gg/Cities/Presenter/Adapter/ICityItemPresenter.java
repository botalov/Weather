package com.sixfingers.botalov.gg.Cities.Presenter.Adapter;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Cities.View.Adapter.ICityViewHolder;

public interface ICityItemPresenter {
    void attachView(ICityViewHolder view);
    void detachView();
    void onBind(CityEntity cityEntity);
}
