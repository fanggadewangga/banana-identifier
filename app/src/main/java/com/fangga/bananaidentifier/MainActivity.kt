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
import com.fangga.result.presentation.ResultScreen
import com.fangga.savedresult.presentation.SavedResultScreen
import com.fangga.scan.presentation.ScanScreen
import com.fangga.termscondition.TermsConditionScreen
import com.fangga.tips.presentation.TipsDetailScreen
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
                screenHeight = screenHeight,
                splashScreen = { SplashScreen(screenWidth) },
                onboardScreen = { OnboardScreen(screenHeight) },
                homeScreen = { HomeScreen(screenHeight, screenWidth) },
                tipsDetailScreen = { id ->
                    TipsDetailScreen(
                        tipsId = id,
                        screenWidth = screenWidth,
                        screenHeight = screenHeight
                    )
                },
                privacyAndPolicyScreen = { PrivacyPolicyScreen(screenWidth, screenHeight) },
                termsAndConditionScreen = { TermsConditionScreen(screenWidth, screenHeight) },
                scanCameraScreen = {
                    ScanScreen(
                        screenWidth = screenWidth,
                        screenHeight = screenHeight
                    )
                },
                scanResultScreen = { ResultScreen(screenWidth = screenWidth) },
                savedResultScreen = { SavedResultScreen(screenHeight) }
            )
        }
    }
}