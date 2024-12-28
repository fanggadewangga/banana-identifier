package com.fangga.core.data.model.enums

/**
 * **Enum Class:** RipenessType
 *
 * **Purpose:**
 * An enum class that represents different types of ripeness for a fruit,
 * specifically bananas. Each enum constant has an associated description string
 * that provides a user-friendly name for the ripeness type.
 *
 * **Enum Constants:**
 * - `ALAMI`: Represents the "Matang Alami" (Naturally Ripened) ripeness type.
 * - `KIMIA`: Represents the "Matang Kimia" (Chemically Ripened) ripeness type.
 *
 * **Properties:**
 * - `description`: A string that provides a user-friendly name for the ripeness type.
 *
 * **Functionality:**
 * - Provides a type-safe way to represent different types of ripeness.
 * - Allows for easy access to the description of each ripeness type.
 *
 * **Usage:**
 * Use this enum class to represent different types of ripeness in your application.
 */

enum class RipenessType(val description: String) {
    ALAMI("Matang Alami"),
    KIMIA("Matang Kimia")
}