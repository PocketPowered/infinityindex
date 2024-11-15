package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.conditionallyChain

@Composable
fun GenericListEntityCard(entity: EntityModel, modifier: Modifier = Modifier) {
    val navController = LocalNavHostController.current
    Card(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .height(375.dp)
            .conditionallyChain(entity.navContext.allowNavigation, Modifier.clickable {
                navController.navigate(entity.navContext.navRoute)
            }),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MarvelImage(
                image = entity.image,
                tint = MaterialTheme.colors.onSurface,
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(300.dp).background(Color.Black)
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
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            overflow = TextOverflow.Ellipsis
        )
        subtitle?.also {
            Text(
                text = it,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}