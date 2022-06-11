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
data class ResponseChildListItem(

	@field:SerializedName("tempatLahir")
	val tempatLahir: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("jenisKelamin")
	val jenisKelamin: String,

	@field:SerializedName("tanggalLahir")
	val tanggalLahir: String,

	@field:SerializedName("namaLengkap")
	val namaLengkap: String,

	@field:SerializedName("parentId")
	val parentId: Int

) : Parcelable

@Parcelize
data class ResponseEducationItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("urlToImage")
	val urlToImage: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("publishedAt")
	val publishedAt: String,

) : Parcelable


