package org.example.atmdepartment;

import java.util.ArrayList;

public class DepartmentWithStartState implements Department {
    private ArrayList<ATMImpl> department = new ArrayList<>();
    private int size = 0;
    private int summ = 0;

    DepartmentWithStartState() {
        int n = (int) (1 + Math.random() * 5);
        for (int i = 0; i < n; i++) {
            ATMImpl atm = new ATMImpl();
            CellImpl hundredCell = new CellImpl(FaceValue.HUNDRED);
            CellImpl fiveThousandCell = new CellImpl(FaceValue.FIVETHOUSAND);
            CellImpl thousandCell = new CellImpl(FaceValue.THOUSAND);
            CellImpl fiveHundredCell = new CellImpl(FaceValue.FIVEHUNDRED);

            atm.addCell(hundredCell.getFaceValue(), hundredCell);
            atm.addCell(fiveHundredCell.getFaceValue(), fiveHundredCell);
            atm.addCell(thousandCell.getFaceValue(), thousandCell);
            atm.addCell(fiveThousandCell.getFaceValue(), fiveThousandCell);

            BankNoteImpl[] banknotes = new BankNoteImpl[(int) (1 + Math.random() * 9)];
            BankNoteImpl[] possibleBankNotes = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED), new BankNoteImpl(FaceValue.FIVEHUNDRED),
                    new BankNoteImpl(FaceValue.THOUSAND), new BankNoteImpl(FaceValue.FIVETHOUSAND)};
            for (int k = 0; k < banknotes.length; k++) {
                int j = (int) (Math.random() * 4);
                banknotes[k] = possibleBankNotes[j];
            }
            atm.addBanknotes(banknotes);
            department.add(atm);
            size++;
        }
    }

    public int summa() {
        summ = 0;
        for (int i = 0; i < size; i++) {
            summ += department.get(i).summ();
        }
        return summ;
    }

    public int getSumm() {
        return summa();
    }

    public int getSize() {
        return size;
    }

    public int size() {
        return size;
    }

    public ATMImpl get(int i) {
        ATMImpl temp = new ATMImpl();
        if (i < department.size()) {
            temp = department.get(i);
        }
        return temp;
    }
}
