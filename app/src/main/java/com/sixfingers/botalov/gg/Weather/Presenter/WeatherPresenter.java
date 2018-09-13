package com.sixfingers.botalov.gg.Weather.Presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.sixfingers.botalov.gg.App.ApplicationGG;
import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.WeatherOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.Model.Weather;
import com.sixfingers.botalov.gg.Cities.View.CitiesActivity;
import com.sixfingers.botalov.gg.Base.Model.WeatherEntity;
import com.sixfingers.botalov.gg.Forecast.View.ForecastActivity;
import com.sixfingers.botalov.gg.Helpers.WeatherStateHelper;
import com.sixfingers.botalov.gg.Weather.Model.CurrentWeatherModel;
import com.sixfingers.botalov.gg.Weather.Model.ICurrentWeatherModel;
import com.sixfingers.botalov.gg.Weather.View.IWeatherView;

import java.util.Date;
import java.util.UUID;

import io.reactivex.observers.DisposableObserver;

public class WeatherPresenter implements IWeatherPresenter {
    private IWeatherView view;
    private ICurrentWeatherModel model;

    private CityEntity selectedCity;

    @Override
    public void attachView(IWeatherView view) {
        this.view = view;
        this.model = new CurrentWeatherModel();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onStartView() {
        selectedCity = model.getSelectedCity();
        view.showLoadAnimation();
        model.getCurrentWeatherObservable(selectedCity.getLat(), selectedCity.getLon()).subscribeWith(getObserver());
    }

    @Override
    public void goToSelectCity() {
        Intent intent = new Intent(ApplicationGG.getAppContext(), CitiesActivity.class);
        ((AppCompatActivity)view).startActivityForResult(intent, 1);
    }

    @Override
    public void showForecast() {

    }

    @Override
    public void goToForecast() {
        Intent intent = new Intent(ApplicationGG.getAppContext(), ForecastActivity.class);
        ((AppCompatActivity)view).startActivity(intent);
    }

    private DisposableObserver<WeatherEntity> getObserver() {
        return new DisposableObserver<WeatherEntity>() {

            @Override
            public void onNext(WeatherEntity weatherEntity) {
                WeatherOnDataBaseEntity weatherOnDataBaseEntity;
                if(weatherEntity != null){
                    weatherOnDataBaseEntity = new WeatherOnDataBaseEntity();
                    weatherOnDataBaseEntity.setCityId(selectedCity.getId());
                    weatherOnDataBaseEntity.setTemp(weatherEntity.getMain().getTemp());
                    weatherOnDataBaseEntity.setDate(new Date());
                    StringBuilder weatherState = new StringBuilder();
                    for(Weather weather : weatherEntity.getWeather()) {
                        weatherState
                                .append(WeatherStateHelper.getWeatherStateString(weather))
                                .append(".");
                    }
                    weatherOnDataBaseEntity.setCloudsState(weatherState.toString());
                    model.saveWeatherOnDataBase(weatherOnDataBaseEntity);
                }
                else{
                    weatherOnDataBaseEntity = model.getWeatherFromDataBase(selectedCity.getId());
                }

                view.updateView(weatherOnDataBaseEntity, selectedCity.getName());
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoadAnimation();
                view.showError(e.getMessage());

                view.updateView(model.getWeatherFromDataBase(selectedCity.getId()), selectedCity.getName());
            }

            @Override
            public void onComplete() {
                view.hideLoadAnimation();
            }
        };

    }
}
