package com.exist.utility.editMenu.subMenu;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.EmployeeDataValidation;

import java.util.HashMap;
import java.util.Map;

public class EditGender extends EditMenu {

    public void execute(String employeeNumberInput) {
        EmployeeDataValidation validate = new EmployeeDataValidation();
        HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();

        String gender = validate.gender("Gender 'm'/'f': ");

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":gender", gender);
        namedParameter.put(":id", Integer.valueOf(employeeNumberInput));

        hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("UPDATE_gender"), namedParameter);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
