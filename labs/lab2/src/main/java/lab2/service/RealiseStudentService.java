package lab2.service;
import lab2.model.ActionStatus;
import lab2.model.CourseInstance;
import lab2.data.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

        ///находим нужного студента по id
        int currentNumOfStudent = IntStream.range(0, data.getAllStudents().size())
                .filter(i -> data.getAllStudents().get(i).getId() == studentId)
                .findFirst().getAsInt();

        // нходим нужный курс в инстас
        int currentNumOfCurseInstance = IntStream.range(0, data.getCourseInstances().size())
                .filter(i -> data.getCourseInstances().get(i).getId() == courseId)
                .findFirst().getAsInt();

        // находим нужный курс в инфо
        int currentNumOfCurseInfo = IntStream.range(0,data.getCourseInfos().size())
                .filter(i-> data.getCourseInfos().get(i).getId() == data.getCourseInstances().get(currentNumOfCurseInstance).getCourseId())
                .findFirst().getAsInt();

        int x = data.getCourseInfos().get(currentNumOfCurseInfo).getStudentCategories().size();

        ArrayList<Long> necessaryCourses = data.getCourseInfos().get(currentNumOfCurseInfo).getPrerequisites();
        ArrayList<Long> completedCourses = data.getAllStudents().get(currentNumOfStudent).getCompletedCourses();











        // проверка на прохождение нужных курсов
        if(necessaryCourses != null) {

//            int I = IntStream.range(0, necessaryCourses.size())
//                    .forEach(i -> {
//                        int J = IntStream.range(0, completedCourses.size())
//                                .forEach(j -> {
//                                    if(necessaryCourses.get(i) == completedCourses.get(j))
//
//                                });
//                    });

            for (int i = 0; i < necessaryCourses.size(); i++){
                for(int j = 0; j < completedCourses.size(); j++){
                    if (necessaryCourses.get(i) == completedCourses.get(j)){
                        break;
                    }
                    else if(j == completedCourses.size() - 1){
                        System.out.println("Студент не записан на курс - т.к. не прошел необходимые курсы");
                        return ActionStatus.NOK ;
                    }
                }
            }
        }

        // проверк на начало курса
        if(data.getCourseInstances().get(currentNumOfCurseInstance).getStartDate().isAfter(LocalDate.now())){
            // проверка судента на бакалавра\магистра
            if(data.getAllStudents().get(currentNumOfStudent).getStudentCategory() == data.getCourseInfos().get(currentNumOfCurseInfo).getStudentCategories().get(0) || data.getAllStudents().get(currentNumOfStudent).getStudentCategory() == data.getCourseInfos().get(currentNumOfCurseInfo).getStudentCategories().get(x-1) ){
                // проверка курса на заполненность
                if(data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().size() < data.getCourseInstances().get(currentNumOfCurseInstance).getCapacity()){
                    data.getAllStudents().get(currentNumOfStudent).getCurrentCourses().add(Math.toIntExact(courseId));
                    data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().add((int) studentId);
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



        ///находим нужного студента по id
        ///находим нужного студента по id
        int currentNumOfStudent = IntStream.range(0, data.getAllStudents().size())
                .filter(i -> data.getAllStudents().get(i).getId() == studentId)
                .findFirst().getAsInt();

        // нходим нужный курс в инстас
        int currentNumOfCurseInstance = IntStream.range(0, data.getCourseInstances().size())
                .filter(i -> data.getCourseInstances().get(i).getId() == courseId)
                .findFirst().getAsInt();

        // находим нужный курс в инфо
        int currentNumOfCurseInfo = IntStream.range(0,data.getCourseInfos().size())
                .filter(i-> data.getCourseInfos().get(i).getId() == data.getCourseInstances().get(currentNumOfCurseInstance).getCourseId())
                .findFirst().getAsInt();

        if(!data.getCourseInstances().get(currentNumOfCurseInstance).getStartDate().isAfter(LocalDate.now())){
            System.out.println("Студент не отписан от курса - т.к. курс уже начался)");
            return ActionStatus.NOK;
        }



//        data.getAllStudents().get(currentNumOfStudent).getCurrentCourses().stream()
//                .forEach(cCourse-> {
//                    if(cCourse == courseId)
//                        data.getAllStudents().get(currentNumOfStudent).getCurrentCourses().remove(cCourse);
//                });
//
//        data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().stream()
//                .forEach(cStudent -> {
//                    if(cStudent == studentId)
//                        data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().remove(cStudent);
//                });

        IntStream.range(0, data.getAllStudents().get(currentNumOfStudent).getCurrentCourses().size())
                        .forEach(i -> {
                            if(data.getAllStudents().get(currentNumOfStudent).getCurrentCourses().get(i) == courseId)
                                data.getAllStudents().get(currentNumOfStudent).getCurrentCourses().remove(i);
                        });
        IntStream.range(0, data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().size())
                        .forEach(i -> {
                            if(data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().get(i) == studentId)
                                data.getCourseInstances().get(currentNumOfCurseInstance).getIdOfStudents().remove(i);
                        });




        System.out.println("Студент успешно отписан от курса");
        return ActionStatus.OK;


    }

    /**
     * @param studentId идентификатор студента
     * @return список всех курсов, на которые записан студент
     */

    @Override
    public ArrayList<CourseInstance> findAllSubscriptionsByStudentId(long studentId) {


        ArrayList<Integer> currentIdOfStudentCourses;
        ArrayList<CourseInstance> currentCourses = new ArrayList<CourseInstance>();

        currentIdOfStudentCourses = data.getAllStudents().stream()
                .filter(student -> student.getId() == studentId)
                .findFirst().get().getCurrentCourses();


        currentIdOfStudentCourses.stream()
                .forEach(id -> {
                    data.getCourseInstances().stream()
                            .filter(courseInstance -> courseInstance.getId() == id)
                            .forEach(courseInstance -> currentCourses.add(courseInstance));

                });


        System.out.println(currentIdOfStudentCourses);
        return currentCourses;
    }
}
