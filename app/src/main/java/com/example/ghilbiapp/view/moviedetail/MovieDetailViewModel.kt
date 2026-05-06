package com.example.ghilbiapp.view.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.ghilbiapp.domain.usecases.GetCharactersUseCase
import com.example.ghilbiapp.domain.usecases.MovieDetailUseCase
import com.example.ghilbiapp.domain.usecases.ObserveFavoriteStatusUseCase
import com.example.ghilbiapp.utils.Resource
import com.example.ghilbiapp.view.navigation.MovieDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieDetailUseCase: MovieDetailUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val observeFavoriteStatusUseCase: ObserveFavoriteStatusUseCase
): ViewModel() {
    private val routeData = savedStateHandle.toRoute<MovieDetailRoute>()
    private val movieId = routeData.movieId

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
        observeFavoriteStatus()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { MovieDetailUiState.Loading }

            when (val movieResult = movieDetailUseCase(movieId)) {
                is Resource.Success -> {
                    val movie = movieResult.data!!
                    _uiState.update { MovieDetailUiState.Success(movie) }
                    loadCharacters(movie.characterUrls)
                }
                is Resource.Error -> {
                    _uiState.update { MovieDetailUiState.Error(movieResult.message ?: "Error") }
                }
            }
        }
    }

    private fun loadCharacters(urls: List<String>) {
        viewModelScope.launch {

            val startingState = _uiState.value
            if (startingState is MovieDetailUiState.Success) {
                _uiState.update {
                    startingState.copy(
                        movie = startingState.movie.copy(
                            charactersState = CharactersUiState.Loading
                        )
                    )
                }
            }

            when (val charsResult = getCharactersUseCase(urls)) {
                is Resource.Success -> {
                    val currentState = _uiState.value
                    if (currentState is MovieDetailUiState.Success) {
                        _uiState.update {
                            currentState.copy(
                                movie = currentState.movie.copy(
                                    charactersState = CharactersUiState.Success(charsResult.data!!)
                                )
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    val currentState = _uiState.value
                    if (currentState is MovieDetailUiState.Success) {
                        _uiState.update {
                            currentState.copy(
                                movie = currentState.movie.copy(
                                    charactersState = CharactersUiState.Error(charsResult.message ?: "Error loading cast")
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun observeFavoriteStatus() {
        viewModelScope.launch {
            // Room nos permite devolver un Flow, así que reaccionará en tiempo real
            // si el usuario agrega o quita la película de favoritos.
//            observeFavoriteStatusUseCase(movieId).collect { isFavorite ->
//                val currentState = _uiState.value
//                if (currentState is MovieDetailUiState.Success) {
//                    _uiState.update {
//                        currentState.copy(
//                            movie = currentState.movie.copy(isFavorite = isFavorite)
//                        )
//                    }
//                }
//            }
        }
    }

}