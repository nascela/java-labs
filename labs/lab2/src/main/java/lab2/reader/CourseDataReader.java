package lab2.reader;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.CourseInstance;
import lab2.model.CourseInfo;
import lab2.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CourseDataReader {

    public CourseDataReader()
    {
        objectMapper.findAndRegisterModules();
    }
    private ObjectMapper objectMapper = new ObjectMapper();



    public ArrayList <CourseInfo> readCourseInfoData() throws IOException {
        CourseInfo[] array = objectMapper.readValue(new File("labs/lab2/src/main/resources/courseInfos.json"), CourseInfo[].class);
        ArrayList<CourseInfo> res = new ArrayList<CourseInfo>();
        Collections.addAll(res, array);
        return res;
    }


    public ArrayList<CourseInstance> readCourseInstanceData() throws IOException {
        CourseInstance [] array = objectMapper.readValue(new File("labs/lab2/src/main/resources/courseInstances.json"), CourseInstance[].class);
        ArrayList<CourseInstance> res = new ArrayList<CourseInstance>();
        Collections.addAll(res, array);

        return res;
    }
}
