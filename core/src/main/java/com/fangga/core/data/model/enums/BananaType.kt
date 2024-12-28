package com.fangga.core.data.model.enums

/**
 * **Enum Class:** BananaType
 *
 * **Purpose:**
 * An enum class that represents different types of bananas. Each enum constant
 * has an associated description string that provides a user-friendly name for
 * the banana type.
 *
 * **Enum Constants:**
 * - `AMBON`: Represents the "Pisang Ambon" banana type.
 * - `MOROSEBO`: Represents the "Pisang Morosebo" banana type.
 * - `KEPOK`: Represents the "Pisang Kepok" banana type.
 * - `HIJAU`: Represents the "Pisang Hijau" banana type.
 * - `SUSU`: Represents the "Pisang Susu" banana type.
 * - `RAJA`: Represents the "Pisang Raja" banana type.
 *
 * **Properties:**
 * - `description`: A string that provides a user-friendly name for the banana type.
 *
 * **Functionality:**
 * - Provides a type-safe way to represent different types of bananas.
 * - Allows for easy access to the description of each banana type.
 *
 * **Usage:**
 * Use this enum class to represent different types of bananas in your application.
 */

enum class BananaType(val description: String) {
    AMBON("Pisang Ambon"),
    MOROSEBO("Pisang Morosebo"),
    KEPOK("Pisang Kepok"),
    HIJAU("Pisang Hijau"),
    SUSU("Pisang Susu"),
    RAJA("Pisang Raja"),
}