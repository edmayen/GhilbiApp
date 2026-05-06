package com.example.ghilbiapp.view.core

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ghilbiapp.R
import com.example.ghilbiapp.domain.model.CharacterModel
import com.example.ghilbiapp.ui.theme.Blue

@Composable
fun CharacterItem(
    character: CharacterModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        )
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            InfoText(
                icon = painterResource(R.drawable.ic_person_outline),
                iconTint = MaterialTheme.colorScheme.primary,
                text = character.gender
            )
            Spacer(Modifier.width(16.dp))
            InfoText(
                icon = painterResource(R.drawable.ic_calendar),
                iconTint = MaterialTheme.colorScheme.primary,
                text = character.age
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            InfoText(
                icon = painterResource(R.drawable.ic_eye),
                iconTint = MaterialTheme.colorScheme.primary,
                text = character.eyeColor
            )
            Spacer(Modifier.width(16.dp))
            InfoText(
                icon = painterResource(R.drawable.ic_hair_fill),
                iconTint = MaterialTheme.colorScheme.primary,
                text = character.hairColor
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val aux = CharacterModel(
        id = "0",
        name = "Ashitaka",
        gender = "male",
        eyeColor = "brown",
        hairColor = "brown",
        age = "25"
    )

    CharacterItem(
        character = aux,
        modifier = Modifier
    )
}