package com.example.a5120app;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestClient {
    private static final String BASE_URL = "https://test-api-on-your-toes.herokuapp.com/";
    // private static final String MAP_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";


    public static String getSuburb() {
        // http://localhost:8080/getSuburb
        String methodPath = "getSuburb";
        String result = getData(methodPath).replaceAll("[^\\d.]", "");
        return result;
    }

    public static String getSuburbByAddress(String suburb) {
        // http://localhost:8080/getSuburb
        String methodPath = "getSuburbByName/\"" + suburb + ",_____\"";
        String result = "";
        String connReturn = getData(methodPath);
        if (!connReturn.equals("[]")) {
            result = connReturn.replaceAll("[^\\d.]", "").substring(0, 4);
        }
        return result;
    }

    public static String getExerciseByName(String name) {
        // http://localhost:8080/getSuburb
        String methodPath = "getExerciseByName/\"" + name + "\"";
        String result = "";
        String connReturn = getData(methodPath);
        if (!connReturn.equals((""))) {
            result = connReturn;
        }
        return result;
    }

    public static String getSuburbScore(String suburbAndPostcode) {
        // http://localhost:8080/getSuburbScore/"Aubrey, 3393"
        String methodPath = "getSuburbScore/\"" + suburbAndPostcode + "\"";
        String result = getData(methodPath).replaceAll("[^\\d.]", "");
        return result;
    }

    public static String getSuburbIndicator(String suburbAndPostcode) {
        // http://localhost:8080/getSuburbScore/"Aubrey, 3393"
        String methodPath = "getSuburbIndicator/\"" + suburbAndPostcode + "\"";
        String result = getData(methodPath).replaceAll("[^a-zA-Z ]", "").substring(9);
        return result;
    }

//    '/getLocationPercentage/:SuburbPostcode'
//    [{"LGA":"Melbourne","Location":"Beach Foreshore","Percentage":0.004269672516118014},
//    {"LGA":"Melbourne","Location":"Other Open Space","Percentage":2.0238247726399385},
//    {"LGA":"Melbourne","Location":"Other Rec./Sports","Percentage":2.228769053413603},
//    {"LGA":"Melbourne","Location":"Parkland/Reserve","Percentage":3.7445027966354982},
//    {"LGA":"Melbourne","Location":"Sport Area/Facility","Percentage":3.7487724691516164},
//    {"LGA":"Melbourne","Location":"Street/Lane/Footpath","Percentage":88.1943554929337},
//    {"LGA":"Melbourne","Location":"Vacant Block","Percentage":0.05550574270953418}]

    public static String getLocationPercentage(String suburbAndPostcode) {
        // http://localhost:8080/getLocationPercentage/"Aubrey, 3393"
        String methodPath = "getLocationPercentage/\"" + suburbAndPostcode + "\"";
        String dataReturn = getData(methodPath);
        String result = dataReturn;
        return result;
    }

//    public static void postData(String urlStr, String data) {
//        URL url = null;
//        HttpURLConnection conn = null;
//        try {
//            url = new URL(BASE_URL + urlStr);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setFixedLengthStreamingMode(data.getBytes().length);
//            conn.setRequestProperty("Content-Type", "application/json");
//            PrintWriter out = new PrintWriter(conn.getOutputStream());
//            out.print(data);
//            out.close();
//            int responseCode = conn.getResponseCode();
//            Log.i("error", new Integer(responseCode).toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            conn.disconnect();
//        }
//    }

//    public static void updateData(String urlStr, String id, String data) {
//        URL url = null;
//        HttpURLConnection conn = null;
//        try {
//            url = new URL(BASE_URL + urlStr + "/" + id);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("PUT");
//            conn.setDoOutput(true);
//            conn.setFixedLengthStreamingMode(data.getBytes().length);
//            conn.setRequestProperty("Content-Type", "application/json");
//            PrintWriter out = new PrintWriter(conn.getOutputStream());
//            out.print(data);
//            out.close();
//            int responseCode = conn.getResponseCode();
//            Log.i("error", new Integer(responseCode).toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            conn.disconnect();
//        }
//    }


//    public static int getMaxId(String type) {
//        int id = 0;
//        URL url = null;
//        String idStr = "";
//        String idUrlStr = "";
//
//        switch (type) {
//            case "user":
//                idUrlStr = "calorietracker.users/count/";
//                break;
//            case "cred":
//                idUrlStr = "calorietracker.credential/count/";
//                break;
//            case "consumption":
//                idUrlStr = "calorietracker.consumption/count/";
//                break;
//            case "food":
//                idUrlStr = "calorietracker.food/count/";
//                break;
//            case "report":
//                idUrlStr = "calorietracker.report/count/";
//                break;
//        }
//
//        HttpURLConnection conn = null;
//
//        String urlStr = BASE_URL + idUrlStr;
//        try {
//            url = new URL(urlStr);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "text/plain");
//            conn.setRequestProperty("Accept", "text/plain");
//            Scanner inStream = new Scanner(conn.getInputStream());
//            while (inStream.hasNextLine()) {
//                idStr = inStream.nextLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            conn.disconnect();
//        }
//        id = Integer.parseInt(idStr);
//        return id;
//    }

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

