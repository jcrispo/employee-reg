package com.exist.utility.editMenu.subMenu;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.EmployeeDataValidation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditHireDate extends EditMenu {

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();

        Date hireDate = validate.dateString("Hire Date: ");

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":hireDate", hireDate);
        namedParameter.put(":id", Integer.valueOf(employeeNumberInput));

        hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("UPDATE_hireDate"), namedParameter);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
