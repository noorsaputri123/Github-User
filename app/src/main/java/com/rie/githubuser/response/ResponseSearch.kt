package com.rie.githubuser.response

import com.google.gson.annotations.SerializedName

data class ResponseSearch(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<ItemsSearch?>? = null
)

data class ItemsSearch(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    )