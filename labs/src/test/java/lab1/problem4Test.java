package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class problem4Test {

    @Test
    void firstShouldTrueIsGeometricProgression() {

        String arr = "1,2,4,8,16";

        boolean result = problem4.isGeometricProgression(arr);

        assertTrue(result);
    }

    @Test
    void secondShouldTrueIsGeometricProgression() {

        String arr = "16,2,8,1,4";

        boolean result = problem4.isGeometricProgression(arr);

        assertTrue(result);
    }

    @Test
    void thirdShouldFalseIsGeometricProgression() {

        String arr = "2,3,5";

        boolean result = problem4.isGeometricProgression(arr);

        assertFalse(result);
    }
}