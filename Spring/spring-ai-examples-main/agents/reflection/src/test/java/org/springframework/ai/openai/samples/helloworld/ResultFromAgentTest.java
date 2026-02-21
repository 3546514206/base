package org.springframework.ai.openai.samples.helloworld;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultFromAgentTest {

    @Test
    void testMergeSortWithRandomArray() {
        int[] array = {12, 11, 13, 5, 6, 7};
        int[] expected = {5, 6, 7, 11, 12, 13};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testMergeSortWithAllSameElements() {
        int[] array = {5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testMergeSortWithEmptyArray() {
        int[] array = {};
        int[] expected = {};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testMergeSortWithNegativeNumbers() {
        int[] array = {-1, -3, -2, -5, -4};
        int[] expected = {-5, -4, -3, -2, -1};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testMergeSortWithAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testMergeSortWithReverseSortedArray() {
        int[] array = {9, 7, 5, 3, 1, 0};
        int[] expected = {0, 1, 3, 5, 7, 9};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testMergeSortWithNullArray() {
        int[] array = null;

        // Should not throw an exception
        assertDoesNotThrow(() -> MergeSort.mergeSort(array));
    }

    @Test
    void testMergeSortWithSingleElement() {
        int[] array = {1};
        int[] expected = {1};

        MergeSort.mergeSort(array);
        assertArrayEquals(expected, array);
    }
}