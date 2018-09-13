package com.sixfingers.botalov.gg.Weather.Presenter;

import com.sixfingers.botalov.gg.Weather.View.IWeatherView;

public interface IWeatherPresenter {
    void attachView(IWeatherView view);
    void detachView();

    void onStartView();

    /**
     * Переход к выбору города
     */
    void goToSelectCity();

    /**
     * Показать прогноз
     */
    void showForecast();

    void goToForecast();
}
