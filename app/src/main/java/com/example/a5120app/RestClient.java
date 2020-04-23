package com.example.a5120app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestClient {
    private static final String BASE_URL = "http://192.168.1.220:8080/CalorieTracker/webresources/";
    // private static final String MAP_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    // eduroam
    // private static final String BASE_URL = "http://118.138.20.238:8080/CalorieTracker/webresources/";
    public static String getUserLogin(String userName, String passWordHash) throws JSONException {
        final String methodPathUser = "calorietracker.users/findByUserName/" + userName;
        String userId = "";
        String user = getData(methodPathUser);

        JSONArray userArray = new JSONArray(user);
        userId = Integer.toString(userArray.getJSONObject(0).getInt("userId"));

        final String methodPathCred = "calorietracker.credential/findByUserId/" + userId;
        JSONArray credArray = new JSONArray(getData(methodPathCred));
        String hash = credArray.getJSONObject(0).getString("passwordHash");

        if (hash.equals(passWordHash))
            return user;
        else
            return "";
    }

    public static String getBurnPerStep(String userId) {
        // http://localhost:8080/CalorieTracker/webresources/calorietracker.users/countCalorieBurnPerStep/1
        String methodPath = "calorietracker.users/countCalorieBurnPerStep/" + userId;
        String result = getData(methodPath).replaceAll("[^\\d.]", "");
        return result;
    }

    public static String getBmr(String userId) {
        String methodPath = "calorietracker.users/countBmrViaActivity/" + userId;
        String result = getData(methodPath).replaceAll("[^\\d.]", "");
        return result;
    }

    //webresources/calorietracker.consumption/countTotalCalorie/1/20180325
    public static String getTodayConsume(String userId, String date) {
        String methodPath = "calorietracker.consumption/countTotalCalorie/" + userId + "/" + date;
        String result = getData(methodPath).replaceAll("[^\\d.]", "");
        return result;
    }

    // [1500.00,1800.00,800.00] c b g+b-c
    public static String produceOneDayReport(String userId, String date){
        String methodPath = "calorietracker.report/produceReport/" + userId + "/" + date;
        return getData(methodPath);
    }

    // [11900.0,10000.0,8500] b c s
    public static String produceReport(String userId, String dateS, String dateE){
        String methodPath = "calorietracker.report/produceReport3/" + userId + "/" + dateS + "/" + dateE;
        return getData(methodPath);
    }

    public static String getFoodById(String foodId) {
        String methodPath = "calorietracker.food/" + foodId;
        return getData(methodPath);
    }

    public static String getAllFood() {
        String methodPath = "calorietracker.food/";
        return getData(methodPath);
    }

    public static String getUserById(String userId) {
        // http://localhost:8080/CalorieTracker/webresources/calorietracker.users/1?
        String methodPath = "calorietracker.users/" + userId;
        return getData(methodPath);
    }

    public static String getReportByNameAndDate(String userName, String date) {
        String methodPath = "calorietracker.report/findUserNameAndReportDate/" + userName + "/" + date;
        return getData(methodPath);
    }

//    public static void updateReportStep(String userName, String date, String steps) throws JSONException {
//        //{"calorieGoal":500.00,"reportDate":"20190325","reportId":10,"totalCaloriesBurned":1800.00,"totalCaloriesConsumed":1500.00,"totalStepTaken":1500,"userId":{"address":"Adelaide","dob":"19900101","email":"aaa@gmail.com","gender":"female","height":1.60,"levelOfActivity":1,"postcode":"3001","stepsPerMile":2400,"surname":"Adams","userId":1,"userName":"Alice","weight":50.00}}
//
//        String report = getReportByNameAndDate(userName, date);
//        JSONArray reportJA = new JSONArray(report);
//        JSONObject reportJO = reportJA.getJSONObject(0);
//        reportJO.put("totalStepTaken", Integer.parseInt(steps));
//        int reportId = reportJO.getInt("reportId");
//        String methodPath = "calorietracker.report";
//        report = reportJO.toString();
//        report = report.substring(0, report.length() - 1);
//        updateData(methodPath, Integer.toString(reportId), report);
//    }

    public static void postReportStep(String userId, String date, String steps, String goal, String burn, String consume)
            throws JSONException {
        String report = "";
        int reportId = getMaxId("report") + 1;
        JSONObject userJO = new JSONObject(getUserById(userId));
        JSONObject reportJO = new JSONObject();
        // {"userId":{"address":"Melbourne","dob":"19950505","email":"test","gender":"female","height":1.75,"levelOfActivity":3,"postcode":"3000","stepsPerMile":2400,"surname":"test","userId":6,"userName":"test","weight":60},
        // "reportDate":"20190519","calorieGoal":0,"totalStepTaken":0,"totalCaloriesBurned":0}
        // "calorieGoal":500.00,"reportDate":"20190325","reportId":1,"totalCaloriesBurned":1800.00,"totalCaloriesConsumed":1500.00,"totalStepTaken":1000

        reportJO.put("userId", userJO);
        reportJO.put("reportDate", date);

        reportJO.put("reportId", reportId);
        reportJO.put("calorieGoal", Double.parseDouble(goal));
        reportJO.put("totalStepTaken", Integer.parseInt(steps));
        reportJO.put("totalCaloriesBurned", Double.parseDouble(burn));
        reportJO.put("totalCaloriesBurned", Double.parseDouble(consume));
        report = reportJO.toString();
        String methodPath = "calorietracker.report";
        postData(methodPath, report);
    }

    public static void createUser(String input) {
        String resURL = "calorietracker.users";
        postData(resURL, input);
    }

    public static void createCredential(String input) {
        //{"credId":6,"passwordHash":"9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08","signUpDate":"20190518","userId":{"address":"Melbourne","dob":"19960605","email":"test","gender":"female","height":"1.75","levelOfActivity":"3","postcode":"3000","stepsPerMile":"null","surname":"test","userId":6,"userName":"test","weight":"65"}}
        //{"credId":1,"passwordHash":"b3464291a58f65050d16b715a13e1c4d422785e733f7a9c44e381dfe620b5855","signUpDate":"20190101","userId":{"address":"Adelaide","dob":"19900101","email":"aaa@gmail.com","gender":"female","height":1.60,"levelOfActivity":1,"postcode":"3001","stepsPerMile":2400,"surname":"Adams","userId":1,"userName":"Alice","weight":50.00}}
        String cred = "";
        try {
            JSONObject credJO = new JSONObject(input);
            String userId = credJO.getString("userId");
            String userInfo = getUserById(userId);
            JSONObject userJO = new JSONObject(userInfo);
            credJO.put("userId", userJO);
            cred = credJO.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String resURL = "calorietracker.credential";
        postData(resURL, cred);
    }

    // {"calorieAmount":62.00,"category":"Fruits","fat":0.50,"foodId":1,"foodName":"apple","servingAmount":128.00,"servingUnit":"one cup"}
    // {"consDate":"20190325","consId":1,"foodId":{"calorieAmount":62.00,"category":"Fruits","fat":0.50,"foodId":1,"foodName":"apple","servingAmount":128.00,"servingUnit":"one cup"},"quantity":3.00,"userId":{"address":"Adelaide","dob":"19900101","email":"aaa@gmail.com","gender":"female","height":1.60,"levelOfActivity":1,"postcode":"3001","stepsPerMile":2400,"surname":"Adams","userId":1,"userName":"Alice","weight":50.00}}
    public static void updateConsumprion(String reportId, String consumption) {
        String methodPath = "calorietracker.consumption";
        updateData(methodPath, reportId, consumption);
    }

    public static void postFood(String reportId, String food) {
        String methodPath = "calorietracker.report/" + reportId;
        postData(methodPath, food);
    }

    public static void postData(String urlStr, String data) {
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(BASE_URL + urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(data.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(data);
            out.close();
            int responseCode = conn.getResponseCode();
            Log.i("error", new Integer(responseCode).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public static void updateData(String urlStr, String id, String data) {
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(BASE_URL + urlStr + "/" + id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(data.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(data);
            out.close();
            int responseCode = conn.getResponseCode();
            Log.i("error", new Integer(responseCode).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public static boolean isExist(String input, String type) {
        boolean exist = true;
        String userStr;
        switch (type) {
            case "userName":
                String userUrl = "calorietracker.users/findByUserName/" + input;
                userStr = getData(userUrl);
                if (userStr.equals("[]")) {
                    exist = false;
                }
                break;
            case "email":
                String emailUrl = "calorietracker.users/findByEmail/" + input;
                userStr = getData(emailUrl);
                if (userStr.equals("[]")) {
                    exist = false;
                }
                break;
        }
        return exist;
    }

    public static int getMaxId(String type) {
        int id = 0;
        URL url = null;
        String idStr = "";
        String idUrlStr = "";

        switch (type) {
            case "user":
                idUrlStr = "calorietracker.users/count/";
                break;
            case "cred":
                idUrlStr = "calorietracker.credential/count/";
                break;
            case "consumption":
                idUrlStr = "calorietracker.consumption/count/";
                break;
            case "food":
                idUrlStr = "calorietracker.food/count/";
                break;
            case "report":
                idUrlStr = "calorietracker.report/count/";
                break;
        }

        HttpURLConnection conn = null;

        String urlStr = BASE_URL + idUrlStr;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            Scanner inStream = new Scanner(conn.getInputStream());
            while (inStream.hasNextLine()) {
                idStr = inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        id = Integer.parseInt(idStr);
        return id;
    }

    public static String getData(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(BASE_URL + urlStr);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input stream and store it as string
            while (inStream.hasNextLine()) {
                result.append(inStream.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return result.toString();
    }


}

