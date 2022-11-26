package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class problem3Test {

    @Test
    void firstTestToFlattenMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

        int[] result = problem3.flattenMatrix(matrix);

        int[] necessary = {1, 4, 7, 2, 5, 8, 3, 6, 9};
        assertArrayEquals(necessary, result);


    }

    @Test
    void secondTestToFlattenMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 2, 3} };

        int[] result = problem3.flattenMatrix(matrix);

        int[] necessary = {1, 4, 7, 1, 2, 5, 8, 2, 3, 6, 9, 3};
        assertArrayEquals(necessary, result);


    }

    @Test
    void thirdTestToFlattenMatrix() {
        int[][] matrix = {{1, 2, 3, 0}, {4, 5, 6, 9}, {7, 8, 9, 1} };

        int[] result = problem3.flattenMatrix(matrix);

        int[] necessary = {1, 4, 7, 2, 5, 8, 3, 6, 9, 0, 9, 1};
        assertArrayEquals(necessary, result);


    }
}