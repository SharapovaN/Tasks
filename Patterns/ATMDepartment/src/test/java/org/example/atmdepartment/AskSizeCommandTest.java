package org.example.atmdepartment;

import org.junit.Test;

public class AskSizeCommandTest {
    @Test
    public void AskSizeCommandTest() {
        DepartmentWithStartState department = new DepartmentWithStartState();
        AskSize sizeCommand = new AskSize(department);
        if(!sizeCommand.execute().equals(department.size())){
            throw new RuntimeException("Something wrong with AskSizeTest");
        }
    }


}