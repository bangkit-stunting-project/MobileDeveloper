package com.capstone.anya.register

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.capstone.anya.R
import com.capstone.anya.api.ResponseLogin
import com.capstone.anya.api.ResponseRegister
import com.capstone.anya.model.UserModel
import com.capstone.anya.model.UserPreference
import com.example.storyappsubmission.ApiConfig
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegisterViewModel() : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    fun postRegister(name: String, email: String, password: String, passwordConfirm: String, address: String, date: String, gender: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().registerUser(name, email, password, passwordConfirm, address, date, gender)
        client.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _isDone.value = true
                    Log.d(SUCCESS, "$_isDone")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "RegisterViewModel"
        private const val SUCCESS = "LoginSuccess"
    }
}