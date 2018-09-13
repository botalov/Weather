package com.sixfingers.botalov.gg.App;

import android.app.Application;
import android.content.Context;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ApplicationGG extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder().name("gg.realm").build();
        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();

            if (realm.where(CityEntity.class).findAll().size() == 0) {
                CityEntity cityEntity = realm.createObject(CityEntity.class, UUID.randomUUID().toString());
                cityEntity.setName("Москва");
                cityEntity.setSelect(true);
                cityEntity.setLat(55.751244);
                cityEntity.setLon(37.618423);
                realm.insert(cityEntity);
            }

        } finally {
            realm.commitTransaction();
        }


        ApplicationGG.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ApplicationGG.context;
    }
}
