package com.fangga.core.data.model.enums

/**
 * **Enum Class:** ScanExampleType
 *
 * **Purpose:**
 * An enum class that represents the different types of scan examples,
 * specifically whether a scan example is correct or incorrect. This enum is
 * used to indicate the validity of a scan example in a user interface.
 *
 * **Enum Constants:**
 * - `CORRECT`: Represents a correct scan example.
 * - `INCORRECT`: Represents an incorrect scan example.
 *
 * **Functionality:**
 * - Provides a type-safe way to represent different types of scan examples.
 * - Allows for easy handling of different scan example types using `when` expressions.
 *
 * **Usage:**
 * Use this enum class to indicate whether a scan example is correct or incorrect
 * in your application.
 */

enum class ScanExampleType {
    CORRECT,
    INCORRECT,
}