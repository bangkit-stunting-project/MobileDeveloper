package com.capstone.anya.ui.child.list

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.*
import com.capstone.anya.api.ResponseChildList
import com.capstone.anya.api.ResponseChildListItem
import com.capstone.anya.api.ResponseRegister
import com.capstone.anya.model.UserPreference
import com.capstone.anya.repository.MainRepository
import com.example.storyappsubmission.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildListViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _childList = MutableLiveData<List<ResponseChildListItem>>()
    val childList: MutableLiveData<List<ResponseChildListItem>> = _childList

    fun getChildList() {
        _isLoading.value = true
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTIsImVtYWlsIjoidGVzdGluZ0BnbWFpbC5jb20iLCJpYXQiOjE2NTQ3MDY2NjAsImV4cCI6MTY1NzMxMzA2MH0.yP0kswSUVTRo47NCoQ3s9cZlSClhELxo05LoCBvzpog"
        val client = ApiConfig.getApiService().childList(token)
        Log.d(TAG, token)
        client.enqueue(object : Callback<List<ResponseChildListItem>> {
            override fun onResponse(
                call: Call<List<ResponseChildListItem>>,
                response: Response<List<ResponseChildListItem>>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _childList.value = responseBody!!
                    Log.d("SUCCESS", responseBody.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ResponseChildListItem>>, t: Throwable) {
                Log.e(TAG, "Gagal")
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "ChildListViewModel"
        private const val SUCCESS = "Success"
    }
}