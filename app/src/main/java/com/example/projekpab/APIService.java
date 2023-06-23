package com.example.projekpab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>> login(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>> register(@Field("username") String username,
                                   @Field("password") String password);

    @GET("selfvices")
    Call<ValueData<List<Unggah>>> getUnggah();

    @FormUrlEncoded
    @POST("selfvices")
    Call<ValueNoData> addUnggah(@Field("nama") String nama,
                                @Field("alamat") String alamat,
                                @Field("telepon") String telepon,
                                @Field("motor") String motor,
                                @Field("jenis") String jenis,
                                @Field("servis") String servis,
                                @Field("user_id") String user_id);

    @FormUrlEncoded
    @PUT("selfvices")
    Call<ValueNoData> updateUnggah(@Field("id") String id,
                                   @Field("nama") String nama,
                                   @Field("alamat") String alamat,
                                   @Field("telepon") String telepon,
                                   @Field("motor") String motor,
                                   @Field("jenis") String jenis,
                                   @Field("servis") String servis);

    @DELETE("selfvices/{id}")
    Call<ValueNoData> deleteUnggah(@Path("id") String id);

}