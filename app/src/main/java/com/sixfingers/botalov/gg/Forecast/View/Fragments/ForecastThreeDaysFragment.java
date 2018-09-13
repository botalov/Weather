package com.sixfingers.botalov.gg.Forecast.View.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;
import com.sixfingers.botalov.gg.Forecast.View.Fragments.Adapter.ForecastListAdapter;
import com.sixfingers.botalov.gg.R;

public class ForecastThreeDaysFragment extends Fragment {

    private ForecastListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forecast_three_days_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getView() != null) {
            initViews(getView());
        }
    }

    private void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.forecast_three_days_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ForecastListAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void updateView(ForecastThreeDaysOnDataBaseEntity entity) {
        if(adapter == null) {
            adapter = new ForecastListAdapter();
        }
        adapter.setData(entity);
        adapter. notifyDataSetChanged();
    }
}
