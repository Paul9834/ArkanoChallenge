package com.paul9834.arkano.repository

import com.paul9834.arkano.data.remote.CharacterApi
import com.paul9834.arkano.data.remote.CharacterDto
import com.paul9834.arkano.data.remote.CharacterResponse
import com.paul9834.arkano.data.remote.InfoDto
import com.paul9834.arkano.data.repository.CharacterRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterRepositoryImplTest {

    private val api: CharacterApi = mockk()
    private val repository = CharacterRepositoryImpl(api)

    private val fakeResponse = CharacterResponse(
        info = InfoDto(count = 2, pages = 1, next = null, prev = null),
        results = listOf(
            CharacterDto(id = 1, name = "Rick Sanchez", status = "Alive", image = "url1"),
            CharacterDto(id = 2, name = "Morty Smith", status = "Alive", image = "url2")
        )
    )

    @Test
    fun `getCharacters maps DTO to domain correctly`() = runTest {
        coEvery { api.getCharacters() } returns fakeResponse

        val result = repository.getCharacters()

        assertEquals(2, result.size)
        assertEquals("Rick Sanchez", result[0].name)
        assertEquals("url1", result[0].imageUrl)
    }

    @Test
    fun `getCharacters returns empty list when results are empty`() = runTest {
        coEvery { api.getCharacters() } returns fakeResponse.copy(results = emptyList())

        val result = repository.getCharacters()

        assertEquals(0, result.size)
    }
}
