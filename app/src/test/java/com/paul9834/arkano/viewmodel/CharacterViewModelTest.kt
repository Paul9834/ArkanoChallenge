package com.paul9834.arkano.viewmodel

import app.cash.turbine.test
import com.paul9834.arkano.data.model.Character
import com.paul9834.arkano.data.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: CharacterRepository
    private lateinit var viewModel: CharacterViewModel

    private val fakeCharacters = listOf(
        Character(id = 1, name = "Rick Sanchez", status = "Alive", imageUrl = "url1"),
        Character(id = 2, name = "Morty Smith", status = "Alive", imageUrl = "url2")
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadCharacters succeeds, uiState emits Success`() = runTest {
        coEvery { repository.getCharacters() } returns fakeCharacters

        viewModel = CharacterViewModel(repository)

        viewModel.uiState.test {
            // primer estado: Loading
            assertTrue(awaitItem() is CharacterUiState.Loading)
            // segundo estado: Success
            val success = awaitItem() as CharacterUiState.Success
            assertEquals(2, success.characters.size)
            assertEquals("Rick Sanchez", success.characters[0].name)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when loadCharacters fails, uiState emits Error`() = runTest {
        coEvery { repository.getCharacters() } throws Exception("Network error")

        viewModel = CharacterViewModel(repository)

        viewModel.uiState.test {
            assertTrue(awaitItem() is CharacterUiState.Loading)
            val error = awaitItem() as CharacterUiState.Error
            assertEquals("Network error", error.message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
