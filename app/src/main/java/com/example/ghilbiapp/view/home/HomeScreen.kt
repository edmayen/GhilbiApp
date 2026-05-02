package com.example.ghilbiapp.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ghilbiapp.view.core.BottomNavigationBar
import com.example.ghilbiapp.view.core.CustomTopAppBar

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Ghibli Theater",
                isVisibleNavIcon = true,
                onBackClicked = {}
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "library",
                onNavigate = { route ->
                    
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        content = { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) { }
        },
    )
}