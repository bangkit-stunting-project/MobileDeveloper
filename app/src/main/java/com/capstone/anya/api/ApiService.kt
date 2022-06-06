package com.capstone.anya.api

import com.example.storyappsubmission.ApiConfig
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
    @POST("user/anak")
    fun registerAnak(
        @Field("namaLengkap") name: String,
        @Field("tempatLahir") address: String,
        @Field("tanggalLahir") date: String
    ): Call<ResponseRegister>

}
