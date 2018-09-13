package com.sixfingers.botalov.gg.Cities.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.melnykov.fab.FloatingActionButton;
import com.sixfingers.botalov.gg.Base.Model.CityEntity;
import com.sixfingers.botalov.gg.Base.View.BaseFullActivity;
import com.sixfingers.botalov.gg.Cities.Presenter.CitiesPresenter;
import com.sixfingers.botalov.gg.Cities.Presenter.ICitiesPresenter;
import com.sixfingers.botalov.gg.Cities.View.Adapter.CitiesListAdapter;
import com.sixfingers.botalov.gg.R;

import java.util.ArrayList;

public class CitiesActivity extends BaseFullActivity implements ICitiesView {
    private ICitiesPresenter presenter;
    private  RecyclerView recyclerView;
    private CitiesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        presenter = new CitiesPresenter();
        presenter.attachView(this);

        initViews();
        presenter.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                presenter.onStart();
                break;
        }
    }

    private void initViews(){
        recyclerView = findViewById(R.id.cities_recycler_view);
        adapter = new CitiesListAdapter();
        adapter.setPresenter(presenter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton addFab = findViewById(R.id.add_city_fab);
        addFab.attachToRecyclerView(recyclerView);
        addFab.setOnClickListener(view -> presenter.onGoToAddCity());
    }

    @Override
    public void updateView(ArrayList<CityEntity> cities) {
        adapter.setData(cities);
    }

    @Override
    public void selectCity() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
