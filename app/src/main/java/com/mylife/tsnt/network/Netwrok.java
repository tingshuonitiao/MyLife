package com.mylife.tsnt.network;

import com.mylife.tsnt.network.api.TranslateApi;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ting说你跳 on 2017/3/16.
 */

public class Netwrok {
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static TranslateApi translateApi;

    public static TranslateApi getTranslateApi() {
        if (translateApi == null) {
            String url = "http://fanyi.youdao.com";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            translateApi = retrofit.create(TranslateApi.class);
        }
        return translateApi;
    }
}
