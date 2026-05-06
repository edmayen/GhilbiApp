package com.example.ghilbiapp.data.api.response

import com.example.ghilbiapp.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val age: String,
    @SerialName("eye_color") val eyeColor: String,
    val gender: String,
    @SerialName("hair_color") val hairColor: String,
    val id: String,
    val name: String
)

fun CharacterResponse.toDomain(): CharacterModel = CharacterModel(
    id = id,
    name = name,
    gender = gender,
    age = age,
    eyeColor = eyeColor,
    hairColor = hairColor
)