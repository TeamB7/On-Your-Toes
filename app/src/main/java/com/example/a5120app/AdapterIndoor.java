package com.example.a5120app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterIndoor extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] name;
    private final String[] type;

    public AdapterIndoor(Activity context, String[] name, String[] type) {
        super(context, R.layout.listview_row, name);
        this.context = context;
        this.name = name;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listview_row, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.exercise_name_tv);

        TextView txtType = (TextView) rowView.findViewById(R.id.exercise_type_tv);
        txtTitle.setText(name[position]);

        txtType.setText(type[position]);
        return rowView;
    }
}
