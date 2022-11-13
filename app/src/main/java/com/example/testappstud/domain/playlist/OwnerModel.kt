package com.example.testappstud.domain.playlist

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName("display_name")
    var displayName: String = ""
)