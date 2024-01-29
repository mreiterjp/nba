package com.example.nba.presentation.component

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * A sealed class that represents a UI text.
 *
 * This class can be used to represent text that can either be a dynamic string or a string resource.
 */
sealed class UiText {
    /**
     * Represents a dynamic string.
     *
     * A dynamic string is a string that is directly provided as a string value.
     */
    class DynamicString(val value: String) : UiText()

    /**
     * Represents a string resource.
     *
     * A string resource is a string that is defined in the app's resources.
     */
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : UiText()

    /**
     * Returns the text as a string.
     *
     * This function converts the UI text to a string representation.
     *
     * @return The string representation of the UI text.
     */
    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    /**
     * Returns the text as a string, using the given context to resolve the string resource.
     *
     * @param context The context to use for resolving the string resource.
     * @return The string representation of the UI text.
     */
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
