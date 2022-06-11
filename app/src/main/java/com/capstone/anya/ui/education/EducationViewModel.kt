package com.capstone.anya.ui.education


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.anya.api.ResponseEducationItem
import com.capstone.anya.model.UserModel
import com.capstone.anya.model.UserPreference
import com.example.storyappsubmission.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationViewModel(private val pref: UserPreference) : ViewModel() {

    fun getToken(): LiveData<UserModel> {
        return pref.getToken().asLiveData()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _educationList = MutableLiveData<List<ResponseEducationItem>>()
    val educationList: MutableLiveData<List<ResponseEducationItem>> = _educationList

    fun getEducation(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().education(token)
        client.enqueue(object : Callback<List<ResponseEducationItem>> {
            override fun onResponse(
                call: Call<List<ResponseEducationItem>>,
                response: Response<List<ResponseEducationItem>>
            ) {
                _isLoading.value = false
                Log.d("OKE", token)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _educationList.value = responseBody!!
                    Log.d("SUCCESS", responseBody.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ResponseEducationItem>>, t: Throwable) {
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