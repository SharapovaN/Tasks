package org.example.atmdepartment;

public enum FaceValue {
    HUNDRED(100),
    FIVEHUNDRED(500),
    THOUSAND(1000),
    FIVETHOUSAND(5000);

    private final int value;

    FaceValue(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}

