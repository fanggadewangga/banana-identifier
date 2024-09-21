package com.fangga.bananaidentifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fangga.bananaidentifier.ui.theme.BananaIdentifierTheme
import com.fangga.navigation.AppNavigation
import com.fangga.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BananaIdentifierTheme {
                AppNavigation(
                    navigator = navigator,
                    splashScreen = { /*TODO*/ },
                    onboardScreen = { /*TODO*/ },
                    homeScreen = { /*TODO*/ },
                    tipsDetailScreen = { /*TODO*/ },
                    privacyAndPolicyScreen = { /*TODO*/ },
                    termsAndConditionScreen = { /*TODO*/ },
                    scanCameraScreen = { /*TODO*/ },
                    scanResultScreen = { /*TODO*/ }) {
                    
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BananaIdentifierTheme {
        Greeting("Android")
    }
}