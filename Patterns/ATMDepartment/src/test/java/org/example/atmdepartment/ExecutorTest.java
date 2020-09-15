package org.example.atmdepartment;

import org.junit.Test;

public class ExecutorTest {

    @Test
    public void ExecutorAskSummCommandTest() {
        DepartmentWithStartState department = new DepartmentWithStartState();
        Executor executor = new Executor();
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};

        executor.addCommand(new AskSumm(department));

        department.get(0).addBanknotes(banknotesForAdding);
        executor.addCommand(new AskSumm(department));

        department.get(0).addBanknotes(banknotesForAdding);
        executor.addCommand(new AskSumm(department));

        department.get(0).addBanknotes(banknotesForAdding);
        executor.addCommand(new AskSumm(department));

        if (executor.size() != 4) {
            throw new RuntimeException("Something wrong with ExecutorTest addCommands method");
        }
        executor.executeCommands();

        if (executor.getCounter() != 4) {
            throw new RuntimeException("Something wrong with ExecutorTest executeCommands method");
        }
    }

}