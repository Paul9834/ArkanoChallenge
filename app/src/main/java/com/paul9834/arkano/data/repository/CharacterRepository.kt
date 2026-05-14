package com.paul9834.arkano.data.repository

import com.paul9834.arkano.data.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}
