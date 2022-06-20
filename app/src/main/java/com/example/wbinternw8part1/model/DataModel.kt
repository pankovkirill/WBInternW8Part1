package com.example.wbinternw8part1.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class DataModel(
    val id: Int,
    val localized_name: String,
    val img: String,
    val attack_type: String,
    val base_health: Int,
    val base_mana: Int,
    val base_mr: Int,
    val base_attack_min: Int,
    val base_attack_max: Int
) : Parcelable