package com.example.ghilbiapp.view.moviedetail

import com.example.ghilbiapp.domain.model.CharacterModel

sealed interface CharactersUiState {
    object Loading : CharactersUiState
    data class Success(val characters: List<CharacterModel>) : CharactersUiState
    data class Error(val message: String) : CharactersUiState
}