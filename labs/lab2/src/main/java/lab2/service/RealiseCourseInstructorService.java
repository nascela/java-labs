package lab2.service;

import lab2.data.Data;
import lab2.model.CourseInstance;
import lab2.model.Instructor;
import lab2.model.Student;

import java.util.ArrayList;

public class RealiseCourseInstructorService implements CourseInstructorService {


    private Data data;

    public RealiseCourseInstructorService(Data data) {
        this.data = data;
    }


    /**
     * @param courseId идентификатор курса
     * @return список студентов, зарегистрированных на данный курс
     */
    @Override
    public Student[] findStudentsByCourseId(long courseId) {

        int currentIdOfCurseInstance = -1;
        ArrayList<Integer> currentIdOfStudentsOnCourses;
        Student currentStudents[];
        int x = 0;

        // нходим нужный курс в инстас
        for(int j = 0; j < data.getCourseInstances().length; j++ ){
            if(data.getCourseInstances()[j].getId() == courseId){
                currentIdOfCurseInstance = j;
            }
        }

        currentIdOfStudentsOnCourses =  data.getCourseInstances()[currentIdOfCurseInstance].getIdOfStudents();
        currentStudents = new Student[currentIdOfStudentsOnCourses.size()];

        for(int i = 0; i < data.getAllStudents().length; i++){
            for (int j = 0; j < currentIdOfStudentsOnCourses.size(); j++){
                if(data.getAllStudents()[i].getId() == currentIdOfStudentsOnCourses.get(j)){
                    currentStudents[x] = data.getAllStudents()[i];
                    x++;
                }
            }
        }

        System.out.println(currentIdOfStudentsOnCourses);
        return currentStudents;
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {

        ArrayList<Integer> currentIdOfStudentsByInstructor = new ArrayList<Integer>();
        Student currentStudents[];
        int x = 0;

        int currentIdOfInstructor = -1;
        for(int i = 0; i < data.getInstructors().length; i++){
            if(data.getInstructors()[i].getId() == instructorId){
                currentIdOfInstructor = i;
                break;
            }
        }

        for(int i = 0; i < data.getCourseInstances().length; i++){
            if(data.getCourseInstances()[i].getInstructorId() == instructorId){
                currentIdOfStudentsByInstructor.addAll(data.getCourseInstances()[i].getIdOfStudents());

            }
        }

        for(int i = 0; i < currentIdOfStudentsByInstructor.size(); i++){
            for(int j = i + 1; j < currentIdOfStudentsByInstructor.size(); j++){
                if(currentIdOfStudentsByInstructor.get(i) == currentIdOfStudentsByInstructor.get(j)){
                    currentIdOfStudentsByInstructor.remove(j);
                }
            }
        }



        currentStudents = new Student[currentIdOfStudentsByInstructor.size()];

        for(int i = 0; i < data.getAllStudents().length; i++){
            for (int j = 0; j < currentIdOfStudentsByInstructor.size(); j++){
                if(data.getAllStudents()[i].getId() == currentIdOfStudentsByInstructor.get(j)){
                    currentStudents[x] = data.getAllStudents()[i];
                    x++;
                }
            }
        }

        System.out.println(currentIdOfStudentsByInstructor);
        return currentStudents;

    }



    /**
     * @param instructorId идентификатор преподавателя
     * @param courseId идентификатор курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {

        int currentIdOfInstructor = -1;
        long curseInfoId = -1;
        int x = 0;

        ArrayList<Long> idOfReplacementsInstructors = new ArrayList<Long>();
        Instructor replacementInstructors[];

        // находим преподавателя
        for(int i = 0; i < data.getInstructors().length; i++){
            if(data.getInstructors()[i].getId() == instructorId){
                currentIdOfInstructor = i;
                break;
            }
        }

        // нходим нужный курс в истанс
        for(int j = 0; j < data.getCourseInstances().length; j++ ){
            if(data.getCourseInstances()[j].getId() == courseId){

                curseInfoId = data.getCourseInstances()[j].getCourseId();
                break;
            }
        }



        for(int i = 0; i < data.getInstructors().length; i ++){
            for(int j = 0; j < data.getInstructors()[i].getCanTeach().length; j++){
                if(data.getInstructors()[i].getCanTeach()[j] == curseInfoId && i != currentIdOfInstructor){
                    idOfReplacementsInstructors.add(data.getInstructors()[i].getId());
                }
            }
        }

        replacementInstructors = new Instructor[idOfReplacementsInstructors.size()];
        for(int i = 0; i < data.getInstructors().length; i ++){
            for(int j = 0; j < idOfReplacementsInstructors.size(); j++){
                if(data.getInstructors()[i].getId() == idOfReplacementsInstructors.get(j)){
                    replacementInstructors[x] = data.getInstructors()[i];
                    x++;
                }

            }
        }



        System.out.println(idOfReplacementsInstructors);
        return replacementInstructors;
    }
}
