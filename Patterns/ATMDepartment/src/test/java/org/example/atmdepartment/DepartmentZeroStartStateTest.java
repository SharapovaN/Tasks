package org.example.atmdepartment;

import org.junit.Test;

public class DepartmentZeroStartStateTest {

    @Test
    public void DepartmentZeroStartStateCreationTest() {
        DepartmentZeroStartState department = new DepartmentZeroStartState();
        if (department.getSumm() != 0 && department.getSize() != 4) {
            throw new RuntimeException("Something wrong with DepartmentZeroStartStateCreationTest");
        }
    }


    @Test
    public void DepartmentZeroStartStateAddBanknoteTest() {
        DepartmentZeroStartState department = new DepartmentZeroStartState();
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};
        department.get(0).addBanknotes(banknotesForAdding);
        if (department.getSumm() != 100) {
            throw new RuntimeException("Something wrong with DepartmentZeroStartStateAddBanknoteTest");
        }
    }

    @Test
    public void DepartmentZeroStartStateWithdrawingBanknotesTest() {
        DepartmentZeroStartState department = new DepartmentZeroStartState();
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};
        department.get(0).addBanknotes(banknotesForAdding);
        department.summa();
        department.get(0).withdrowBankNotes(100);
        if (department.getSumm() != 0) {
            throw new RuntimeException("Something wrong with DepartmentZeroStartStateWithdrawingBanknotesTest");
        }
    }

}