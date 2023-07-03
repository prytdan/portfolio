package com.example.task2.kotlinfolder.model

import androidx.annotation.StringRes
import com.example.task2.R

enum class ErrorName(@StringRes val resourceId: Int?) {
    NEGATIVE_NUMBERS_INPUT(R.string.edit_text_negative_numbers_input),
    EMPTY_INPUT(R.string.edit_text_empty_input),
    INVALID_INPUT(R.string.edit_text_invalid_input),
    NO_ERROR(null);
}