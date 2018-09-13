package com.sixfingers.botalov.gg.Weather.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sixfingers.botalov.gg.Base.Model.WeatherOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.View.BaseFullActivity;
import com.sixfingers.botalov.gg.R;
import com.sixfingers.botalov.gg.Weather.Presenter.IWeatherPresenter;
import com.sixfingers.botalov.gg.Weather.Presenter.WeatherPresenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends BaseFullActivity implements IWeatherView {

    private IWeatherPresenter presenter;

    private TextView cityNameTextView;
    private TextView tempTextView;
    private TextView cloudsTextView;
    private TextView dateTimeUpdateTextView;

    private TextView emptyDataTextView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        presenter = new WeatherPresenter();
        presenter.attachView(this);

        initViews();

        presenter.onStartView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                presenter.onStartView();
                break;
        }
    }

    private void initViews() {
        ImageButton goToSelectCityButton = findViewById(R.id.city_image_button);
        goToSelectCityButton.setOnClickListener(v -> presenter.goToSelectCity());

        cityNameTextView = findViewById(R.id.city_name_text_view);
        dateTimeUpdateTextView = findViewById(R.id.update_text_view);
        cloudsTextView = findViewById(R.id.clouds_text_view);
        tempTextView = findViewById(R.id.temp_text_view);

        progressBar = findViewById(R.id.load_progress_bar);

        Button forecastButton = findViewById(R.id.forecast_button);
        forecastButton.setOnClickListener(view -> presenter.goToForecast());

        emptyDataTextView = findViewById(R.id.empty_date_text_view);
    }

    @Override
    public void showLoadAnimation() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadAnimation() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateView(WeatherOnDataBaseEntity weatherEntity, String cityName) {
        if(weatherEntity != null) {
            emptyDataTextView.setVisibility(View.GONE);
            cityNameTextView.setText(cityName);
            tempTextView.setText(String.format(Locale.getDefault(), "%.0f°C", weatherEntity.getTemp()));

            cloudsTextView.setText(weatherEntity.getCloudsState());

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            dateTimeUpdateTextView.setText(dateFormat.format(weatherEntity.getDate()));
        }
        else{
            emptyDataTextView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showError(String message) {

    }
}
