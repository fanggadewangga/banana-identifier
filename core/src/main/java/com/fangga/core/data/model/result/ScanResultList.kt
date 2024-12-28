package com.fangga.core.data.model.result

import android.graphics.Bitmap

/**
 * **Data Class:** ScanResultList
 *
 * **Purpose:**
 * A data class that represents a scan result item for display in a list. It
 * contains information about the scanned item, including its image, ID, banana
 * type, ripeness type, action reveal state, and timestamp.
 *
 * **Properties:**
 * - `image`: A `Bitmap` that represents the image associated with the scan result.
 * - `resultId`: A string that represents the unique identifier of the scan result.
 * - `bananaType`: A string that represents the type of banana identified in the scan.
 * - `ripenessType`: A string that represents the ripeness type of the banana.
 * - `isActionRevealed`: A boolean that indicates whether the action associated
 *   with the item is revealed (e.g., for swipe actions).
 * - `timestamp`: A string that represents the timestamp of the scan.
 *
 * **Functionality:**
 * - Provides a convenient way to store and access the results of a scan operation
 *   for display in a list.
 * - Automatically generates `equals()`, `hashCode()`, and `toString()` methods.
 * - Automatically generates a `copy()` method for creating modified instances.
 *
 * **Usage:**
 * Use this data class to store and pass around the results of scan operations
 * when displaying them in a list or similar UI element.
 */

data class ScanResultList(
    val image: Bitmap,
    val resultId: String,
    val bananaType: String,
    val ripenessType: String,
    var isActionRevealed: Boolean = false,
    val timestamp: String,
)
