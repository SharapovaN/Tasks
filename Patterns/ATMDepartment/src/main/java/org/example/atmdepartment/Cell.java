package org.example.atmdepartment;

public interface Cell {

    FaceValue getFaceValue();

    void add(BankNoteImpl bankNote);

    BankNoteImpl withdraw();

    int getCount();
}
