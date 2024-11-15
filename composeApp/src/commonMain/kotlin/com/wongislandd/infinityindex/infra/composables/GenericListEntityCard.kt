package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.util.EntityModel

@Composable
fun GenericEntityCard(entity: EntityModel, modifier: Modifier = Modifier) {
    val navController = LocalNavHostController.current
    Card(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .height(460.dp)
            .clickable {
                navController.navigate(entity.navContext.navRoute)
            },
        elevation = 8.dp
    ) {
        Column {
            MarvelImage(
                image = entity.image,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.height(300.dp).background(Color.Black)
            )
            EntityCardTitlePlate(entity.displayName)
        }
    }
}

@Composable
private fun EntityCardTitlePlate(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            overflow = TextOverflow.Ellipsis
        )
        subtitle?.also {
            Text(
                text = it,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}