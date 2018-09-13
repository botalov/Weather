package com.sixfingers.botalov.gg.Weather.Model;

import com.sixfingers.botalov.gg.App.ApplicationGG;
import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.WeatherOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.Model.WeatherEntity;
import com.sixfingers.botalov.gg.R;
import com.sixfingers.botalov.gg.Network.WeatherNetwork;
import com.sixfingers.botalov.gg.Network.CurrenWeather.ICurrentWeatherNetwork;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Retrofit;

public class CurrentWeatherModel implements ICurrentWeatherModel {
    private WeatherNetwork network;

    public CurrentWeatherModel(){
        network = new WeatherNetwork();
    }

    @Override
    public Observable<WeatherEntity> getCurrentWeatherObservable(double lat, double lon) {
        Retrofit retrofit = network.getRetrofit();
        return retrofit.create(ICurrentWeatherNetwork.class)
                .getWeather(lat, lon, ApplicationGG.getAppContext().getString(R.string.app_id), ApplicationGG.getAppContext().getString(R.string.units))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveWeatherOnDataBase(WeatherOnDataBaseEntity weather) {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            realm.insertOrUpdate(weather);
        }
        finally {
            realm.commitTransaction();
        }
    }

    @Override
    public WeatherOnDataBaseEntity getWeatherFromDataBase(String city) {
        Realm realm = Realm.getDefaultInstance();
        try{
            realm.beginTransaction();
            return realm.where(WeatherOnDataBaseEntity.class).equalTo("cityId", city).findFirst();
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
}
