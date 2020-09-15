package org.example.atmdepartment;

public interface ATM {

    void addCell(FaceValue faceValue, CellImpl cell);

    void addBanknotes(BankNoteImpl[] bankNotes);

    int summ();

    String withdrowBankNotes(int wanted);
}

