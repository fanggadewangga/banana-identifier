package com.fangga.bananaidentifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalConfiguration
import com.fangga.about.presentation.PrivacyPolicyScreen
import com.fangga.features.home.presentation.HomeScreen
import com.fangga.features.onboard.presentation.OnboardScreen
import com.fangga.features.splash.presentation.SplashScreen
import com.fangga.navigation.AppNavigation
import com.fangga.navigation.Navigator
import com.fangga.termscondition.TermsConditionScreen
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
            val screenWidth = LocalConfiguration.current.screenWidthDp
            val screenHeight = LocalConfiguration.current.screenHeightDp
            AppNavigation(
                navigator = navigator,
                splashScreen = { SplashScreen(screenWidth) },
                onboardScreen = { OnboardScreen(screenHeight) },
                homeScreen = { HomeScreen(screenHeight, screenWidth) },
                tipsDetailScreen = { /*TODO*/ },
                privacyAndPolicyScreen = { PrivacyPolicyScreen(screenWidth, screenHeight) },
                termsAndConditionScreen = { TermsConditionScreen(screenWidth, screenHeight) },
                scanCameraScreen = { /*TODO*/ },
                scanResultScreen = { /*TODO*/ }) {
            }
        }
    }
}