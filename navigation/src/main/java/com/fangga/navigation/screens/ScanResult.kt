package com.fangga.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fangga.navigation.utils.DestinationRoute
import com.fangga.navigation.utils.WithArgsScreen

object ScanResult : WithArgsScreen<String> {
    override val route = "scan_result/{isNewResult}/{imageUri}/{bananaType}/{ripenessType}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument("isNewResult") {
                type = NavType.BoolType
            },
            navArgument("imageUri") {
                type = NavType.StringType
            },
            navArgument("bananaType") {
                type = NavType.StringType
            },
            navArgument("ripenessType") {
                type = NavType.StringType
            }
        )

    override fun objectParser(entry: NavBackStackEntry): String {
        val isNewResult = entry.arguments?.getBoolean("isNewResult") ?: false
        val imageUri = entry.arguments?.getString("imageUri") ?: ""
        val bananaType = entry.arguments?.getString("bananaType") ?: ""
        val ripenessType = entry.arguments?.getString("ripenessType") ?: ""
        return "$isNewResult,$imageUri,$bananaType,$ripenessType"
    }

    override fun destination(arg: String): DestinationRoute {
        val args = arg.split(",")
        val isNewResult = args.getOrNull(0).orEmpty()
        val imageUri = args.getOrNull(1).orEmpty()
        val bananaType = args.getOrNull(1).orEmpty()
        val ripenessType = args.getOrNull(2).orEmpty()
        return "$route/$isNewResult/$imageUri/$bananaType/$ripenessType"
    }
}
