package com.example.task2.javafolder.model;

import androidx.annotation.StringRes;

import com.example.task2.R;

public enum ErrorName {
    NEGATIVE_NUMBERS_INPUT(R.string.edit_text_negative_numbers_input),
    EMPTY_INPUT(R.string.edit_text_empty_input),
    INVALID_INPUT(R.string.edit_text_invalid_input),
    NO_ERROR(0);

    @StringRes
    public final int nameResourceId;

    ErrorName(int nameResourceId) {
        this.nameResourceId = nameResourceId;
    }
}
