package com.fangga.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppNavigationBar
import com.fangga.core.data.model.navbar.NavigationBarItem
import com.fangga.core.resource.cameraShutterIcon
import com.fangga.core.resource.greenPrimary
import com.fangga.core.utils.singleClick
import com.fangga.navigation.screens.ScanResult
import com.fangga.navigation.screens.TipsDetail
import kotlinx.coroutines.flow.collectLatest

/**
 * **Function:** AppNavigation
 *
 * **Purpose:**
 * A composable function that sets up the navigation for the entire application.
 * It uses the Jetpack Compose Navigation library to manage navigation between
 * different screens.
 *
 * **Parameters:**
 * - `navigator`: An instance of `Navigator` for handling navigation actions.
 * - `screenHeight`: The height of the screen in pixels, used for calculating
 *   the height of the bottom navigation bar.
 * - `splashScreen`: A composable function for the splash screen.
 * - `onboardScreen`: A composable function for the onboarding screen.
 * - `homeScreen`: A composable function for the home screen.
 * - `tipsDetailScreen`: A composable function for the tips detail screen,
 *   which takes a `String` representing the tips ID as a parameter.
 * - `privacyAndPolicyScreen`: A composable function for the privacy and
 *   policy screen.
 * - `termsAndConditionScreen`: A composable function for the terms and
 *   conditions screen.
 * - `scanCameraScreen`: A composable function for the scan camera screen.
 * - `scanResultScreen`: A composable function for the scan result screen,
 *   which takes a `String` representing the result ID, a `String` representing
 *   the image URI, a `String` representing the banana type, a `String`
 *   representing the ripeness type, and a `Boolean` indicating if it's a new
 *   result as parameters.
 * - `savedResultScreen`: A composable function for the saved result screen.
 *
 * **Functionality:**
 * - Sets up a `NavHost` to manage navigation between different screens.
 * - Uses a `Scaffold` to provide a basic layout structure, including a
 *   bottom navigation bar and a floating action button.
 * - Uses a `LaunchedEffect` to collect navigation actions from the
 *   `Navigator` and perform the corresponding navigation using the
 *   `NavController`.
 * - Defines the navigation graph using `composable` functions, associating
 *   each route with a composable screen.
 * - Handles navigation to screens with arguments by parsing the arguments
 *   from the navigation back stack entry.
 *
 * **Usage:**
 * Use this composable function as the root of your application's UI to set
 * up navigation.
 */


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavigation(
    navigator: Navigator,
    screenHeight: Int,
    splashScreen: @Composable () -> Unit,
    onboardScreen: @Composable () -> Unit,
    homeScreen: @Composable () -> Unit,
    tipsDetailScreen: @Composable (String) -> Unit,
    privacyAndPolicyScreen: @Composable () -> Unit,
    termsAndConditionScreen: @Composable () -> Unit,
    scanCameraScreen: @Composable () -> Unit,
    scanResultScreen: @Composable (String, String, String, String, Boolean) -> Unit,
    savedResultScreen: @Composable () -> Unit,
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val mainScreens = listOf(NavigationBarItem.Home, NavigationBarItem.SavedResult)
    val isShowNavbar = mainScreens.any { it.route == currentDestination?.route }

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest { action ->
            when (action) {
                Navigator.Action.Back -> navController.popBackStack()
                is Navigator.Action.Navigate -> navController.navigate(
                    route = action.destination,
                    builder = action.navOptions
                )
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (isShowNavbar)
                BottomAppBar(
                    backgroundColor = Color.White,
                    contentPadding = PaddingValues(0.dp),
                    cutoutShape = CircleShape,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .height((screenHeight * 0.08).dp)
                ) {
                    AppNavigationBar(
                        isShowNavbar = true,
                        items = mainScreens,
                        currentDestination = currentDestination,
                        onClick = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.id) {
                                    saveState = true
                                    inclusive = true
                                }
                                restoreState = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((screenHeight * 0.08).dp)
                    )
                }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (isShowNavbar) {
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = greenPrimary,
                    modifier = Modifier.size(64.dp),
                    onClick = singleClick { navController.navigate(Destination.scanCamera.route) }
                ) {
                    AppImage(
                        imageUrl = cameraShutterIcon,
                        contentDescription = "Camera shutter icon",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.splash.route,
        ) {

            composable(Destination.splash.route) {
                splashScreen()
            }

            composable(Destination.onboard.route) {
                onboardScreen()
            }

            composable(Destination.home.route) {
                homeScreen()
            }

            composable(Destination.tipsDetail.route, Destination.tipsDetail.arguments) {
                val tipsId = TipsDetail.objectParser(it)
                tipsDetailScreen(tipsId)
            }

            composable(Destination.privacyAndPolicy.route) {
                privacyAndPolicyScreen()
            }

            composable(Destination.termsAndCondition.route) {
                termsAndConditionScreen()
            }

            composable(Destination.scanCamera.route) {
                scanCameraScreen()
            }

            composable(Destination.scanResult.route, Destination.scanResult.arguments) {
                val scanResultArgs = ScanResult.objectParser(it)
                val (
                    isNewResult,
                    resultId,
                    encodedImageUri,
                    bananaType,
                    ripenessType
                ) = scanResultArgs.split(",")
                val imageUri = Uri.decode(encodedImageUri)
                scanResultScreen(
                    resultId,
                    imageUri,
                    bananaType,
                    ripenessType,
                    isNewResult.toBoolean()
                )
            }

            composable(Destination.savedResult.route) {
                savedResultScreen()
            }
        }
    }
}