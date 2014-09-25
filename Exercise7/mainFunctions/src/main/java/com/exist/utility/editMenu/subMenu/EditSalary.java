package com.exist.utility.editMenu.subMenu;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.EmployeeDataValidation;

import java.util.HashMap;
import java.util.Map;

public class EditSalary extends EditMenu {

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();

        Long salary = Long.valueOf(validate.number("Salary: "));

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":salary", salary);
        namedParameter.put(":id", Integer.valueOf(employeeNumberInput));

        hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("UPDATE_salary"), namedParameter);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
