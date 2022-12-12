package lab2.service;

import lab2.model.Instructor;
import lab2.model.Student;

import java.util.ArrayList;

/**
 * Интерфейс сервиса для преподавателя
 */
public interface CourseInstructorService {
    
    /**
     * @param courseId идентификатор курса
     * @return список студентов, зарегистрированных на данный курс
     */
    ArrayList<Student> findStudentsByCourseId(long courseId);

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    ArrayList<Student> findStudentsByInstructorId(long instructorId);

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseId идентификатор курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    ArrayList<Instructor> findReplacement(long instructorId, long courseId);

}
