package com.fangga.bananaidentifier

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalConfiguration
import com.fangga.about.presentation.PrivacyPolicyScreen
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.utils.mapDateToFormattedString
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
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @RequiresApi(Build.VERSION_CODES.O)
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
                tipsDetailScreen = { id -> TipsDetailScreen(tipsId = id) },
                privacyAndPolicyScreen = { PrivacyPolicyScreen(screenWidth, screenHeight) },
                termsAndConditionScreen = { TermsConditionScreen(screenWidth, screenHeight) },
                scanCameraScreen = {
                    ScanScreen(
                        screenWidth = screenWidth,
                        screenHeight = screenHeight
                    )
                },
                scanResultScreen = { resultId, imageUri, bananaType, ripenessType, isNewResult ->
                    val date = LocalDateTime.now()
                    val result = ScanResult(
                        resultId = resultId,
                        image = Uri.parse(imageUri),
                        bananaType = bananaType,
                        ripenessType = ripenessType,
                        timestamp = mapDateToFormattedString(date = date)
                    )
                    ResultScreen(
                        screenWidth = screenWidth,
                        scanResult = result,
                        isNewResult = isNewResult
                    )
                },
                savedResultScreen = { SavedResultScreen(screenHeight) }
            )
        }
    }
}