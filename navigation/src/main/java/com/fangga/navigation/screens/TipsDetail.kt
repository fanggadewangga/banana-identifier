package com.fangga.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fangga.navigation.utils.WithArgsScreen
import com.fangga.navigation.utils.DestinationRoute

object TipsDetail : WithArgsScreen<String> {
    override val route = "tips/{id}"

    override val arguments: List<NamedNavArgument> = listOf(navArgument("id") { type = NavType.StringType })

    override fun objectParser(entry: NavBackStackEntry): String = entry.arguments?.getString("id") ?: ""

    override fun destination(arg: String): DestinationRoute = "tips/$arg"
}