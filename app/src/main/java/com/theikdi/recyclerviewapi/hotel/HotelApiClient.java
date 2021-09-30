package com.theikdi.recyclerviewapi.hotel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelApiClient {
    private static final String BASE_URL = "https://theikdimaung.com/testfive/";
    private static Retrofit retrofit;

    public static Retrofit getHotelApiClient(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
