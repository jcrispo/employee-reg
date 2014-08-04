package com.exist.addViewPackage;

import com.exist.employeeInfoPackage.EmployeeDataValidation;
import com.exist.employeeInfoPackage.EmployeeData;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class AddEmployeeData{
    private static String tempString;
    private static EmployeeDataValidation validate;
    private static Scanner dataIn;

    public AddEmployeeData () {
        dataIn = new Scanner(System.in);
        validate = new EmployeeDataValidation();
    }

    public EmployeeData addEmpData (EmployeeData data) {
        data.setID();
        data.setFirstName(validate.name("Input First Name: "));
        data.setMiddleName(validate.name("Input Middle Name: "));
        data.setLastName(validate.name("Input Last Name: "));
        data.setBirthYear(validate.birthDate("Input Birth Year 'YYYY': ", "year"));
        data.setBirthMonth(validate.birthDate("Input Birth Month (1~12) 'M': ", "month"));
        data.setBirthDay(validate.birthDate("Input Birth Date (1~31) 'd': ", "day"));
        data.setBirthHour(validate.birthDate("Input Birth Hour: ", "hour"));
        data.setBirthMinute(validate.birthDate("Input Birth Minute: ", "minutes"));
        data.setGender(validate.gender("Input Gender | 'm' for Male & 'f' for Female: "));
        data.setPosition(validate.position("Input Position: "));
        return data;
    }

    public List<Integer> updateEmployeeIDList (List<Integer> employeeIDList, Map<Integer, EmployeeData> employeeDatabase) {
        employeeIDList.clear();
        for (Integer employeeID : employeeDatabase.keySet()) {
            employeeIDList.add(employeeID);
        }
        return employeeIDList;
    }

}
