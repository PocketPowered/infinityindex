package com.wongislandd.infinityindex

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.di.initializeKoin
import com.wongislandd.infinityindex.themes.MarvelTheme
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.crashlytics.crashlytics
import dev.gitlive.firebase.initialize

class MainActivity : ComponentActivity() {

    init {
        initializeKoin()
        Firebase.initialize(context = this)
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        setContent {
            MarvelTheme {
                InfinityIndexApp(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .safeDrawingPadding()
                )
            }
        }
    }
}