package com.paul9834.arkano.data.remote

import retrofit2.http.GET

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}
