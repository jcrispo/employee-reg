package com.exist.utility.editMenu.subMenu;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.EmployeeDataValidation;

import java.util.HashMap;
import java.util.Map;

public class EditMiddleName extends EditMenu {

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();

        String middleName = validate.name("Middle Name: ");

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":middleName", middleName);
        namedParameter.put(":id", Integer.valueOf(employeeNumberInput));

        hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("UPDATE_middleName"), namedParameter);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
