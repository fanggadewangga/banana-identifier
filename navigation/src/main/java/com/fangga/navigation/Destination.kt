package com.fangga.navigation

import com.fangga.navigation.screens.Home
import com.fangga.navigation.screens.Onboard
import com.fangga.navigation.screens.PrivacyAndPolicy
import com.fangga.navigation.screens.SavedResult
import com.fangga.navigation.screens.ScanCamera
import com.fangga.navigation.screens.ScanResult
import com.fangga.navigation.screens.Splash
import com.fangga.navigation.screens.TermsAndCondition
import com.fangga.navigation.screens.TipsDetail

/**
 * **Object:** Destination
 *
 * **Purpose:**
 * An object that holds the different destinations (routes) for the application's
 * navigation. It provides a central place to access and manage all the
 * navigation destinations.
 *
 * **Functionality:**
 * - Holds instances of different destination classes, such as `Splash`,
 *   `Onboard`, `Home`, `TipsDetail`, `PrivacyAndPolicy`, `TermsAndCondition`,
 *   `ScanCamera`, `ScanResult`, and `SavedResult`.
 * - Provides a convenient way to access the routes for each destination.
 *
 * **Usage:**
 * Use this object to access the different navigation destinations in your
 * application.
 */

object Destination {
    val splash = Splash
    val onboard = Onboard
    val home = Home
    val tipsDetail = TipsDetail
    val privacyAndPolicy = PrivacyAndPolicy
    val termsAndCondition = TermsAndCondition
    val scanCamera = ScanCamera
    val scanResult = ScanResult
    val savedResult = SavedResult
}