package com.theikdi.recyclerviewapi.hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HotelApiInterface {


    @POST("api2.json") // get_pets.php
    Call<List<Hotels>> getHotels();

    @FormUrlEncoded
    @POST("add_pet.php")
    Call<Hotels> insertPet(
            @Field("key") String key,
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed,
            @Field("gender") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_pet.php")
    Call<Hotels> updatePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed,
            @Field("gender") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_pet.php")
    Call<Hotels> deletePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Hotels> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);
}
