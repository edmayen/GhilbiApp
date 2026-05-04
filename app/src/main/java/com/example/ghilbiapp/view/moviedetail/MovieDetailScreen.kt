package com.example.ghilbiapp.view.moviedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
    }

}