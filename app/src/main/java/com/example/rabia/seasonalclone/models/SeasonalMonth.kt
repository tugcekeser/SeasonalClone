package com.example.rabia.seasonalclone.models

import com.google.gson.annotations.SerializedName

data class SeasonalMonth(

        @SerializedName("January") val january: Boolean?,
        @SerializedName("February") val february: Boolean?,
        @SerializedName("March") val march: Boolean?,
        @SerializedName("April") val april: Boolean?,
        @SerializedName("May") val may: Boolean?,
        @SerializedName("June") val june: Boolean?,
        @SerializedName("July") val july: Boolean?,
        @SerializedName("August") val august: Boolean?,
        @SerializedName("September") val september: Boolean?,
        @SerializedName("October") val october: Boolean?,
        @SerializedName("November") val november: Boolean?,
        @SerializedName("December") val december: Boolean?
)