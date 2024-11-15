package com.wongislandd.infinityindex.entities.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun CharacterDetails(character: Character, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(character, modifier)
        DetailsSection("Description", character.description)
    }
}