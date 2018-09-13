package com.sixfingers.botalov.gg.Helpers;

import android.content.Context;

import com.sixfingers.botalov.gg.App.ApplicationGG;
import com.sixfingers.botalov.gg.Base.Model.Weather;
import com.sixfingers.botalov.gg.R;

public class WeatherStateHelper {

    public static String getWeatherStateString(Weather weather){
        Context context = ApplicationGG.getAppContext();
        if(weather == null) {
            return "";
        }

        int weatherId = weather.getId();
        switch (weatherId) {
            //Гроза
            case 200:
                return context.getString(R.string.thunderstorm_with_light_rain);
            case 201:
                return context.getString(R.string.thunderstorm_with_rain);
            case 202:
                return context.getString(R.string.thunderstorm_with_heavy_rain);
            case 210:
                return context.getString(R.string.light_thunderstorm);
            case 211:
                return context.getString(R.string.thunderstorm);
            case 212:
                return context.getString(R.string.heavy_thunderstorm);
            case 221:
                return context.getString(R.string.ragged_thunderstorm);
            case 230:
                return context.getString(R.string.thunderstorm_with_light_drizzle);
            case 231:
                return context.getString(R.string.thunderstorm_with_drizzle);
            case 232:
                return context.getString(R.string.thunderstorm_with_heavy_drizzle);

            //Морось
            case 300:
                return context.getString(R.string.light_intensity_drizzle);
            case 301:
                return context.getString(R.string.drizzle);
            case 302:
                return context.getString(R.string.heavy_intensity_drizzle);
            case 310:
                return context.getString(R.string.light_intensity_drizzle_rain);
            case 311:
                return context.getString(R.string.drizzle_rain);
            case 312:
                return context.getString(R.string.heavy_intensity_drizzle_rain);
            case 313:
                return context.getString(R.string.shower_rain_and_drizzle);
            case 314:
                return context.getString(R.string.heavy_shower_rain_and_drizzle);
            case 321:
                return context.getString(R.string.shower_drizzle);

            //Дождь
            case 500:
                return context.getString(R.string.light_rain);
            case 501:
                return context.getString(R.string.moderate_rain);
            case 502:
                return context.getString(R.string.heavy_intensity_rain);
            case 503:
                return context.getString(R.string.very_heavy_rain);
            case 504:
                return context.getString(R.string.extreme_rain);
            case 511:
                return context.getString(R.string.freezing_rain);
            case 520:
                return context.getString(R.string.light_intensity_shower_rain);
            case 521:
                return context.getString(R.string.shower_rain);
            case 522:
                return context.getString(R.string.heavy_intensity_shower_rain);
            case 531:
                return context.getString(R.string.ragged_shower_rain);

            //Снег
            case 600:
                return context.getString(R.string.light_snow);
            case 601:
                return context.getString(R.string.snow);
            case 602:
                return context.getString(R.string.heavy_snow);
            case 611:
                return context.getString(R.string.sleet);
            case 612:
                return context.getString(R.string.shower_sleet);
            case 615:
                return context.getString(R.string.light_rain_and_snow);
            case 616:
                return context.getString(R.string.rain_and_snow);
            case 620:
                return context.getString(R.string.light_shower_snow);
            case 621:
                return context.getString(R.string.shower_snow);
            case 622:
                return context.getString(R.string.heavy_shower_snow);

             //Атмосфера
            case 701:
                return context.getString(R.string.mist);
            case 711:
                return context.getString(R.string.smoke);
            case 721:
                return context.getString(R.string.haze);
            case 731:
                return context.getString(R.string.sand_dust_whirls);
            case 741:
                return context.getString(R.string.fog);
            case 751:
                return context.getString(R.string.sand);
            case 761:
                return context.getString(R.string.dust);
            case 762:
                return context.getString(R.string.volcanic_ash);
            case 771:
                return context.getString(R.string.squalls);
            case 781:
                return context.getString(R.string.tornado);

            case 800:
                return context.getString(R.string.clear_sky);
            case 801:
                return context.getString(R.string.few_clouds);
            case 802:
                return context.getString(R.string.scattered_clouds);
            case 803:
                return context.getString(R.string.broken_clouds);
            case 804:
                return context.getString(R.string.overcast_clouds);



            default:
                return "";
        }
    }

}
