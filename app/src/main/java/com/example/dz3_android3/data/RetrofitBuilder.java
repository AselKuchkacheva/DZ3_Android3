package com.example.dz3_android3.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private RetrofitBuilder() {
    }

    private static MockerApi instance;

    public static MockerApi getInstance() {
        if (instance == null) {
            instance = buildRetrofit();
        }
        return instance;
    }

private static MockerApi buildRetrofit(){
return new Retrofit.Builder()
        .baseUrl("https://android-3-mocker.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MockerApi.class);
}
}
