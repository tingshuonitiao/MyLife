package com.mylife.tsnt.translate.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ting说你跳 on 2017/3/14.
 */

public interface TranslateService {
    @GET("/openapi.do")
    Call<TranslateBean> queryTranslation(@Query("keyfrom") String keyfrom,
                                         @Query("key") String key,
                                         @Query("type") String type,
                                         @Query("doctype") String doctype,
                                         @Query("version") String version,
                                         @Query("q") String content,
                                         @Query("only") String only);
}

