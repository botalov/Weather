package com.sixfingers.botalov.gg.Cities.Presenter.Adapter;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.WeatherEntity;
import com.sixfingers.botalov.gg.Cities.View.Adapter.ICityViewHolder;
import com.sixfingers.botalov.gg.Weather.Model.CurrentWeatherModel;

import io.reactivex.observers.DisposableObserver;

public class CityItemPresenter implements ICityItemPresenter {
    private ICityViewHolder view;
    private CurrentWeatherModel model;

    public CityItemPresenter(){
        model = new CurrentWeatherModel();
    }

    @Override
    public void attachView(ICityViewHolder view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onBind(CityEntity cityEntity) {
        view.showLoader();
        model.getCurrentWeatherObservable(cityEntity.getLat(), cityEntity.getLon()).subscribeWith(getObserver());
    }

    private DisposableObserver<WeatherEntity> getObserver() {
        return new DisposableObserver<WeatherEntity>() {

            @Override
            public void onNext(WeatherEntity weatherEntity) {
                view.updateTemp(weatherEntity.getMain().getTemp());
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoader();
            }

            @Override
            public void onComplete() {
                view.hideLoader();
            }
        };

    }
}
