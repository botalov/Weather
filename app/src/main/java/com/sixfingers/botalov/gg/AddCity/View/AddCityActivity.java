package com.sixfingers.botalov.gg.AddCity.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.sixfingers.botalov.gg.AddCity.Presenter.AddCityPresenter;
import com.sixfingers.botalov.gg.Base.View.BaseFullActivity;
import com.sixfingers.botalov.gg.R;

public class AddCityActivity extends BaseFullActivity implements IAddCityView {

    private AddCityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        initViews();

        presenter = new AddCityPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(presenter != null){
            presenter.detachView();
        }
    }

    private void initViews() {
        PlaceAutocompleteFragment places= (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        places.setFilter(typeFilter);
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                presenter.onSelectCity(place);
            }

            @Override
            public void onError(Status status) {
                //Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onFinishSearch() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
