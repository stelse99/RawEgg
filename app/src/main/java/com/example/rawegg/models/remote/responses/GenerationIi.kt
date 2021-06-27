package com.example.rawegg.models.remote.responses
import com.google.gson.annotations.SerializedName

data class GenerationIi(
    val crystal: Crystal,
    val gold: Gold,
    val silver: Silver
)