package com.example.ghilbiapp.view.moviedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ghilbiapp.R
import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.ui.theme.Blue
import com.example.ghilbiapp.ui.theme.LightOrange
import com.example.ghilbiapp.utils.Utils.toFormattedDuration
import com.example.ghilbiapp.utils.Utils.toFormattedScore
import com.example.ghilbiapp.view.core.CharacterItem
import com.example.ghilbiapp.view.core.FavoriteButton
import com.example.ghilbiapp.view.core.InfoChip
import com.example.ghilbiapp.view.core.MovieBanner
import com.example.ghilbiapp.view.core.MovieDescription

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when(val currentState = uiState) {
            is MovieDetailUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is MovieDetailUiState.Success -> {
                MovieDetailContent(
                    movieDetailData = currentState.movie,
                    modifier = Modifier
                )
            }
            is MovieDetailUiState.Error -> {
                val errorMessage = (uiState as MovieDetailUiState.Error).message
                Text(
                    text = errorMessage,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {}
        }
    }
}

@Composable
private fun MovieDetailContent(
    movieDetailData: MovieDetailModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        MovieBanner(
            movieDetailData,
            Modifier
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoChip(
                icon = painterResource(id = R.drawable.ic_star),
                iconTint = LightOrange,
                text = movieDetailData.rtScore.toFormattedScore()
            )
            InfoChip(
                icon = painterResource(id = R.drawable.ic_clock),
                iconTint = Blue,
                text = movieDetailData.runningTime.toFormattedDuration()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        FavoriteButton(
            onClick = { /* TODO: Guardar en base de datos local / Room */ }
        )
        Spacer(modifier = Modifier.height(24.dp))
        MovieDescription(
            synopsis = movieDetailData.description
        )

        Spacer(modifier = Modifier.height(32.dp))
        CharactersSection(
            charactersState = movieDetailData.charactersState
        )
        Spacer(modifier = Modifier.height(32.dp))
    }

}

@Composable
fun CharactersSection(
    charactersState: CharactersUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_cast),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Main Characters",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        when (charactersState) {
            is CharactersUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is CharactersUiState.Success -> {
                val charactersList = charactersState.characters

                if (charactersList.isEmpty()) {
                    Text(
                        text = "No characters listed for this movie.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(end = 16.dp)
                    ) {
                        items(
                            items = charactersList,
                            key = { it.id }
                        ) { character ->
                            CharacterItem(character = character)
                        }
                    }
                }
            }

            is CharactersUiState.Error -> {
                Text(
                    text = charactersState.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}