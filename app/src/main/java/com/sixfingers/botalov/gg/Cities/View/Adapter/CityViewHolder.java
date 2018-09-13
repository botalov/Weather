package com.sixfingers.botalov.gg.Cities.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Cities.Presenter.Adapter.CityItemPresenter;
import com.sixfingers.botalov.gg.Cities.Presenter.Adapter.ICityItemPresenter;
import com.sixfingers.botalov.gg.R;

import java.util.Locale;

class CityViewHolder extends RecyclerView.ViewHolder implements ICityViewHolder {
    private CityEntity cityEntity;
    private ICityItemPresenter presenter;

    private TextView tempTextView;
    private ProgressBar loadProgressBar;

    CityViewHolder(@NonNull View itemView) {
        super(itemView);
        presenter = new CityItemPresenter();
        presenter.attachView(this);
    }



    @Override
    public void bind(CityEntity cityEntity){
        this.cityEntity = cityEntity;
        TextView textView = itemView.findViewById(R.id.city_name_text_view);
        textView.setText(cityEntity.getName());

        tempTextView = itemView.findViewById(R.id.temp_text_view);
        loadProgressBar = itemView.findViewById(R.id.load_progress_bar);

        presenter.onBind(this.cityEntity);
    }

    @Override
    public void showLoader() {
        loadProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loadProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateTemp(double temp) {
        tempTextView.setText(String.format(Locale.getDefault(), "%.0f°C", temp));
    }


}