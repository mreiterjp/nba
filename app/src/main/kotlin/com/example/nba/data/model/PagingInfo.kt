package com.example.nba.data.model

 
import com.google.gson.annotations.SerializedName

data class PagingInfo(
     @SerializedName("total_pages")
    val totalPages: Int,
     @SerializedName("current_page")
    val currentPage: Int,
     @SerializedName("next_page")
    val nextPage: Int? = null,
     @SerializedName("per_page")
    val perPage: Int,
     @SerializedName("total_count")
    val totalCount: Int,
)
