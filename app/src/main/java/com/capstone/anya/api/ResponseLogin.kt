package com.capstone.anya.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLogin(

	@field:SerializedName("token")
	val token: String

) : Parcelable
