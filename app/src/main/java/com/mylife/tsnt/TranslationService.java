package com.mylife.tsnt;

import com.mylife.tsnt.bean.YoudaoFanyiBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ting说你跳 on 2017/3/14.
 */

public interface TranslationService {
    @GET("/openapi.do")
    Call<YoudaoFanyiBean> queryWeather(@Query("keyfrom") String keyfrom,
                                       @Query("key") String key,
                                       @Query("type") String type,
                                       @Query("doctype") String doctype,
                                       @Query("version") String version,
                                       @Query("q") String content,
                                       @Query("only") String only);
}

