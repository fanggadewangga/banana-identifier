package com.fangga.core.data.model.result

import com.fangga.core.data.model.enums.BananaType
import com.fangga.core.data.model.enums.RipenessType

/**
 * **Data Class:** BananaClassificationResult
 *
 * **Purpose:**
 * A data class that represents the result of a banana classification process.
 * It contains the identified type of banana and its ripeness type.
 *
 * **Properties:**
 * - `bananaType`: An enum value of type `BananaType` that represents the identified
 *   type of banana.
 * - `ripenessType`: An enum value of type `RipenessType` that represents the
 *   identified ripeness type of the banana.
 *
 * **Functionality:**
 * - Provides a convenient way to store and access the results of a banana classification process.
 *
 * **Usage:**
 * Use this data class to store and pass around the results of banana
 * classification in application.
 */

data class BananaClassificationResult(
    val bananaType: BananaType,
    val ripenessType: RipenessType
)
