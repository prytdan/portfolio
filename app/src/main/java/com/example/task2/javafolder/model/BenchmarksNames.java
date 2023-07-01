package com.example.task2.javafolder.model;

import androidx.annotation.StringRes;

import com.example.task2.R;

public enum BenchmarksNames {
    ADD_BEGIN_ARRAYLIST(R.string.add_begin_arraylist, 0),
    ADD_BEGIN_LINKEDLIST(R.string.add_begin_linkedlist, 1),
    ADD_BEGIN_COPYONWRITEARRAYLIST(R.string.add_begin_copyonwritearraylist, 2),
    ADD_MIDDLE_ARRAYLIST(R.string.add_middle_arraylist, 3),
    ADD_MIDDLE_LINKEDLIST(R.string.add_middle_linkedlist, 4),
    ADD_MIDDLE_COPYONWRITEARRAYLIST(R.string.add_middle_copyonwritearraylist, 5),
    ADD_END_ARRAYLIST(R.string.add_end_arraylist, 6),
    ADD_END_LINKEDLIST(R.string.add_end_linkedlist, 7),
    ADD_END_COPYONWRITEARRAYLIST(R.string.add_end_copyonwritearraylist, 8),
    SEARCH_VALUE_ARRAYLIST(R.string.search_value_arraylist, 9),
    SEARCH_VALUE_LINKEDLIST(R.string.search_value_linkedlist, 10),
    SEARCH_VALUE_COPYONWRITEARRAYLIST(R.string.search_value_copyonwritearraylist, 11),
    REMOVE_BEGIN_ARRAYLIST(R.string.remove_begin_arraylist, 12),
    REMOVE_BEGIN_LINKEDLIST(R.string.remove_begin_linkedlist, 13),
    REMOVE_BEGIN_COPYONWRITEARRAYLIST(R.string.remove_begin_copyonwritearraylist, 14),
    REMOVE_MIDDLE_ARRAYLIST(R.string.remove_middle_arraylist, 15),
    REMOVE_MIDDLE_LINKEDLIST(R.string.remove_middle_linkedlist, 16),
    REMOVE_MIDDLE_COPYONWRITEARRAYLIST(R.string.remove_middle_copyonwritearraylist, 17),
    REMOVE_END_ARRAYLIST(R.string.remove_end_arraylist, 18),
    REMOVE_END_LINKEDLIST(R.string.remove_end_linkedlist, 19),
    REMOVE_END_COPYONWRITEARRAYLIST(R.string.remove_end_copyonwritearraylist, 20),
    ADD_NEW_TREEMAP(R.string.add_new_treemap, 0),
    ADD_NEW_HASHMAP(R.string.add_new_hashmap, 1),
    SEARCH_KEY_TREEMAP(R.string.search_key_treemap, 2),
    SEARCH_KEY_HASHMAP(R.string.search_key_hashmap, 3),
    REMOVE_TREEMAP(R.string.remove_treemap, 4),
    REMOVE_HASHMAP(R.string.remove_hashmap, 5);

    @StringRes
    public final int nameResourceId;
    public final int benchmarksId;

    BenchmarksNames(int nameResourceId, int benchmarksId) {
        this.nameResourceId = nameResourceId;
        this.benchmarksId = benchmarksId;
    }
}
