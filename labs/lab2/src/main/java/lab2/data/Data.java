package lab2.data;
import lab2.model.*;
import lab2.reader.CourseDataReader;
import lab2.reader.InstructorDataReader;
import lab2.reader.StudentDataReader;
import lab2.model.StudentCategory;



import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

public class Data  {



    private ArrayList<Student> bachelorStudents = new ArrayList<Student>();
//    private Student bachelorStudents[];

    private ArrayList<Student> masterStudents = new ArrayList<Student>();
    //private Student masterStudents[];


    private ArrayList<Student> allStudents = new ArrayList<Student>();
    //private Student allStudents[];


    private ArrayList<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
    //private CourseInfo courseInfos[];

    private ArrayList<CourseInstance> courseInstances = new ArrayList<CourseInstance>();
    //private CourseInstance courseInstances[];

    private ArrayList<Instructor> instructors = new ArrayList<Instructor>();
    //private Instructor instructors[];


    public Data() throws IOException {


        StudentDataReader allStudents = new StudentDataReader();
        this.bachelorStudents = allStudents.readBachelorStudentData();
        this.masterStudents = allStudents.readMasterStudentData();
        bachelorStudents.stream()
                .forEach(student -> student.setStudentCategory(StudentCategory.valueOf("BACHELOR")));

        masterStudents.stream()
                .forEach(student -> student.setStudentCategory(StudentCategory.valueOf("MASTER")));

        this.allStudents = this.bachelorStudents;
        this.allStudents.addAll(this.masterStudents);





        CourseDataReader allCourses = new CourseDataReader();
        this.courseInfos = allCourses.readCourseInfoData();
        this.courseInstances = allCourses.readCourseInstanceData();
        InstructorDataReader allInstructors = new InstructorDataReader();
        this.instructors = allInstructors.readInstructorsData();

    }

    public ArrayList<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(ArrayList<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public ArrayList<CourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(ArrayList<CourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }

    public ArrayList<CourseInstance> getCourseInstances() {
        return courseInstances;
    }

    public void setCourseInstances(ArrayList<CourseInstance> courseInstances) {
        this.courseInstances = courseInstances;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }


}
