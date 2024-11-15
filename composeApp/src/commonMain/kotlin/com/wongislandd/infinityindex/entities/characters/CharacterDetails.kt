package com.wongislandd.infinityindex.entities.characters

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.EntityDetails
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun CharacterDetails(character: Character, modifier: Modifier = Modifier) {
    EntityDetails(modifier = modifier) {
        TopLevelEntityDetails(character, modifier)
        SimpleDetailsSection("Description", character.description)
    }
}