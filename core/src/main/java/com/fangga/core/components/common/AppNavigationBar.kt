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
import com.fangga.core.model.navbar.NavigationBarItem
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.h12SemiBold
import com.fangga.core.utils.singleClick

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