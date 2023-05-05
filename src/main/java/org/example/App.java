package org.example;
import javax.script.*;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class App {
    /**
     * Метод, редактирующий строку фильтра и возвращающий новую, по которой будет происходить сравнение
     * @param filter - строка-фильтр, которую вводит пользователь
     * @return возвращает модифицированную строку, по которой будет выполняться поиск
     */
    public static String editFilterString(String filter) {

        String returned = filter.replaceAll("]", "-1]");
        returned = returned.replaceAll("&", "&&");
        returned = returned.replaceAll("=", "===");
        returned = returned.replaceAll("<>", "!==");
        return returned;

    }

    public static void main(String[] args) throws IOException, ScriptException, IllegalArgumentException{
        java.util.Scanner userInput = new Scanner(System.in);

        while(true) {
            System.out.println("Введите фильтр для строк файла.");

            String userFilter = userInput.nextLine();
            if(userFilter.equals("!quit")){
                System.exit(0);
            }
            Filter filter = new Filter();
            System.out.println("Подождите, выполняется фильтрация данных...");
            HashMap<Character, ArrayList<String[]>> searchMap = filter.workWithFilter(userFilter);
            System.out.println("Фильтрация успешно завершена!");
            System.out.println("Введите начало названия аэропортов.");
            UserRequest request = new UserRequest();
            String userRequest = userInput.nextLine();
            if(userRequest.equals("!quit")){
                System.exit(0);
            }
            Instant start = Instant.now();
            ArrayList<String[]> printAnswer = request.workWithUserRequest(userRequest, searchMap);

            printAnswer.forEach(str -> System.out.println(str[1] + Arrays.toString(str)));
            Instant finish = Instant.now();
            long d = Duration.between(start, finish).toMillis();

            System.out.println("Количество найденных строк: " + printAnswer.size());
            System.out.println("Время, затраченное на поиск: " + d + " ms");
            searchMap.clear();
        }


    }



}
