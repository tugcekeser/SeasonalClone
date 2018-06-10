package com.example.rabia.seasonalclone.models

import com.google.gson.annotations.SerializedName

data class SeasonalCalifornia(

        @SerializedName("ID") val id: Integer?,
        @SerializedName("Name") val itemName: String?,
        @SerializedName("months") val months: SeasonalMonth?
)