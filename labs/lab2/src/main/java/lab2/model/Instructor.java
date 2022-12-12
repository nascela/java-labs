package lab2.model;

import java.util.ArrayList;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends Person {



    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */

    private ArrayList<Long> canTeach = new ArrayList<Long>();

    public ArrayList<Long> getCanTeach() { return canTeach;}

    public void setCanTeach(ArrayList<Long> canTeach) {this.canTeach = canTeach;}

    
}
