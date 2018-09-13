package com.sixfingers.botalov.gg.Forecast.View;

import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;

public interface IForecastView {
    void showLoader();
    void hideLoader();

    void updateView(String cityName, ForecastThreeDaysOnDataBaseEntity entity);
    void showError(String message);
}
