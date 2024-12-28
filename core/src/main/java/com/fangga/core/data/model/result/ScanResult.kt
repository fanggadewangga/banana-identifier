package com.fangga.core.data.model.result

import android.net.Uri

/**
 * **Data Class:** ScanResult
 *
 * **Purpose:**
 * A data class that represents the result of a scan operation. It contains
 * information about the scanned item, including its ID, banana type, ripeness
 * type, image URI, and timestamp.
 *
 * **Properties:**
 * - `resultId`: A string that represents the unique identifier of the scan result.
 * - `bananaType`: A string that represents the type of banana identified in the scan.
 * - `ripenessType`: A string that represents the ripeness type of the banana.
 * - `image`: A `Uri` that represents the location of the image associated with the scan.
 * - `timestamp`: A string that represents the timestamp of the scan.
 *
 * **Functionality:**
 * - Provides a convenient way to store and access the results of a scan operation.
 * - Automatically generates `equals()`, `hashCode()`, and `toString()` methods.
 * - Automatically generates a `copy()` method for creating modified instances.
 *
 * **Usage:**
 * Use this data class to store and pass around the results of scan operations
 * in application.
 */

data class ScanResult(
    var resultId: String,
    val bananaType: String,
    val ripenessType: String,
    val image: Uri,
    val timestamp: String,
)
