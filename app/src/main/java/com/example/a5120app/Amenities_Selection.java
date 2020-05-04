package com.example.a5120app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Amenities_Selection extends AppCompatActivity {
    ListView listView;
    String mAmentity_type[]= {"Tennis","Park","Yoga", "Pilates"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities__selection);
        listView= findViewById(R.id.lv_amenities);

        MyAdapter adapter = new MyAdapter(this,mAmentity_type);

        //  set On Click action
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                    // passing clicked info to fragment maps using Bundle
                    Intent intent= new Intent(getApplicationContext(),MapsFragment.class);
                    Bundle bundle= new Bundle();
                    intent.putExtra("Suburb",mAmentity_type[0]);
                    intent.putExtra("position",""+0);
                    startActivity(intent);

                }
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String rAmenity_type[];

        MyAdapter (Context c,String amenity[]) {
            super(c,R.layout.list_row,R.id.tv_facility,amenity);
            this.context=c;
            this.rAmenity_type=amenity;

        }
        // Inflate list view with layout from List_row.xml
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row =layoutInflater.inflate(R.layout.list_row,parent,false);
            TextView mText = row.findViewById(R.id.tv_facility);

            mText.setText(rAmenity_type[position]);

            return row;
        }
    }


}
