package com.mrwhoknows.gocorona;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiHolder apiHolder;
    private TextView total, active, indian, foreigners, dead, cured;
    private ListView listView;
    private ArrayList<CoronaData.Data.Regional> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       findViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rootnet.in/covid19-in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiHolder = retrofit.create(ApiHolder.class);

        fetchData();

    }

    private void findViews(){
        listView = findViewById(R.id.listView);
        total = findViewById(R.id.totalCasesRes);
        active = findViewById(R.id.totalActiveCasesRes);
        indian = findViewById(R.id.indianCasesRes);
        foreigners = findViewById(R.id.foreignersRes);
        dead = findViewById(R.id.deadRes);
        cured = findViewById(R.id.curedRes);
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

                    int activeCases = coronaData.getData().getSummary().getTotalCases() -
                            (coronaData.getData().getSummary().getDeaths() + coronaData.getData().getSummary().cured);

                    total.setText(String.valueOf(coronaData.getData().getSummary().getTotalCases()));
                    indian.setText(String.valueOf(coronaData.getData().getSummary().getIndianCases()));
                    active.setText(String.valueOf(activeCases));
                    foreigners.setText(String.valueOf(coronaData.getData().getSummary().getForeignCases()));
                    dead.setText(String.valueOf(coronaData.getData().getSummary().getDeaths()));
                    cured.setText(String.valueOf(coronaData.getData().getSummary().getCured()));



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
