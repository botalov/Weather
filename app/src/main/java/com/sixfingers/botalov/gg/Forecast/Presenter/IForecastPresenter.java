package com.sixfingers.botalov.gg.Forecast.Presenter;

import com.sixfingers.botalov.gg.Forecast.View.IForecastView;

public interface IForecastPresenter {
    void attachView(IForecastView view);
    void detachView();

    void onLoadView();
}
