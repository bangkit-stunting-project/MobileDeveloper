package com.capstone.anya.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.anya.model.UserModel
import com.capstone.anya.model.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    fun getToken(): LiveData<UserModel> {
        return pref.getToken().asLiveData()
    }

    fun saveToken(user: UserModel) {
        viewModelScope.launch {
            pref.saveToken(user)
        }
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }
}