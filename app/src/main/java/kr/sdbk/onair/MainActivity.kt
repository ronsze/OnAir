package kr.sdbk.onair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import kr.sdbk.onair.ui.theme.OnAirTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnAirTheme {
                OnAirApp(
                    isOnboardingComplete = false,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}