package org.example.atmdepartment;

import org.junit.Assert;
import org.junit.Test;

public class AskSummCommandTest {
    @Test
    public void AskSummCommandTest() {
        DepartmentWithStartState department = new DepartmentWithStartState();
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};
        AskSumm sumCommand = new AskSumm(department);
        Assert.assertTrue(sumCommand.execute().equals(department.getSumm()));

        int newSumm = department.getSumm() + 100;
        department.get(0).addBanknotes(banknotesForAdding);
        AskSumm sumCommand2 = new AskSumm(department);
        Assert.assertTrue(sumCommand2.execute().equals(newSumm));

    }
}