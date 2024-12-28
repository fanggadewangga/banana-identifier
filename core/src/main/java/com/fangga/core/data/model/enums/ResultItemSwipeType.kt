package com.fangga.core.data.model.enums

/**
 * **Enum Class:** ResultItemSwipeType
 *
 * **Purpose:**
 * An enum class that represents the different types of swipe actions that can be
 * performed on a result item. This enum is used to determine the action to be
 * performed when a user swipes a result item, such as saving or deleting the item.
 *
 * **Enum Constants:**
 * - `SAVE`: Represents the action of saving the result item.
 * - `DELETE`: Represents the action of deleting the result item.
 *
 * **Functionality:**
 * - Provides a type-safe way to represent different types of swipe actions.
 * - Allows for easy handling of different swipe actions using `when` expressions.
 *
 * **Usage:**
 * Use this enum class to determine the action to be performed when a user swipes
 * a result item in your application.
 */

enum class ResultItemSwipeType {
    SAVE,
    DELETE
}