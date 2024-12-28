package com.fangga.core.components.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.fangga.core.data.model.navbar.NavigationBarItem
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.h12SemiBold
import com.fangga.core.utils.singleClick

/**
 * **Composable Function:** AppNavigationBar
 *
 * **Purpose:**
 * A custom bottom navigation bar composable that displays a list of navigation items.
 * This function provides a way to create a bottom navigation bar with customizable
 * styling and behavior.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the navigation bar.
 * - `onClick`: A lambda function that is called when a navigation item is clicked.
 *   It receives the route of the clicked item as a parameter.
 * - `isShowNavbar`: A boolean indicating whether the navigation bar should be displayed.
 * - `items`: A list of `NavigationBarItem` objects representing the navigation items.
 * - `currentDestination`: The current navigation destination, used to determine which
 *   item is currently selected.
 *
 * **Functionality:**
 * - Checks if the navigation bar should be displayed based on the `isShowNavbar` parameter.
 * - Uses the `BottomNavigation` composable to create the navigation bar.
 * - Iterates through the `items` list and creates a `BottomNavigationItem` for each item.
 * - Determines if an item is selected based on the `currentDestination`.
 * - Displays the selected and unselected icons for each item using `Icon` composables.
 * - Displays the title for each item using the `AppText` composable.
 * - Applies styling to the selected and unselected items.
 *
 * **Usage:**
 * Use this composable function to create a custom bottom navigation bar in your
 * Jetpack Compose UI.
 **/

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    isShowNavbar: Boolean,
    items: List<NavigationBarItem>,
    currentDestination: NavDestination?
) {
    if (isShowNavbar) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = modifier
        ) {
            items.forEach { item ->

                val isSelected = currentDestination?.route == item.route

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = singleClick {
                        onClick(item.route)
                        return@singleClick
                    },
                    icon = {
                        if (isSelected)
                            Icon(
                                painter = painterResource(item.selectedIcon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        else
                            Icon(
                                painter = painterResource(id = item.unselectedIcon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                    },
                    label = {
                        val textColor = if (isSelected) greenPrimary else Color.LightGray
                        val textStyle = if (isSelected) h12SemiBold else bodyText14Regular

                        if (item.title != null)
                            AppText(text = item.title, textStyle = textStyle, color = textColor)
                    },
                    unselectedContentColor = Color.LightGray,
                    selectedContentColor = greenPrimary
                )
            }
        }
    }
}