package com.mrwhoknows.gocorona;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiHolder apiHolder;
    private ListView listView;
    private ArrayList<CoronaData.Data.Regional> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rootnet.in/covid19-in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiHolder = retrofit.create(ApiHolder.class);

        fetchData();

    }

    private void fetchData(){
        Call<CoronaData> dataCall = apiHolder.getData();
        dataCall.enqueue(new Callback<CoronaData>() {
            @Override
            public void onResponse(Call<CoronaData> call, Response<CoronaData> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "CODE: "+response.code(), Toast.LENGTH_SHORT).show();
                }
                CoronaData coronaData = response.body();
                if (coronaData.isSuccess()) {
                    Log.i("RES ref", coronaData.getLastRefreshed());
                    Log.i("RES Sum TotalCases", String.valueOf(coronaData.getData().getSummary().getTotalCases()));
                    Log.i("RES Sum Indians", String.valueOf(coronaData.getData().getSummary().getIndianCases()));
                    Log.i("RES Sum fore", String.valueOf(coronaData.getData().getSummary().getForeignCases()));
                    Log.i("RES Sum Cured", String.valueOf(coronaData.getData().getSummary().getCured()));
                    Log.i("RES Sum Death", String.valueOf(coronaData.getData().getSummary().getDeaths()));

                    for (CoronaData.Data.Regional regional : coronaData.getData().getRegional()){
                        arrayList.add(regional);
                        Log.i("RES loc", regional.getLoc());
                        Log.i("RES Indians", String.valueOf(regional.getIndianCases()));
                        Log.i("RES Fore", String.valueOf(regional.getForeignCases()));
                        Log.i("RES Cure", String.valueOf(regional.getCured()));
                        Log.i("RES Death", String.valueOf(regional.getDeaths()));
                    }

                    CoronaDataListAdapter adapter = new  CoronaDataListAdapter(MainActivity.this, R.layout.items,arrayList);
                    listView.setAdapter(adapter);
                }else {
                    Toast.makeText(MainActivity.this, "Temporary Not Available!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CoronaData> call, Throwable t) {
                Log.i("RES error", t.getCause().toString());
                Toast.makeText(MainActivity.this, "ERROR: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
