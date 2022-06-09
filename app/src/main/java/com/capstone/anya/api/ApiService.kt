package com.capstone.anya.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("user/register")
    fun registerUser(
        @Field("namaLengkap") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") passwordConfirmation: String,
        @Field("tempatLahir") address: String,
        @Field("tanggalLahir") date: String,
        @Field("jenisKelamin") gender: String
    ): Call<ResponseRegister>

    @FormUrlEncoded
    @POST("anak")
    fun registerChild(
        @Header("auth")auth:String,
        @Field("namaLengkap") name: String,
        @Field("tempatLahir") address: String,
        @Field("tanggalLahir") date: String
    ): Call<ResponseRegister>

    @GET("anak")
    fun childList(
        @Header("auth") auth:String
    ): Call<List<ResponseChildListItem>>

    @GET("anak/{id}")
    fun child(
        @Header("auth") auth:String,
        @Path("id") id: Int
    ): Call<List<ResponseChildListItem>>

}
