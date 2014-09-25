package com.exist.utility.editMenu.subMenu;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.EmployeeDataValidation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditBirthDate extends EditMenu {

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();

        Date birthDate = validate.dateString("Birthdate: ");

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":birthDate", birthDate);
        namedParameter.put(":id", Integer.valueOf(employeeNumberInput));

        hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("UPDATE_birthDate"), namedParameter);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
