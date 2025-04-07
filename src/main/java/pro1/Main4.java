package pro1;

import java.util.Comparator;

import com.google.gson.Gson;

import pro1.apiDataModel.TeachersList;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
        String json2 = Api.getTeachersByDepartment(department);
        TeachersList teachers= new Gson().fromJson(json2, TeachersList.class);


        teachers.items.stream()
        .filter(t -> t.email != null)
        .sorted(Comparator.comparing(t -> t.email.length()))
        .limit(count)
        .forEach(t -> System.out.println(t.email));

        //Možno přes for cyklus
        // for(Teacher t : result)
        //     System.out.println(t.email);
    }
}