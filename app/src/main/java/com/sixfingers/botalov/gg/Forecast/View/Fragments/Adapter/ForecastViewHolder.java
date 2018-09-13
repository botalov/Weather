package com.sixfingers.botalov.gg.Forecast.View.Fragments.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastOnDataBaseEntity;
import com.sixfingers.botalov.gg.Cities.Presenter.Adapter.ICityItemPresenter;
import com.sixfingers.botalov.gg.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

class ForecastViewHolder extends RecyclerView.ViewHolder {

    ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(ForecastOnDataBaseEntity forecast) {
        if (forecast != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            TextView dateTextView = itemView.findViewById(R.id.date_text_view);
            dateTextView.setText(dateFormat.format(forecast.getDate()));

            TextView maxTempTextView = itemView.findViewById(R.id.max_temp_text_view);
            maxTempTextView.setText(String.format(Locale.getDefault(), "%.0f°C", forecast.getMaxTemp()));

            TextView minTempTextView = itemView.findViewById(R.id.min_temp_text_view);
            minTempTextView.setText(String.format(Locale.getDefault(), "%.0f°C", forecast.getMinTemp()));

        }
    }
}