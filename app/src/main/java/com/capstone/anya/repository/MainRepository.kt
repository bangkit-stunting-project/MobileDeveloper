package com.capstone.anya.repository

import com.example.storyappsubmission.ApiConfig

class MainRepository constructor(private val retrofitService: ApiConfig) {
    fun getAllMovies() = retrofitService.getApiService().childList("token")

}