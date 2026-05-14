package com.paul9834.arkano.viewmodel

import com.paul9834.arkano.data.model.Character

sealed class CharacterUiState {
    object Loading : CharacterUiState()
    data class Success(val characters: List<Character>) : CharacterUiState()
    data class Error(val message: String) : CharacterUiState()
}
