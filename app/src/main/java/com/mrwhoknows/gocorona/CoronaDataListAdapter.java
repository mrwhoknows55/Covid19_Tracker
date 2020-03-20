package com.mrwhoknows.gocorona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class CoronaDataListAdapter extends ArrayAdapter<CoronaData.Data.Regional> {

    private static final String TAG = "CoronaDataListAdapter";
    private Context context;
    private int resource;

    public CoronaDataListAdapter(@NonNull Context context, int resource,  @NonNull ArrayList<CoronaData.Data.Regional> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String loc = getItem(position).getLoc();
        int indianCases = getItem(position).getIndianCases();
        int foreignCases = getItem(position).getForeignCases();
        int cured = getItem(position).getCured();
        int deaths = getItem(position).getDeaths();

        CoronaData.Data.Regional regional = new CoronaData.Data.Regional(loc,indianCases,foreignCases,cured,deaths);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,  false);

        TextView location = convertView.findViewById(R.id.text1);
        TextView cases = convertView.findViewById(R.id.text2);
        TextView deathsText = convertView.findViewById(R.id.text4);
        TextView curedText = convertView.findViewById(R.id.text5);

        location.setText(loc);
        cases.setText(String.valueOf(indianCases+foreignCases));
        curedText.setText(String.valueOf(cured));
        deathsText.setText(String.valueOf(deaths));

        return convertView;
    }
}
