package lab1;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class problem2Test {

    @Test
    void firstTestToSegregateEvenAndOddNumbers() {

        int[] nums = { 2, 1, 5, 6, 8 };

        int[] result = problem2.segregateEvenAndOddNumbers(nums);

        int[] necessary = {2, 6, 8, 1, 5};
        assertArrayEquals(result, necessary);
    }

    @Test
    void secondTestToSegregateEvenAndOddNumbers() {

        int[] nums = { 4, 5, 11, 22,49, 84, 4, 13 };

        int[] result = problem2.segregateEvenAndOddNumbers(nums);

        int[] necessary = {4, 22, 84, 4, 5, 11, 49, 13};
        assertArrayEquals(result, necessary);
    }
}