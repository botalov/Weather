package com.sixfingers.botalov.gg.Forecast.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sixfingers.botalov.gg.Base.Model.Forecast.ForecastThreeDaysOnDataBaseEntity;
import com.sixfingers.botalov.gg.Base.View.BaseFullActivity;
import com.sixfingers.botalov.gg.Forecast.Presenter.ForecastPresenter;
import com.sixfingers.botalov.gg.Forecast.Presenter.IForecastPresenter;
import com.sixfingers.botalov.gg.Forecast.View.Fragments.ForecastThreeDaysFragment;
import com.sixfingers.botalov.gg.Forecast.View.Fragments.ForecastSevenDaysFragment;
import com.sixfingers.botalov.gg.R;

import java.util.ArrayList;
import java.util.List;

public class ForecastActivity extends BaseFullActivity implements IForecastView {

    private TextView cityNameTextView;
    private ProgressBar progressBar;

    private IForecastPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        presenter = new ForecastPresenter();
        presenter.attachView(this);

        if (savedInstanceState != null) {
            forecastThreeDaysFragment = (ForecastThreeDaysFragment) getSupportFragmentManager().getFragment(savedInstanceState, "forecastThreeDaysFragment");
        } else {
            forecastThreeDaysFragment = new ForecastThreeDaysFragment();
        }

        initViews();
        initViewPager();

        presenter.onLoadView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "forecastThreeDaysFragment", forecastThreeDaysFragment);
    }

    private void initViews() {
        progressBar = findViewById(R.id.load_progress_bar);
        cityNameTextView = findViewById(R.id.city_name_text_view);
    }

    private ForecastThreeDaysFragment forecastThreeDaysFragment;
    private void initViewPager() {
        ForecastFragmentPagerAdapter adapter = new ForecastFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(forecastThreeDaysFragment, getString(R.string.forecast_three_days));
        adapter.addFragment(new ForecastSevenDaysFragment(), getString(R.string.forecast_seven_days));

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateView(String cityName, ForecastThreeDaysOnDataBaseEntity entity) {
        cityNameTextView.setText(cityName);

        forecastThreeDaysFragment.updateView(entity);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    class ForecastFragmentPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ForecastFragmentPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
