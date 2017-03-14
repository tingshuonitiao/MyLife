package com.mylife.tsnt;

import com.mylife.tsnt.bean.JuheWeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Joinwe on 2017/3/14.
 */

public interface WeatherService {
    @GET("v.juhe.cn/weather/index")
    Call<JuheWeatherBean> queryWeather(@Query("cityname") String cityname,
                                       @Query("dtype") String dtype,
                                       @Query("format") String format);
}
/*,
@Query("key") String key*/

