package com.exist.utility.editMenu.subMenu;

import com.exist.database.HDBRetrieveManager;
import com.exist.database.HDBUpdateDeleteManager;
import com.exist.mainFunctions.EmployeeDataValidation;
import com.exist.mainFunctions.ViewEmployeeData;

import java.util.HashMap;
import java.util.Map;

public class EditPosition extends EditMenu {

    public void execute(String employeeNumberInput) {
        HDBUpdateDeleteManager hdbUpdateDeleteManager = new HDBUpdateDeleteManager();
        EmployeeDataValidation validate = new EmployeeDataValidation();
        ViewEmployeeData view = new ViewEmployeeData();

        view.showQueryNoLimit(HDBRetrieveManager.getQuery("S_ALL_Positions_JOIN_Departments_ORDER_position"));
        System.out.println("Enter only the Position ID.");
        Integer positionID = Integer.valueOf(validate.numericDataExists("Position: ", HDBRetrieveManager.getQuery("FROM_Positions_WHERE_ID")));

        Map<String, Object> namedParameter = new HashMap<String, Object>();

        namedParameter.put(":positionID", positionID);
        namedParameter.put(":id", Integer.valueOf(employeeNumberInput));

        hdbUpdateDeleteManager.updateDB(HDBRetrieveManager.getQuery("UPDATE_positionID"), namedParameter);
    }

    public boolean exitEditMenu() {
        return false;
    }

}
