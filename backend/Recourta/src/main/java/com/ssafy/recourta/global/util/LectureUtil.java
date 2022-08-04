package com.ssafy.recourta.global.util;

import org.json.simple.JSONArray;

import java.util.Arrays;

public class LectureUtil {

    public static JSONArray stringToJsonArray(String lectureTime) {
        // lectureTime은 "[월 10:00 ~ 11:30, 월 18:30 ~ 19:00, ...] 같은 형식

        // str = "월 10:00 ~ 11:30, 월 18:30 ~ 19:00, ... "
        String str = lectureTime.substring(1, lectureTime.length()-1);
        // arr = ["월 10:00 ~ 11:30", "월 18:30 ~ 19:00", ... ]
        String[] arr = str.split(", |,");
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(Arrays.asList(arr));
        return jsonArray;
    }
}
