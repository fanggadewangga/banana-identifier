package com.fangga.bananaidentifier

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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

/**
 * **Class:** MainActivity
 *
 * **Purpose:**
 * The main entry point of the application, responsible for setting up the UI and navigation.
 *
 * **Dependencies:**
 * - `@AndroidEntryPoint`: Enables Dagger-Hilt for dependency injection.
 * - `Navigator`: Injected interface for handling screen navigation.
 *
 * **Functionality:**
 * - Sets the screen orientation to portrait mode.
 * - Uses Jetpack Compose to define the UI.
 * - Retrieves screen dimensions and context.
 * - Initializes the `AppNavigation` composable, which manages the app's navigation graph.
 *
 * **Properties:**
 * - `navigator`: Injected `Navigator` instance for screen transitions.
 *
 * **Key Actions:**
 * - `enableEdgeToEdge()`: Enables drawing behind system bars.
 * - `setContent()`: Sets the root composable content.
 * - `LaunchedEffect(Unit)`: Sets the screen orientation to portrait.
 * - `AppNavigation(...)`: Configures the navigation graph with composable functions for each screen.
 *
 * **Navigation Setup:**
 * The `AppNavigation` composable is configured with lambdas for each screen, including:
 *   - `splashScreen`: The initial splash screen.
 *   - `onboardScreen`: The onboarding flow.
 *   - `homeScreen`: The main home screen.
 *   - `tipsDetailScreen`: Screen for displaying detailed tips, taking a `tipsId`.
 *   - `privacyAndPolicyScreen`: Screen for privacy policy.
 *   - `termsAndConditionScreen`: Screen for terms and conditions.
 *   - `scanCameraScreen`: Screen for the camera scanner.
 *   - `scanResultScreen`: Screen for displaying scan results, taking parameters like `resultId`, `imageUri`, `bananaType`, `ripenessType`, and `isNewResult`.
 *   - `savedResultScreen`: Screen for displaying saved scan results.
 *
 * **Additional Notes:**
 * - `@SuppressLint("SourceLockedOrientationActivity")`: Suppresses lint warning for locking screen orientation.
 * - `@RequiresApi(Build.VERSION_CODES.O)`: Requires Android 8.0 (Oreo) or higher due to `LocalDateTime`.
 * - The `scanResultScreen` creates a `ScanResult` data class instance using the provided parameters.
 * - The `mapDateToFormattedString` function is assumed to be defined elsewhere.
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @SuppressLint("SourceLockedOrientationActivity")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val screenWidth = LocalConfiguration.current.screenWidthDp
            val screenHeight = LocalConfiguration.current.screenHeightDp
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                val activity = context as ComponentActivity
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

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