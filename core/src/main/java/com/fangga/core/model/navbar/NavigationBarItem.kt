package com.fangga.core.model.navbar

import androidx.annotation.DrawableRes
import com.fangga.core.resource.homeSelectedIcon
import com.fangga.core.resource.homeUnselectedIcon
import com.fangga.core.resource.savedSelectedIcon
import com.fangga.core.resource.savedUnselectedIcon

sealed class NavigationBarItem(
    val route: String,
    val title: String?,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    data object Home : NavigationBarItem(
        route = "home",
        title = "Beranda",
        unselectedIcon = homeUnselectedIcon,
        selectedIcon = homeSelectedIcon
    )

    data object SavedResult : NavigationBarItem(
        route = "saved_result",
        title = "Tersimpan",
        unselectedIcon = savedUnselectedIcon,
        selectedIcon = savedSelectedIcon
    )
}