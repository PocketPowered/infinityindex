package com.wongislandd.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.MarvelLinks
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.models.local.Character

@Composable
fun CharacterDetails(character: Character, modifier: Modifier = Modifier) {
    EntityDetails(character, modifier = modifier) {
        SimpleDetailsSection("Description", character.description)
        MarvelLinks(character.relatedLinks)
    }
}