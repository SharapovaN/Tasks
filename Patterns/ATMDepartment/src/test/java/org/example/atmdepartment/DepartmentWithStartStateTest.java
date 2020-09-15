package org.example.atmdepartment;

import org.junit.Test;

public class DepartmentWithStartStateTest {
    @Test
    public void DepartmentWithStartStateCreationTest() {
        DepartmentWithStartState department = new DepartmentWithStartState();
        if (department.getSumm() == 0 && department.getSize() != 4) {
            throw new RuntimeException("Something wrong with DepartmentZeroStartStateCreationTest");
        }
    }


    @Test
    public void DepartmentWithStartStateAddBanknoteTest() {
        DepartmentWithStartState department = new DepartmentWithStartState();
        int temp = department.getSumm() + 100;
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};
        department.get(0).addBanknotes(banknotesForAdding);
        if (department.getSumm() != temp) {
            throw new RuntimeException("Something wrong with DepartmentZeroStartStateAddBanknoteTest");
        }
    }

    @Test
    public void DepartmentWithStartStateWithdrawingBanknotesTest() {
        DepartmentWithStartState department = new DepartmentWithStartState();
        int temp = department.getSumm();
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};
        department.get(0).addBanknotes(banknotesForAdding);
        department.get(0).withdrowBankNotes(100);
        if (department.getSumm() != temp) {
            throw new RuntimeException("Something wrong with DepartmentZeroStartStateWithdrawingBanknotesTest");
        }
    }

}