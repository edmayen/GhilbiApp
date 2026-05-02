package com.example.ghilbiapp.view.core

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ghilbiapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    isVisibleNavIcon: Boolean = false,
    onBackClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = { if (isVisibleNavIcon) {
                IconButton(
                    onClick = { onBackClicked() }
                ){
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back),
                        contentDescription = "Perfil del usuario",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CustomTopAppBar(
        title = "Ghibli Theater"
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewWithBackNav() {
    CustomTopAppBar(
        title = "Ghibli Theater",
        isVisibleNavIcon = true,
        onBackClicked = {}
    )
}