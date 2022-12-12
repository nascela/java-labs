package lab2.service;

import lab2.data.Data;
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
    public ArrayList<Student> findStudentsByCourseId(long courseId) {

        ArrayList<Integer> currentIdOfStudentsOnCourses = new ArrayList<Integer>();
        ArrayList<Student> currentStudents = new ArrayList<Student>();



        // нходим нужный курс в инстас, присваиваем список id студентов на данном курсе
        data.getCourseInstances().stream()
                .forEach(courseInstance -> {
                    if(courseInstance.getId() == courseId)
                        currentIdOfStudentsOnCourses.addAll(courseInstance.getIdOfStudents());
                });


        data.getAllStudents().stream()
                .forEach(student -> {
                    currentIdOfStudentsOnCourses.stream()
                            .forEach(id -> {
                                if(student.getId() == id)
                                    currentStudents.add(student);
                            });
                });


        System.out.println(currentIdOfStudentsOnCourses);
        return currentStudents;
    }

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    @Override
    public ArrayList<Student> findStudentsByInstructorId(long instructorId) {

        ArrayList<Integer> currentIdOfStudentsByInstructor = new ArrayList<Integer>();
        ArrayList<Student> currentStudents  = new ArrayList<Student>();



        data.getCourseInstances().stream()
                .filter(courseInstance -> courseInstance.getInstructorId() == instructorId)
                .forEach(courseInstance -> currentIdOfStudentsByInstructor.addAll(courseInstance.getIdOfStudents()));





        currentIdOfStudentsByInstructor.stream()
                .distinct()
                .forEach(id -> {
                    data.getAllStudents().stream()
                            .filter(student -> student.getId() == id)
                            .forEach(student -> currentStudents.add(student));
                });





        currentIdOfStudentsByInstructor.stream().distinct().forEach(id -> System.out.println(id));
        return currentStudents;

    }



    /**
     * @param instructorId идентификатор преподавателя
     * @param courseId идентификатор курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    @Override
    public ArrayList<Instructor> findReplacement(long instructorId, long courseId) {

        ArrayList<Long> idOfReplacementsInstructors = new ArrayList<Long>();
        ArrayList<Instructor> replacementInstructors = new ArrayList<Instructor>();


        Long courseInfoId = data.getCourseInstances().stream()
                .filter(courseInstance -> courseInstance.getId() == courseId)
                .findFirst().get().getCourseId();


        data.getInstructors().stream()
                .forEach(instructor -> {
                    instructor.getCanTeach().stream()
                            .filter(id -> id == courseInfoId && instructor.getId() != instructorId)
                            .forEach(id -> idOfReplacementsInstructors.add(id));

                });



        idOfReplacementsInstructors.stream()
                .distinct()
                .forEach(id -> {
                    data.getInstructors().stream()
                            .filter(instructor -> instructor.getId() == id)
                            .forEach(instructor -> replacementInstructors.add(instructor));
                });






        idOfReplacementsInstructors.stream().distinct().forEach(id-> System.out.println(id));
        return replacementInstructors;
    }
}
