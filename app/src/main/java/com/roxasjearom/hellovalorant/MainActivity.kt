package com.roxasjearom.hellovalorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.roxasjearom.hellovalorant.presentation.agents.AgentScreen
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloValoTheme {
                AgentScreen()
            }
        }
    }
}
