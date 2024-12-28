package com.fangga.core.data.model.navbar

import androidx.annotation.DrawableRes
import com.fangga.core.resource.homeSelectedIcon
import com.fangga.core.resource.homeUnselectedIcon
import com.fangga.core.resource.savedSelectedIcon
import com.fangga.core.resource.savedUnselectedIcon

/**
 * **Sealed Class:** NavigationBarItem
 *
 * **Purpose:**
 * A sealed class that represents the different items in a navigation bar. Each
 * item has a route, an optional title, an unselected icon, and a selected icon.
 * This class is used to define the structure and properties of navigation bar
 * items in a type-safe manner.
 *
 * **Properties:**
 * - `route`: A string that represents the navigation route associated with the item.
 * - `title`: An optional string that represents the title of the navigation item.
 * - `unselectedIcon`: A drawable resource ID for the icon when the item is unselected.
 * - `selectedIcon`: A drawable resource ID for the icon when the item is selected.
 *
 * **Subclasses:**
 * - `Home`: Represents the "Home" navigation item.
 * - `SavedResult`: Represents the "Saved Result" navigation item.
 *
 * **Functionality:**
 * - Provides a type-safe way to represent different navigation bar items.
 * - Encapsulates the properties of each navigation item.
 * - Allows for easy handling of different navigation items using `when` expressions.
 *
 * **Usage:**
 * Use this sealed class to define the items in your navigation bar and to
 * navigate between different screens in your application.
 */

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