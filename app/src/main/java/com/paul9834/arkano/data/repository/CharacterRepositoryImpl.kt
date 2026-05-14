package com.paul9834.arkano.data.repository

import com.paul9834.arkano.data.model.Character
import com.paul9834.arkano.data.remote.CharacterApi
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi
) : CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
        return api.getCharacters().results.map { dto ->
            Character(
                id = dto.id,
                name = dto.name,
                status = dto.status,
                imageUrl = dto.image
            )
        }
    }
}
