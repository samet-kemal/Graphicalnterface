package com.samet.graphicalnterface;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class BloodPressure extends RealmObject {

    private String HighBloodPressure;
    private String LowBloodPressure;


    @Override
    public String toString() {
        return "BloodPressure{" +
                "HighBloodPressure='" + HighBloodPressure + '\'' +
                ", LowBloodPressure='" + LowBloodPressure + '\'' +
                '}';
    }

    public String getHighBloodPressure() {
        return HighBloodPressure;
    }

    public void setHighBloodPressure(String highBloodPressure) {
        HighBloodPressure = highBloodPressure;
    }

    public String getLowBloodPressure() {
        return LowBloodPressure;
    }

    public void setLowBloodPressure(String lowBloodPressure) {
        LowBloodPressure = lowBloodPressure;
    }
}
