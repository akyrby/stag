package pro1;

import java.util.Comparator;
import java.util.HashMap;

import com.google.gson.Gson;

import pro1.apiDataModel.ActionsList;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID

        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);
        
        HashMap<Long, Integer> hashmap = new HashMap<>();
        
        actions.items.stream()
        .forEach(a ->{
            int current = hashmap.getOrDefault(a.teacherId, 0);
            hashmap.put(a.teacherId, current + a.personsCount);
        });



        return hashmap.entrySet().stream()
        .max(Comparator.comparing(h -> h.getValue()))
        .get()
        .getKey();
    }
}