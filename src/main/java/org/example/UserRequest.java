package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class UserRequest {
    public ArrayList<String[]> workWithUserRequest(String userRequest, HashMap<Character, ArrayList<String[]>> searchMap){
        ArrayList<String[]> finalAnswer = new ArrayList<>();

        String userRequestIgnoreCase = Character.toUpperCase(userRequest.charAt(0)) + userRequest.substring(1).toLowerCase();


        int findIndex = -1;
        if(searchMap.containsKey(userRequestIgnoreCase.charAt(0))) {
            int left = 0, right = searchMap.get(userRequestIgnoreCase.charAt(0)).size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (searchMap.get(userRequestIgnoreCase.charAt(0)).get(mid)[1].startsWith(userRequestIgnoreCase)) {
                    findIndex = mid;
                    break;
                } else {
                    if (searchMap.get(userRequestIgnoreCase.charAt(0)).get(mid)[1].compareTo(userRequestIgnoreCase) > 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            if (findIndex != -1) {
                finalAnswer.add(searchMap.get(userRequestIgnoreCase.charAt(0)).get(findIndex));

                for (int i = findIndex + 1; i < searchMap.get(userRequestIgnoreCase.charAt(0)).size(); i++) {
                    if (!(searchMap.get(userRequestIgnoreCase.charAt(0)).get(i)[1].startsWith(userRequestIgnoreCase))) {
                        break;
                    }
                    finalAnswer.add(searchMap.get(userRequestIgnoreCase.charAt(0)).get(i));
                }

                for (int i = findIndex - 1; i > 0; i--) {
                    if (!(searchMap.get(userRequestIgnoreCase.charAt(0)).get(i)[1].startsWith(userRequestIgnoreCase))) {
                        break;
                    }
                    finalAnswer.add(searchMap.get(userRequestIgnoreCase.charAt(0)).get(i));
                }
            }
        }



        finalAnswer.sort(Comparator.comparing(a -> a[1]));
        return finalAnswer;
        //finalAnswer.forEach(str -> System.out.println(str[1] + Arrays.toString(str)));


    }

}
