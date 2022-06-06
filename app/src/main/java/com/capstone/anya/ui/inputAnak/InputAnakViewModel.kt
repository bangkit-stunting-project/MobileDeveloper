package com.capstone.anya.ui.inputAnak

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.anya.api.ResponseRegister
import com.capstone.anya.register.RegisterViewModel
import com.example.storyappsubmission.ApiConfig
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputAnakViewModel() : ViewModel()  {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    fun postRegisterAnak(name: String, address: String, date: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().registerAnak(name, address, date)
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
                } else {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    Log.d("TAG", jsonObj.getString("message"))
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "InputAnakViewModel"
    }
}