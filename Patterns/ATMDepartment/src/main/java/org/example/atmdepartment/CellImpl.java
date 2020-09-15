package org.example.atmdepartment;

import java.util.ArrayList;

public class CellImpl implements Cell {
    private final FaceValue faceValue;
    private ArrayList<BankNoteImpl> bankNotes = new ArrayList<BankNoteImpl>();
    private int count = 0;

    public CellImpl(FaceValue faceValue) {
        this.faceValue = faceValue;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    public void add(BankNoteImpl bankNote) {
        if (faceValue.equals(bankNote.getFaceValue()))
            bankNotes.add(bankNote);
            count = bankNotes.size();
    }

   public BankNoteImpl withdraw() {
        BankNoteImpl returnBankNote = null;
            if (count > 0) {
                returnBankNote = bankNotes.remove(bankNotes.size() - 1);
                count -= 1;

        }
        return returnBankNote;
    }

    public int getCount() {
        return count;
    }
}
