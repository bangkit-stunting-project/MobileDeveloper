package com.capstone.anya.ui.profile

import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.anya.model.UserPreference
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

//    fun logout() {
//        viewModelScope.launch {
//            pref.logout()
//        }
//    }
}