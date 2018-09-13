package com.sixfingers.botalov.gg.Forecast.View.Fragments.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastOnDataBaseEntity;
import com.sixfingers.botalov.gg.R;

import java.util.ArrayList;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastViewHolder> {
    private ArrayList<ForecastOnDataBaseEntity> data;

    public void setData(ForecastThreeDaysOnDataBaseEntity data) {
        this.data = new ArrayList<>();
        this.data.addAll(data.getForecastsList());
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ForecastViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forecast_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder forecastViewHolder, int position) {
        forecastViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }
}
