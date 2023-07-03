package com.example.task2.viewviewmodel.fragments.parentfragment;

import static org.junit.Assert.*;

import com.example.task2.javafolder.model.ErrorName;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.collections.CollectionsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SecondParentViewModelTest {
    private ParentViewModel parentViewModel;
    private String input;
    private ErrorName expectedErrorName;

    public SecondParentViewModelTest(String input, ErrorName expectedErrorName) {
        this.input = input;
        this.expectedErrorName = expectedErrorName;
    }

    @Before
    public void setup() {
        parentViewModel = new CollectionsViewModel();
    }

    @Parameterized.Parameters
    public static Object[] testValues() {
        return new Object[] {
                new Object[]{"ABC", ErrorName.INVALID_INPUT},
                new Object[]{"1234ABC", ErrorName.INVALID_INPUT},
                new Object[]{"1234567a", ErrorName.INVALID_INPUT},
                new Object[]{"1234567123456", ErrorName.INVALID_INPUT},
                new Object[]{" ", ErrorName.INVALID_INPUT},
                new Object[]{"-123", ErrorName.NEGATIVE_NUMBERS_INPUT},
                new Object[]{"0", ErrorName.NEGATIVE_NUMBERS_INPUT},
                new Object[]{"", ErrorName.EMPTY_INPUT},
                new Object[]{"1", ErrorName.NO_ERROR}
        };
    }

    @Test
    public void testValidationOfErrorInUsersInput() {
        ErrorName receivedDuringTestErrorName = parentViewModel.validateUsersInput(input);
        assertEquals(expectedErrorName, receivedDuringTestErrorName);
    }
}