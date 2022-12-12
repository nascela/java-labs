package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Instructor;
import lab2.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class InstructorDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<Instructor> readInstructorsData() throws IOException {

        Instructor[] array = objectMapper.readValue(new File("labs/lab2/src/main/resources/instructors.json"), Instructor[].class);
        ArrayList<Instructor> res = new ArrayList<Instructor>();
        Collections.addAll(res, array);
        return res;
    }
}
