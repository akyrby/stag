package pro1;

import java.util.Comparator;

import com.google.gson.Gson;

import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.TeachersList;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {
        // TODO 3.2:
        //  - Stáhni seznam učitelů na katedře
        //  - Stáhni seznam akcí na katedře
        //  - Najdi učitele s nejvyšším "score" a vrať jeho e-mail
        String json = Api.getActionsByDepartment(department,year);
        String json2 = Api.getTeachersByDepartment(department);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);
        TeachersList teachers= new Gson().fromJson(json2, TeachersList.class);



        return teachers.items.stream()
            .max(Comparator.comparing(t -> TeacherScore(t.id, actions)))
            .get().email;
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        return departmentSchedule.items
                .stream()
                .filter(a -> a.teacherId == teacherId)
                .mapToInt(a -> a.personsCount)
                .sum();

         // TODO 3.1: Doplň pomocnou metodu - součet všech přihlášených studentů na akcích daného učitele
    }
}