package com.sixfingers.botalov.gg.Forecast.Model;

import com.sixfingers.botalov.gg.App.ApplicationGG;
import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;
import com.sixfingers.botalov.gg.Network.ForecastWeather.IForecastWeatherNetwork;
import com.sixfingers.botalov.gg.Network.WeatherNetwork;
import com.sixfingers.botalov.gg.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Retrofit;

public class ForecastModel implements IForecastModel {
    private WeatherNetwork network;

    public ForecastModel(){
        network = new WeatherNetwork();
    }

    @Override
    public Observable<ForecastEntity> getForecastThreeDaysWeatherObservable(double lat, double lon) {
        Retrofit retrofit = network.getRetrofit();
        return retrofit.create(IForecastWeatherNetwork.class)
                .getForecastThreeDays(lat, lon, ApplicationGG.getAppContext().getString(R.string.app_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public ForecastThreeDaysOnDataBaseEntity getForecastThreeDaysFromDataBase(String city) {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            return realm.where(ForecastThreeDaysOnDataBaseEntity.class).equalTo("cityId", city).findFirst();
        }
        finally {
            realm.commitTransaction();
        }
    }

    @Override
    public CityEntity getSelectedCity() {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            return realm.where(CityEntity.class).equalTo("isSelect", true).findFirst();
        }
        finally {
            realm.commitTransaction();
        }
    }

    @Override
    public void saveForecastThreeDays(ForecastThreeDaysOnDataBaseEntity entity) {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            realm.insertOrUpdate(entity);
        }
        finally {
            realm.commitTransaction();
        }
    }
}
