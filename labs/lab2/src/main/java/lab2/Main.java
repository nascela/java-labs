package lab2;
import java.time.LocalDate;

import lab2.data.Data;
import lab2.model.Student;
import lab2.reader.StudentDataReader;
import lab2.service.RealiseCourseInstructorService;
import lab2.service.RealiseStudentService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Data data = new Data();
        RealiseStudentService ss = new RealiseStudentService(data);
        RealiseCourseInstructorService is = new RealiseCourseInstructorService(data);
        ss.subscribe(102, 100002);
        ss.findAllSubscriptionsByStudentId(102);
        is.findStudentsByCourseId(100002);
        is.findStudentsByInstructorId(9002);
        ss.unsubscribe(102, 100002);
        ss.findAllSubscriptionsByStudentId(102);
        is.findStudentsByCourseId(100002);
        is.findStudentsByInstructorId(9002);
        is.findReplacement(9002, 100002);
    }
}
