package pro1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import pro1.apiDataModel.Deadline;

public class Main7 {
    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);

        Gson gson = new Gson();
        JsonArray prijimaciOborArray = gson.fromJson(json, JsonObject.class).getAsJsonArray("prijimaciObor");
        List<Deadline> deadlines = gson.fromJson(prijimaciOborArray, new TypeToken<List<Deadline>>() {}.getType());

        if (deadlines == null || deadlines.isEmpty()) {
            return "";
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");


        return deadlines.stream()
                .map(Deadline::getApplicationDeadline)
                .filter(deadline -> deadline != null && !deadline.isBlank())
                .map(deadline -> {
                    try {
                        return LocalDate.parse(deadline.trim(), formatter);
                    } catch (Exception e) {
                        return null; 
                    }
                })
                .filter(date -> date != null) 
                .distinct() 
                .sorted() 
                .map(date -> date.format(formatter))
                .collect(Collectors.joining(","));
    }
}
