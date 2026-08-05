package com.loom

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.loom.ui.navigation.LoomNavigation
import com.loom.ui.theme.LoomColors
import com.loom.ui.theme.LoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SECURITY: Block screenshots/screen recording and hide the app preview
        // in the recent-apps switcher on auth + any TDLib session screens.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContent {
            LoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), // fixed: was missing, background didn't fill screen
                    color = LoomColors.BackgroundDark
                ) {
                    LoomNavigation()
                }
            }
        }
    }
}