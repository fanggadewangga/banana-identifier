package com.fangga.core.data.base

/**
 * **Sealed Class:** Resource<T>
 *
 * **Purpose:**
 * A sealed class that represents the different states of a resource, such as data
 * loading, success, error, or empty. This class is commonly used in data handling
 * scenarios to provide a type-safe way to manage the state of asynchronous operations.
 *
 * **Type Parameter:**
 * - `T`: The type of the data associated with the resource.
 *
 * **Properties:**
 * - `data`: An optional data object of type `T` associated with the resource.
 * - `message`: An optional message string associated with the resource, typically used
 *   to provide error details.
 *
 * **Subclasses:**
 * - `Success<T>`: Represents a successful resource state with data.
 * - `Error<T>`: Represents an error resource state with an optional message and data.
 * - `Loading<T>`: Represents a loading resource state with optional data.
 * - `Empty<T>`: Represents an empty resource state with no data or message.
 *
 * **Functionality:**
 * - Provides a type-safe way to represent different states of a resource.
 * - Allows for easy handling of different states using `when` expressions.
 * - Encapsulates data and error messages within the resource state.
 *
 * **Usage:**
 * Use this sealed class to manage the state of asynchronous operations, such as
 * fetching data from a network or database.
 * */

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Empty<T> : Resource<T>()
}