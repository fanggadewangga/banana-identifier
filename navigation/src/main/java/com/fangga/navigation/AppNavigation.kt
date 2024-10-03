package com.fangga.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppNavigationBar
import com.fangga.core.model.navbar.NavigationBarItem
import com.fangga.core.resource.cameraShutterIcon
import com.fangga.core.resource.greenPrimary
import com.fangga.core.utils.singleClick
import com.fangga.navigation.screens.TipsDetail
import kotlinx.coroutines.flow.collectLatest

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
    scanResultScreen: @Composable () -> Unit, // TODO: Add Scan Result To Argument
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
            AppNavigationBar(
                isShowNavbar = isShowNavbar,
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
                modifier = Modifier.height((screenHeight * 0.08).dp)
            )
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

            composable(Destination.scanResult.route) {
                scanResultScreen()
            }

            composable(Destination.savedResult.route) {
                savedResultScreen()
            }
        }
    }


}