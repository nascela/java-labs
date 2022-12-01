package lab2.reader;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.CourseInstance;
import lab2.model.CourseInfo;

import java.io.File;
import java.io.IOException;

public class CourseDataReader {

    public CourseDataReader()
    {
        objectMapper.findAndRegisterModules();
    }
    private ObjectMapper objectMapper = new ObjectMapper();



    public CourseInfo[] readCourseInfoData() throws IOException {
        return objectMapper.readValue(new File("labs/lab2/src/main/resources/courseInfos.json"), CourseInfo[].class);
    }


    public CourseInstance[] readCourseInstanceData() throws IOException {
        return objectMapper.readValue(new File("labs/lab2/src/main/resources/courseInstances.json"), CourseInstance[].class);
    }
}
