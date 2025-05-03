package pro1;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeSet;
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
        // Fetch JSON data
        String json = Api.getSpecializations(year);


        // Parse the root JSON object
        Gson gson = new Gson();
        JsonObject root = gson.fromJson(json, JsonObject.class);

        // Extract the "prijimaciObor" array
        JsonArray prijimaciOborArray = root.getAsJsonArray("prijimaciObor");

        // Deserialize the array into a list of Deadline objects
        Type listType = new TypeToken<List<Deadline>>() {}.getType();
        List<Deadline> deadlines = gson.fromJson(prijimaciOborArray, listType);

        if (deadlines == null || deadlines.isEmpty()) {
            System.out.println("No deadlines found in the JSON.");
            return "";
        }



        // Define a date formatter for the JSON date format (dd.MM.yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        // Use a TreeSet to ensure uniqueness and sorting
        TreeSet<LocalDate> uniqueDates = deadlines.stream()
                .map(deadline -> {
                    try {
                        // Parse and normalize the date
                        return LocalDate.parse(deadline.getApplicationDeadline().trim(), formatter);
                    } catch (Exception e) {
                        System.out.println("Invalid date: " + deadline.getApplicationDeadline());
                        return null; // Skip invalid dates
                    }
                })
                .filter(date -> date != null) // Remove null dates
                .collect(Collectors.toCollection(TreeSet::new)); // Use TreeSet for uniqueness and sorting

        // Log unique dates
        System.out.println("Unique Dates: " + uniqueDates);

        // Convert the unique dates back to the desired format and join them
        return uniqueDates.stream()
                .map(date -> date.format(formatter)) // Convert back to dd.MM.yyyy format
                .collect(Collectors.joining(","));
    }
}
