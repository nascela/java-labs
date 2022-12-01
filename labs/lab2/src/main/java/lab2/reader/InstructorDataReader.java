package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Instructor;

import java.io.File;
import java.io.IOException;

public class InstructorDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Instructor[] readInstructorsData() throws IOException {
        return objectMapper.readValue(new File("labs/lab2/src/main/resources/Instructors.json"), Instructor[].class);
    }
}
