package org.example.atmdepartment;

public class BankNoteImpl implements Banknote {
    private final FaceValue faceValue;

    public BankNoteImpl(FaceValue faceValue){
        this.faceValue=faceValue;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }
}
