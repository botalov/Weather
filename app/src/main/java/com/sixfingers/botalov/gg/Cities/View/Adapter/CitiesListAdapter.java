package com.sixfingers.botalov.gg.Cities.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Cities.Presenter.ICitiesPresenter;
import com.sixfingers.botalov.gg.R;

import java.util.ArrayList;

public class CitiesListAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private ArrayList<CityEntity> data;
    private ICitiesPresenter presenter;

    public void setData(ArrayList<CityEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setPresenter(ICitiesPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new CityViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int position) {
        cityViewHolder.bind(data.get(position));
        cityViewHolder.itemView.setOnClickListener(view -> presenter.onSelectCity(data.get(position)));
    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }
}
