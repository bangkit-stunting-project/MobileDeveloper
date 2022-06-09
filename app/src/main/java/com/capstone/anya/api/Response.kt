package com.capstone.anya.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseLogin(

	@field:SerializedName("token")
	val token: String,

	@field:SerializedName("message")
	val message: String

) : Parcelable

@Parcelize
data class ResponseRegister(

	@field:SerializedName("message")
	val message: String

) : Parcelable

@Parcelize
data class ResponseChildList(

	@field:SerializedName("ResponseChildList")
	val responseChildList: List<ResponseChildListItem>

) : Parcelable

@Parcelize
data class ResponseChildListItem(

	@field:SerializedName("tempatLahir")
	val tempatLahir: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tanggalLahir")
	val tanggalLahir: String,

	@field:SerializedName("namaLengkap")
	val namaLengkap: String,

	@field:SerializedName("parentId")
	val parentId: Int

) : Parcelable
