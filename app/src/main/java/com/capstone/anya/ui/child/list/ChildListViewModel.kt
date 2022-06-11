package com.capstone.anya.ui.child.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.anya.api.ResponseChildListItem
import com.capstone.anya.model.UserModel
import com.capstone.anya.model.UserPreference
import com.example.storyappsubmission.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildListViewModel(private val pref: UserPreference) : ViewModel() {

    fun getToken(): LiveData<UserModel> {
        return pref.getToken().asLiveData()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _childList = MutableLiveData<List<ResponseChildListItem>>()
    val childList: MutableLiveData<List<ResponseChildListItem>> = _childList

    fun getChildList(token: String) {
        _isLoading.value = true
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