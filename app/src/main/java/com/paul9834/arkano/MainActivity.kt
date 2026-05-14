package com.paul9834.arkano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.paul9834.arkano.ui.screen.CharacterListScreen
import com.paul9834.arkano.ui.components.theme.ArkanoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArkanoTheme {
                CharacterListScreen()
            }
        }
    }
}
