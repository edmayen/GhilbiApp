package com.example.ghilbiapp.domain.usecases

import com.example.ghilbiapp.domain.model.CharacterModel
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.utils.Resource
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: GhibliMovieRepository
) {
    suspend operator fun invoke(charactersUrls: List<String>): Resource<List<CharacterModel>> =
        repository.fetchMovieCharacters(charactersUrls)
}