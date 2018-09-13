package com.sixfingers.botalov.gg.Weather.View;

import com.sixfingers.botalov.gg.Base.Model.WeatherOnDataBaseEntity;

public interface IWeatherView {
    void showLoadAnimation();
    void hideLoadAnimation();
    void updateView(WeatherOnDataBaseEntity weatherEntity, String cityName);
    void showError(String message);
}
