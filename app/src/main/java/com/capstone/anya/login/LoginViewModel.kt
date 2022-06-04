package com.capstone.anya.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.capstone.anya.R
import com.capstone.anya.api.ResponseLogin
import com.capstone.anya.model.UserModel
import com.capstone.anya.model.UserPreference
import com.example.storyappsubmission.ApiConfig
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getToken(): LiveData<UserModel> {
        return pref.getToken().asLiveData()
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }

    fun clearToken() {
        viewModelScope.launch {
            pref.clearToken()
        }
    }

    fun postLogin(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().loginUser(email, password)
        client.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                Log.d(TAG, "$response")
                if (response.isSuccessful && responseBody != null) {
                    saveToken(responseBody.token)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}