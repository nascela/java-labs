package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public ArrayList<Student> readBachelorStudentData() throws IOException {

        Student [] array = objectMapper.readValue(new File("labs/lab2/src/main/resources/bachelorStudents.json"), Student[].class);
        ArrayList<Student> res = new ArrayList<Student>();
        Collections.addAll(res, array);
        return res;
    }


    /**
     * @return список студентов-магистров
     */
    public ArrayList<Student> readMasterStudentData() throws IOException {
        Student [] array = objectMapper.readValue(new File("labs/lab2/src/main/resources/masterStudents.json"), Student[].class);
        ArrayList<Student> res = new ArrayList<Student>();
        Collections.addAll(res, array);
        return res;
    }

}
