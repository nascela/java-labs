package lab2.model;

import java.util.ArrayList;

/**
 * Класс для базовой информации о курсе
 */
public class CourseInfo {


    /**
     * идентификатор курса
     */
    private long id;

    /**
     * название курса
     */
    private String name;

    /**
     * краткое описание курса
     */
    private String description;

    public ArrayList<Long> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Long> prerequisites) {
        this.prerequisites = prerequisites;
    }

    /**
     * Список идентификаторов курсов, которые нужно обязательно пройти до начала данного курса
     */
    //private long[] prerequisites;
    private ArrayList<Long> prerequisites = new ArrayList<Long>();


    /**
     * список категорий студентов, которые могут посещать курс
     */
    //private StudentCategory[] studentCategories;
    private ArrayList<StudentCategory> studentCategories = new ArrayList<StudentCategory>();

    void setName(String name){
        this.name = name;
    }
    String getName(){
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public ArrayList<StudentCategory> getStudentCategories() {return studentCategories;}

    public void setStudentCategories(ArrayList<StudentCategory> studentCategories) {this.studentCategories = studentCategories;}



}
