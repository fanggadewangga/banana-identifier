package com.fangga.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fangga.navigation.screens.TipsDetail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    splashScreen: @Composable () -> Unit,
    onboardScreen: @Composable () -> Unit,
    homeScreen: @Composable () -> Unit,
    tipsDetailScreen: @Composable (String) -> Unit,
    privacyAndPolicyScreen: @Composable () -> Unit,
    termsAndConditionScreen: @Composable () -> Unit,
    scanCameraScreen: @Composable () -> Unit,
    scanResultScreen: @Composable () -> Unit, // TODO: Add Scan Result To Argument
    savedResultScreen: @Composable () -> Unit,
) {

    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest { action ->
            when(action) {
                Navigator.Action.Back -> navController.popBackStack()
                is Navigator.Action.Navigate -> {
                    Log.d("Navigate to", action.destination)
                    navController.navigate(
                        route = action.destination,
                        builder = action.navOptions
                    )
                }
            }
        }
    }

    NavHost(navController = navController, startDestination = Destination.onboard.route) {

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

        composable(Destination.scanResult.route) {
            scanResultScreen()
        }

        composable(Destination.savedResult.route) {
            savedResultScreen()
        }
    }
}