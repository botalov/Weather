package com.sixfingers.botalov.gg.Forecast.Presenter;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.List;
import com.sixfingers.botalov.gg.Forecast.Model.ForecastModel;
import com.sixfingers.botalov.gg.Forecast.Model.IForecastModel;
import com.sixfingers.botalov.gg.Forecast.View.IForecastView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import io.reactivex.observers.DisposableObserver;
import io.realm.RealmList;

public class ForecastPresenter implements IForecastPresenter {
    private IForecastView view;
    private IForecastModel model;

    private CityEntity selectedCity;

    public ForecastPresenter(){
        model = new ForecastModel();
    }

    @Override
    public void attachView(IForecastView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onLoadView() {
        selectedCity = model.getSelectedCity();
        view.showLoader();
        model.getForecastThreeDaysWeatherObservable(selectedCity.getLat(), selectedCity.getLon()).subscribeWith(getObserver());
    }

    private DisposableObserver<ForecastEntity> getObserver() {
        return new DisposableObserver<ForecastEntity>() {

            @Override
            public void onNext(ForecastEntity weatherEntity) {

                if(weatherEntity != null) {
                    ForecastThreeDaysOnDataBaseEntity forecastThreeDaysOnDataBaseEntity = new ForecastThreeDaysOnDataBaseEntity();
                    forecastThreeDaysOnDataBaseEntity.setCityId(selectedCity.getId());
                    RealmList<ForecastOnDataBaseEntity> forecastList = new RealmList<>();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                    Double maxTemp = Double.MIN_VALUE;
                    Double minTemp = Double.MAX_VALUE;

                    if (weatherEntity.getList() != null && weatherEntity.getList().size() > 0) {
                        Date oldDate = null;
                        Date date = null;
                        try {
                            oldDate = format.parse(weatherEntity.getList().get(0).getDtTxt());
                            date = oldDate;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        for (List list : weatherEntity.getList()) {
                            try {
                                date = format.parse(list.getDtTxt());
                                if (!date.equals(oldDate)) {
                                    ForecastOnDataBaseEntity forecastOnDataBaseEntity = new ForecastOnDataBaseEntity();
                                    forecastOnDataBaseEntity.setDate(oldDate);
                                    forecastOnDataBaseEntity.setId(UUID.randomUUID().toString());
                                    forecastOnDataBaseEntity.setMaxTemp(maxTemp - 273.15);
                                    forecastOnDataBaseEntity.setMinTemp(minTemp - 273.15);
                                    if(forecastList.size() < 3) {
                                        forecastList.add(forecastOnDataBaseEntity);
                                    }

                                    maxTemp = Double.MIN_VALUE;
                                    minTemp = Double.MAX_VALUE;
                                    oldDate = date;
                                }

                                if (list.getMain().getTemp() > maxTemp) {
                                    maxTemp = list.getMain().getTemp();
                                }
                                if (list.getMain().getTemp() < minTemp) {
                                    minTemp = list.getMain().getTemp();
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        ForecastOnDataBaseEntity forecastOnDataBaseEntity = new ForecastOnDataBaseEntity();
                        forecastOnDataBaseEntity.setDate(oldDate);
                        forecastOnDataBaseEntity.setId(UUID.randomUUID().toString());
                        forecastOnDataBaseEntity.setMaxTemp(maxTemp - 273.15);
                        forecastOnDataBaseEntity.setMinTemp(minTemp - 273.15);
                        forecastOnDataBaseEntity.setDate(date);
                        if(forecastList.size() < 3) {
                            forecastList.add(forecastOnDataBaseEntity);
                        }

                        forecastThreeDaysOnDataBaseEntity.setForecastsList(forecastList);
                    }
                    forecastThreeDaysOnDataBaseEntity.setCityId(selectedCity.getId());
                    model.saveForecastThreeDays(forecastThreeDaysOnDataBaseEntity);
                    view.updateView(selectedCity.getName(), forecastThreeDaysOnDataBaseEntity);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoader();
                ForecastThreeDaysOnDataBaseEntity forecastThreeDaysOnDataBaseEntity = model.getForecastThreeDaysFromDataBase(selectedCity.getId());
                if(forecastThreeDaysOnDataBaseEntity != null) {
                    view.updateView(selectedCity.getName(), forecastThreeDaysOnDataBaseEntity);
                }
                else{
                    view.showError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                view.hideLoader();
            }
        };

    }
}
