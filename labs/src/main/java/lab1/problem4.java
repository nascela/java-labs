package lab1;
import java.util.Arrays;
public class problem4 {

    /**
     * Метод isGeometricProgression определяет, является ли данная последовательность чисел numbers геометрической
     * прогрессией (возможно, при перестановке элементов)
     *
     * @param numbers строка, содержащая n положительных целых чисел, разделенных запятой
     * @return true, если последовательность является геометрической прогрессией
     *         false, если последовательность не является геометрической прогрессией
     *
     * ПРИМЕР1:
     * Вход: numbers = "1,2,4,8,16"
     * Выход: true
     *
     * ПРИМЕР2:
     * Вход: numbers = "16,2,8,1,4"
     * Выход: true (так как в результате перестановки элементов можно получить геометрическую прогрессию [1,2,4,8,16])
     *
     * ПРИМЕР3:
     * Вход: numbers = "2,3,5"
     * Выход: false
     */
    public static boolean isGeometricProgression(String numbers) {
        double[] numArr = Arrays.stream(numbers.split(",")).mapToDouble(Double::parseDouble).toArray();
        for(int i = 0; i < numArr.length - 1; ++i){
            for (int j = i + 1; j < numArr.length; ++j ){
                if (numArr[i] >  numArr[j]){
                    double tmp = numArr[i];
                    numArr[i] = numArr[j];
                    numArr[j] = tmp;
                }
            }
        }
        for (int i = 0; i < numArr.length - 2; ++i){
            if (numArr[i + 1] / numArr[i] == numArr[i + 2] / numArr[i + 1]){
                continue;
            }
            else return false;
        }
        return true;
    }

}
