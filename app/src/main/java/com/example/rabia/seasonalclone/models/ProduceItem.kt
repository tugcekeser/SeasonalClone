package com.example.rabia.seasonalclone.models

import com.google.gson.annotations.SerializedName

data class ProduceItem(

        @SerializedName("ID") val id: Integer?,
        @SerializedName("Name") val itemName: String?,
        @SerializedName("Type") val itemType: String?,
        @SerializedName("Description") val itemDesc: String?,
        @SerializedName("imgURL") val itemImgUrl: String?
)