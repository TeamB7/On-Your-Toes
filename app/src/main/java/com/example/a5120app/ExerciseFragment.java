package com.example.a5120app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

import androidx.fragment.app.Fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ExerciseFragment extends Fragment {
    private View view;
    private Button logBtn;
    private WebView exerciseImg;
    private TextView exerciseTv;
    private String exerciseName = "";
    private PopupWindow popupWindow;
    private HashMap<String, String> exerciseHashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercise, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String exercise = bundle.getString("exercise", null);
            exerciseName = exercise;
        }
        exerciseImg = view.findViewById(R.id.exercise_image);
        exerciseTv = view.findViewById(R.id.exercise_tv);
        logBtn = view.findViewById(R.id.log_exercise_btn);

        GetExerciseAsyncTask getExerciseAsyncTask = new GetExerciseAsyncTask();
        getExerciseAsyncTask.execute();

        final Context context = getContext();
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.pop_up_window, null);

                popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        //Close the window when clicked
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });


        return view;
    }

    //[{"Exercise":"Arnold Press",
// "Equipment":"Dumbbells",
// "ExerciseType":"Weight",
// "MuscleGroup":"Arms - Bicep,Shoulders",
// "Notes":"","Modifications":"",
// "AlternateEquipments":"Filled water bottle 1L = 1Kg, band",
// "Sets":"2-3","Reps":"10-12",
// "Example":"giphy-2.gif (https://dl.airtable.com/FvaObZ1SyqwBU12Wx4K7_giphy-2.gif)"}]
    private void getData(String exerciseString) throws JSONException {
        JSONArray exerciseJson = new JSONArray(exerciseString);
        exerciseHashMap = new HashMap<String, String>();
        String exerciseName = exerciseJson.getJSONObject(0).getString("Exercise");
        String type = exerciseJson.getJSONObject(0).getString("ExerciseType");
        String muscleGroup = exerciseJson.getJSONObject(0).getString("MuscleGroup");
        String modifications = exerciseJson.getJSONObject(0).getString("Modifications");
        String sets = exerciseJson.getJSONObject(0).getString("Sets");
        String reps = exerciseJson.getJSONObject(0).getString("Reps");
        String gif = exerciseJson.getJSONObject(0).getString("Example");
        String gifUrl = gif.substring(gif.indexOf("(") + 1, gif.indexOf(")"));

        exerciseHashMap.put("name", exerciseName);
        exerciseHashMap.put("type", type);
        exerciseHashMap.put("muscleGroup", muscleGroup);
        exerciseHashMap.put("modifications", modifications);
        exerciseHashMap.put("sets", sets);
        exerciseHashMap.put("reps", reps);
        exerciseHashMap.put("gifUrl", gifUrl);
    }

    private class GetExerciseAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String exerciseString) {
            try {
                getData(exerciseString);
                exerciseTv.setText(exerciseString);
                exerciseImg.loadUrl(exerciseHashMap.get("gifUrl"));
                exerciseImg.getSettings().setLoadWithOverviewMode(true);
                exerciseImg.getSettings().setUseWideViewPort(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = RestClient.getExerciseByName(exerciseName);
            return result;
        }
    }
}
