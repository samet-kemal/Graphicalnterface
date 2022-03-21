package com.samet.graphicalnterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText highPressure,lowPressure;
    Button AddButton;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm=Realm.getDefaultInstance();
        Init();
        AddBlooodPressure();
        Show();
    }

    public void Init(){

        highPressure=(EditText) findViewById(R.id.highPressureEditText);
        lowPressure=(EditText)findViewById(R.id.lowPressureEditText);
        AddButton=(Button) findViewById(R.id.addButton);
        barChart=(BarChart) findViewById(R.id.barChart);

    }

    public void AddBlooodPressure(){
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        BloodPressure bloodPressure = realm.createObject(BloodPressure.class);
                        bloodPressure.setHighBloodPressure(highPressure.getText().toString());
                        bloodPressure.setLowBloodPressure(lowPressure.getText().toString());

                    }
                });
                Show();
                barChart.refreshDrawableState();
            }
        });
    }
    
    public void Show(){
        
        RealmResults<BloodPressure> list= realm.where(BloodPressure.class).findAll();

        float highTension = 0f;
        float lowTension=0f ;
        for (BloodPressure p : list) {
                highTension=highTension+Float.parseFloat(p.getHighBloodPressure());
                if (p.getLowBloodPressure()!=null){
                    lowTension =lowTension+Float.parseFloat(p.getLowBloodPressure());
                }else {
                    lowTension=8f;
                }

        }
        ArrayList<BarEntry> arrayList = new ArrayList<>();
        arrayList.add(new BarEntry(0,highTension));
        arrayList.add(new BarEntry(1,lowTension));

        BarDataSet barDataSet = new BarDataSet(arrayList,"Blood Pressure Graphic");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setContentDescription("Your Blood Pressure Graphic");
    }

}