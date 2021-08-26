package com.vb.exquisitiveapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ChallengeListResponse(
    val challenges: List<Challenge>
)

@Entity(tableName = "challenge_table")
data class Challenge (
    @PrimaryKey()
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("fun_fact") val fun_fact : String,
    @SerializedName("show") val show : Boolean,
    @SerializedName("image") val image : String
)