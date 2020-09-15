package org.example.atmdepartment;

import java.util.ArrayList;

public class DepartmentZeroStartState implements Department {
    private ArrayList<ATMImpl> department = new ArrayList<>();
    private int size = 0;
    private int summ = 0;

    DepartmentZeroStartState() {
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

            department.add(atm);
            size++;
        }
    }

    @Override
    public int summa() {
        summ = 0;
        for (int i = 0; i < size; i++) {
            summ += department.get(i).summ();
        }
        return summ;
    }

    @Override
    public ATMImpl get(int i) {
        ATMImpl temp = new ATMImpl();
        if (i < department.size()) {
            temp = department.get(i);
        }
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    public int getSize() {
        return size();
    }

    public int getSumm() {
        return summa();
    }
}
