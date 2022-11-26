package lab1;

public class problem3 {

    /**
     * Метод flattenMatrix преобразует матрицу размера nxm в одномерный массив, записывая сперва элементы первого столбца,
     * затем элементы второго столбца и т.д.
     *
     * @param matrix матрица размера nxm
     * @return одномерный массив
     *
     * ПРИМЕР:
     * Вход: matrix = [[1, 2, 3],
     *                 [4, 5, 6],
     *                 [7, 8, 9]]
     * Выход: [1, 4, 7, 2, 5, 8, 3, 6, 9]
     */
    public static int[] flattenMatrix(int[][] matrix) {
        int[] result;
        int n = 0;
        result = new int[matrix.length * matrix[0].length];
        for (int i = 0; i < matrix[0].length; ++i){
            for (int j = 0; j < matrix.length; ++j){
                result[n] = matrix[j][i];
                ++n;
            }
        }
        return result;
    }

}