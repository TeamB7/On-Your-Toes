package com.example.a5120app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import androidx.fragment.app.Fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class ExerciseFragment extends Fragment {
    private View view;
    private Button logBtn, finishBtn, likeBtn;
    private WebView exerciseImg;
    private TextView exerciseTv, dateTv;
    private EditText setsEd, repsEd;
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

                likeBtn = popupView.findViewById(R.id.like_btn);
                finishBtn = popupView.findViewById(R.id.finish_btn);
                dateTv = popupView.findViewById(R.id.date_tv);
                setsEd = popupView.findViewById(R.id.sets_ed);
                repsEd = popupView.findViewById(R.id.reps_ed);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                final String formattedDate = df.format(c);

                dateTv.setText(formattedDate);

                likeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                finishBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getActivity().getSharedPreferences("Exercise", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();

                        String sets = setsEd.getText().toString().trim();
                        String reps = repsEd.getText().toString().trim();

                        JSONArray jsonArray = new JSONArray();
                        jsonArray.put(exerciseName);
                        jsonArray.put(sets);
                        jsonArray.put(reps);
                        jsonArray.put(formattedDate);

                        int index = sp.getInt("Index", 0);
                        int newIndex = index + 1;

                        if (index == 3) {
                            String str = sp.getString("1", null);
                            ed.putString("0", sp.getString("1", null));
                            ed.putString("1", sp.getString("2", null));
                            ed.putString("2", jsonArray.toString());

                            str = sp.getString("1", null);
                        } else {
                            ed.putInt("Index", newIndex);
                            ed.putString(String.valueOf(index), jsonArray.toString());
                        }

                        String str = sp.getString("2", null);

                        ed.commit();
                        popupWindow.dismiss();
                    }

                });
            }
        });

        return view;
    }

    //    [{"Exercise":"Burpee","ExerciseType":"Plyo",
//    "MuscleGroup":"Full Body",
//    "Modifications":"Easier: Don't Jump after, and break down motion squat, step legs back and no pushup\nHarder: Speed",
//    "Sets":"1-2","Reps":"10-12","Example":"https://dl.airtable.com/xDZ3bhDQqG3erLNNwgyF_Burpee.gif","e_id":3,"Likes":null,
//    "Image":{"type":"Buffer","data":[]}}]
    private void getData(String exerciseString) throws JSONException {
        JSONArray exerciseJson = new JSONArray(exerciseString);
        exerciseHashMap = new HashMap<String, String>();
        String exerciseName = exerciseJson.getJSONObject(0).getString("Exercise");
        String type = exerciseJson.getJSONObject(0).getString("ExerciseType");
        String muscleGroup = exerciseJson.getJSONObject(0).getString("MuscleGroup");
        String modifications = exerciseJson.getJSONObject(0).getString("Modifications");
        String sets = exerciseJson.getJSONObject(0).getString("Sets");
        String reps = exerciseJson.getJSONObject(0).getString("Reps");
        String gifUrl = exerciseJson.getJSONObject(0).getString("Example");

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
                logBtn.setVisibility(View.VISIBLE);
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
