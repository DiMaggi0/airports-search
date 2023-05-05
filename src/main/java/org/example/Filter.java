package org.example;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static org.example.App.editFilterString;

public class Filter {
    public HashMap<Character, ArrayList<String[]>> workWithFilter(String filter) throws IOException, ScriptException {
        HashMap<Character, ArrayList<String[]>> searchMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/airports.csv"))) {
            String line = br.readLine();
            while (line != null) {

                String[] fileString = line.split(",");
                for (int i = 0; i < fileString.length; i++) {
                    if (fileString[i].contains("\"") || fileString[i].contains("'")) {
                        fileString[i] = fileString[i].substring(1, fileString[i].length() - 1);
                    }
                    if(fileString[i].equals("\\N")){
                        fileString[i] = "";
                    }
                }

                ArrayList<String[]> al;
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

                engine.put("Column", fileString);





                if ((boolean) engine.eval(editFilterString(filter))) { // здесь вызывается функция, отвечающая за фильтр, вводимый пользователем

                    // здесь создаем hashmap, которая будет хранить нужные нам строки
                    if (searchMap.containsKey(fileString[1].charAt(0))) {
                        al = searchMap.get(fileString[1].charAt(0));
                        al.add(fileString);
                        searchMap.remove(fileString[1].charAt(0));

                    } else {
                        al = new ArrayList<>();
                        al.add(fileString);
                    }
                    searchMap.put(fileString[1].charAt(0), al);
                }
                line = br.readLine();
            }


        }

        for (char key : searchMap.keySet()) {
            searchMap.get(key).sort(Comparator.comparing(a -> a[1]));

        }

        return searchMap;
    }

}
