package com.example.deitapp2;

public class Record {
    public String weight;
    public String bodyFat;
    public String BMI;
    public String metabolism;

    public Record() {

    }

    public Record(String weight, String bodyFat, String bmi, String metabolism) {
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.BMI = bmi;
        this.metabolism = metabolism;
    }
}
