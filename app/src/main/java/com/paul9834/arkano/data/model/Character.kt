package com.paul9834.arkano.data.model

import com.paul9834.arkano.data.remote.CharacterDto

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val imageUrl: String
)

// Mapper: DTO → Domain
fun CharacterDto.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    imageUrl = image
)
