package com.capstone.anya.ui.child.input

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.anya.api.ResponseRegister
import com.example.storyappsubmission.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildInputViewModel() : ViewModel()  {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    fun postRegisterChild(token:String, name: String, address: String, date: String, gender: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().registerChild(token, name, address, date, gender)
        client.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                Log.d(TAG, "$response")
                if (response.isSuccessful && responseBody != null) {
                    _isDone.value = true
                    Log.d(TAG, "$_isDone")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "Gagal")
            }
        })
    }

    companion object {
        private const val TAG = "InputAnakViewModel"
    }
}