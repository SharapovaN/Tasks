package org.example.atmdepartment;

public class AskSize implements Command {
    private Department department;

    AskSize(Department department){
        this.department=department;
    }
    @Override
    public Object execute() {
        System.out.println("Количество банкоматов в департаменте : "+department.size());
        return department.size();
    }
}
