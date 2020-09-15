package org.example.atmdepartment;
/*
Были реализованы следующие паттерны:
FactoryMethod;
Command;
Memento;

В приложении можно создать два вида департаментов:
  -департамент банкоматов с несколькими банкоматами, каждый из которых имеет ненулевую начальную сумму денег;
  -департамент банкоматов с несколькими банкоматами, каждый из которых имеет нулевую начальную сумму денег;
 */

public class Main {
    public static void main(String[] args) {
        BankNoteImpl[] banknotesForAdding = new BankNoteImpl[]{new BankNoteImpl(FaceValue.HUNDRED)};

        Configuration config;
        config = ConfigurationFactory.getConfiguration("WithStartState");

        State abc = new State(config.params());
        Originator originator = new Originator();
        originator.saveState(abc);

        Executor executor = new Executor();
        executor.addCommand(new AskSumm(abc.getArray()));
        executor.addCommand(new AskSize(abc.getArray()));
        abc.getArray().get(0).addBanknotes(banknotesForAdding);

        executor.addCommand(new AskSumm(abc.getArray()));
        abc.getArray().get(0).addBanknotes(banknotesForAdding);

        executor.addCommand(new AskSumm(abc.getArray()));
        abc.getArray().get(0).addBanknotes(banknotesForAdding);

        executor.addCommand(new AskSumm(abc.getArray()));

        abc = originator.restoreState();
        executor.addCommand(new AskSumm(abc.getArray()));

        executor.executeCommands();
    }
}
