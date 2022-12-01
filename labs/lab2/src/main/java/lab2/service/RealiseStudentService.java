package lab2.service;
import lab2.model.ActionStatus;
import lab2.model.CourseInstance;
import lab2.data.Data;

import java.time.LocalDate;
import java.util.ArrayList;

public class RealiseStudentService implements StudentService{

    private Data data;

    public RealiseStudentService(Data data) {
        this.data = data;
    }

    /**
     * Регистрация студента на курс. Регистрация возможна при следующих условиях:
     * -    курс еще не начался;
     * -    курс предназначен для категории данного студента (магистра/бакалавра);
     * -    студент прошел все обязательные курсы, необходимые для посещения данного курса;
     * -    в курсе есть свободные места.
     *
     * @param studentId идентификатор студента
     * @param courseId идентификатор курса, соответствующий CourseInstance.id
     * @return результат выполнения регистрации
     */

    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        int currentIdOfStudent = -1;
        int currentIdOfCurseInstance = -1;
        int currentIdOfCurseInfo = -1;
        int x = 0;

        long[] necessaryCourses;
        long[] completedCourses;

        ///находим нужного студента по id
        for (int i = 0; i < data.getAllStudents().length; i++){
            if (data.getAllStudents()[i].getId() == studentId ){
                currentIdOfStudent = i;
            }
        }
        // нходим нужный курс в инстас
        for(int j = 0; j < data.getCourseInstances().length; j++ ){
            if(data.getCourseInstances()[j].getId() == courseId){
                currentIdOfCurseInstance = j;
            }
        }
        // находим нужный курс в инфо
        for(int j = 0; j < data.getCourseInfos().length; j++ ){
            if(data.getCourseInfos()[j].getId() == data.getCourseInstances()[currentIdOfCurseInstance].getCourseId()){
                currentIdOfCurseInfo = j;
                x = data.getCourseInfos()[j].getStudentCategories().length;
            }
        }

        necessaryCourses = data.getCourseInfos()[currentIdOfCurseInfo].getPrerequisites();
        completedCourses = data.getAllStudents()[currentIdOfStudent].getCompletedCourses();
        // проверка на прохождение нужных курсов
        if(necessaryCourses != null) {

            for (int i = 0; i < necessaryCourses.length; i++){
                for(int j = 0; j < completedCourses.length; j++){
                    if (necessaryCourses[i] == completedCourses[j]){
                        break;
                    }
                    else if(j == completedCourses.length - 1){
                        System.out.println("Студент не записан на курс - т.к. не прошел необходимые курсы");
                        return ActionStatus.NOK ;
                    }
                }
            }
        }

        // проверк на начало курса
        if(data.getCourseInstances()[currentIdOfCurseInstance].getStartDate().isAfter(LocalDate.now())){
            // проверка судента на бакалавра\магистра
            if(data.getAllStudents()[currentIdOfStudent].getStudentCategory() == data.getCourseInfos()[currentIdOfCurseInfo].getStudentCategories()[0] || data.getAllStudents()[currentIdOfStudent].getStudentCategory() == data.getCourseInfos()[currentIdOfCurseInfo].getStudentCategories()[x - 1] ){
                // проверка курса на заполненность
                if(data.getCourseInstances()[currentIdOfCurseInstance].getIdOfStudents().size() < data.getCourseInstances()[currentIdOfCurseInstance].getCapacity()){
                    data.getAllStudents()[currentIdOfStudent].getCurrentCourses().add(Math.toIntExact(courseId));
                    data.getCourseInstances()[currentIdOfCurseInstance].getIdOfStudents().add((int) studentId);
                    System.out.println("Студент записан на курс");
                    return ActionStatus.OK;
                    //throw new Exception("");
                }
                else {
                    System.out.println("Студент не записан на курс - т.к. нехватает места на курсе");
                    return ActionStatus.NOK;
                }
            }
            else {
                System.out.println("Студент не записан на курс - т.к. не подходит по учебной программе)");
                return ActionStatus.NOK;
            }
        }
        else {
            System.out.println("Студент не записан на курс - т.к. курс уже начался)");
            return ActionStatus.NOK;
        }


    }
    /**
     * Отмена регистрации студента на курс, которая возможна только в том случае, когда
     * курс еще не начался.
     *
     * @param studentId идентификатор студента
     * @param courseId идентификатор курса, соответствующий CourseInstance.id
     * @return результат выполнения отмены регистрации
     */



    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {

        int currentIdOfStudent = -1;
        int currentIdOfCurseInstance = -1;
        int currentIdOfCurseInfo = -1;

        ///находим нужного студента по id
        for (int i = 0; i < data.getAllStudents().length; i++){
            if (data.getAllStudents()[i].getId() == studentId ){
                currentIdOfStudent = i;
            }
        }
        // нходим нужный курс в истанс
        for(int j = 0; j < data.getCourseInstances().length; j++ ){
            if(data.getCourseInstances()[j].getId() == courseId){
                currentIdOfCurseInstance = j;
            }
        }
        // находим нужный курс в инфо
        for(int j = 0; j < data.getCourseInfos().length; j++ ){
            if(data.getCourseInfos()[j].getId() == data.getCourseInstances()[currentIdOfCurseInstance].getCourseId()){
                currentIdOfCurseInfo = j;
            }
        }

        if(!data.getCourseInstances()[currentIdOfCurseInstance].getStartDate().isAfter(LocalDate.now())){
            System.out.println("Студент не отписан от курса - т.к. курс уже начался)");
            return ActionStatus.NOK;
        }

        for(int i = 0; i < data.getAllStudents()[currentIdOfStudent].getCurrentCourses().size(); i++){
            if (data.getAllStudents()[currentIdOfStudent].getCurrentCourses().get(i) == courseId){
                data.getAllStudents()[currentIdOfStudent].getCurrentCourses().remove(i);
            }
        }

        for(int i = 0; i < data.getCourseInstances()[currentIdOfCurseInstance].getIdOfStudents().size(); i++){
            if (data.getCourseInstances()[currentIdOfCurseInstance].getIdOfStudents().get(i) == studentId){
                data.getCourseInstances()[currentIdOfCurseInstance].getIdOfStudents().remove(i);
            }
        }


        System.out.println("Студент успешно отписан от курса");
        return ActionStatus.OK;


    }

    /**
     * @param studentId идентификатор студента
     * @return список всех курсов, на которые записан студент
     */

    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {

        int currentIdOfStudent = -1;
        ArrayList<Integer> currentIdOfStudentCourses;
        CourseInstance currentCourses[];
        int x = 0;

        ///находим нужного студента по id
        for (int i = 0; i < data.getAllStudents().length; i++){
            if (data.getAllStudents()[i].getId() == studentId ){
                currentIdOfStudent = i;
                break;
            }
        }

        currentIdOfStudentCourses = data.getAllStudents()[currentIdOfStudent].getCurrentCourses();
        currentCourses = new CourseInstance[currentIdOfStudentCourses.size()];

        for(int i = 0; i < data.getCourseInstances().length; i++){
            for(int j = 0; j < currentIdOfStudentCourses.size(); j++){
                if(data.getCourseInstances()[i].getId() == currentIdOfStudentCourses.get(j)){
                    currentCourses[x] = data.getCourseInstances()[i];
                    x++;
                }
            }
        }

        System.out.println(currentIdOfStudentCourses);
        return currentCourses;
    }
}
