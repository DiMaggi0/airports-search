package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class UserRequest {
    public ArrayList<String[]> processRequest(String userRequest, HashMap<Character, ArrayList<String[]>> searchMap) {
        ArrayList<String[]> finalResponse = new ArrayList<>();


        System.out.println(userRequest);
        int findIndex = -1;
        if (searchMap.containsKey(userRequest.charAt(0))) {
            int left = 0, right = searchMap.get(userRequest.charAt(0)).size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (searchMap.get(userRequest.charAt(0)).get(mid)[1].startsWith(userRequest)) {
                    findIndex = mid;
                    break;
                } else {
                    if (searchMap.get(userRequest.charAt(0)).get(mid)[1].compareTo(userRequest) > 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            if (findIndex != -1) {
                finalResponse.add(searchMap.get(userRequest.charAt(0)).get(findIndex));

                for (int i = findIndex + 1; i < searchMap.get(userRequest.charAt(0)).size(); i++) {
                    if (!(searchMap.get(userRequest.charAt(0)).get(i)[1].startsWith(userRequest))) {
                        break;
                    }
                    finalResponse.add(searchMap.get(userRequest.charAt(0)).get(i));
                }

                for (int i = findIndex - 1; i > 0; i--) {
                    if (!(searchMap.get(userRequest.charAt(0)).get(i)[1].startsWith(userRequest))) {
                        break;
                    }
                    finalResponse.add(searchMap.get(userRequest.charAt(0)).get(i));
                }
            }
        }


        finalResponse.sort(Comparator.comparing(a -> a[1]));
        return finalResponse;

    }

}
