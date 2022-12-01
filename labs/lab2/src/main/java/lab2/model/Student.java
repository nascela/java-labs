package lab2.model;


import java.util.ArrayList;

/**
 * Класс для информации о студенте
 */

public class Student extends Person {




    /**
     * список идентификаторов курсов (CourseInstance.id), пройденных студентом
     */
    private StudentCategory studentCategory;
    private long[] completedCourses;


    private ArrayList <Integer> currentCourses = new ArrayList<Integer>();




    public long[] getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(long[] completedCourses) {
        this.completedCourses = completedCourses;
    }

    public StudentCategory getStudentCategory() {
        return studentCategory;
    }

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public ArrayList<Integer> getCurrentCourses() {return currentCourses;}

    public void setCurrentCourses(ArrayList<Integer> currentCourses) {this.currentCourses = currentCourses;}



}
