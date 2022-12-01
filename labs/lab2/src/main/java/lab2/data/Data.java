package lab2.data;
import lab2.model.*;
import lab2.reader.CourseDataReader;
import lab2.reader.InstructorDataReader;
import lab2.reader.StudentDataReader;



import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data  {


    private Student bachelorStudents[];

    private Student masterStudents[];



    private Student allStudents[];



    private CourseInfo courseInfos[];

    private CourseInstance courseInstances[];

    private Instructor instructors[];


    public Data() throws IOException {
        int x = 0;

        StudentDataReader allStudents = new StudentDataReader();
        this.bachelorStudents = allStudents.readBachelorStudentData();
        this.masterStudents = allStudents.readMasterStudentData();
        this.allStudents = new Student[bachelorStudents.length + masterStudents.length];
        for(int i = 0; i < bachelorStudents.length; i++){
            this.allStudents[i] = bachelorStudents[i];
            this.allStudents[i].setStudentCategory(StudentCategory.valueOf("BACHELOR"));
            x++;
        }
        for(int i = 0; i < masterStudents.length; i++){
            this.allStudents[i+x] = masterStudents[i];
            this.allStudents[i+x].setStudentCategory(StudentCategory.valueOf("MASTER"));
        }


        CourseDataReader allCourses = new CourseDataReader();
        this.courseInfos = allCourses.readCourseInfoData();
        this.courseInstances = allCourses.readCourseInstanceData();
        InstructorDataReader allInstructors = new InstructorDataReader();
        this.instructors = allInstructors.readInstructorsData();

    }


    public void setAllStudents(Student[] allStudents) {
        this.allStudents = allStudents;
    }

    public Student[] getAllStudents() {
        return allStudents;
    }


    public Student[] getBachelorStudents() {
        return bachelorStudents;
    }

    public void setBachelorStudents(Student[] bachelorStudents) {
        this.bachelorStudents = bachelorStudents;
    }

    public Student[] getMasterStudents() {
        return masterStudents;
    }

    public void setMasterStudents(Student[] masterStudents) {
        this.masterStudents = masterStudents;
    }

    public CourseInfo[] getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(CourseInfo[] courseInfos) {
        this.courseInfos = courseInfos;
    }

    public CourseInstance[] getCourseInstances() {
        return courseInstances;
    }

    public void setCourseInstances(CourseInstance[] courseInstances) {
        this.courseInstances = courseInstances;
    }

    public Instructor[] getInstructors() {
        return instructors;
    }

    public void setInstructors(Instructor[] instructors) {
        this.instructors = instructors;
    }

}
