package com.exist.editDeletePackage;

import com.exist.employeeInfoPackage.EmployeeDataValidation;
import com.exist.addViewPackage.ViewEmployeeData;
import com.exist.employeeInfoPackage.EmployeeData;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Formatter;

public class EditEmployeeData{
    private static EmployeeDataValidation validate;
    private static ViewEmployeeData viewData;
    private static Scanner inputEdit;
    private static DateFormat dateFormat;
    private static final String MENU = "m";
    private static final int FIRSTNAME = 1;
    private static final int MIDDLENAME = 2;
    private static final int LASTNAME = 3;
    private static final int BIRTHDAY = 5;
    private static final int GENDER = 4;
    private static final int POSITION = 6;
    private static final int EXIT = 7;

    public EditEmployeeData () {
        validate = new EmployeeDataValidation();
        viewData = new ViewEmployeeData();
        inputEdit = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
    }

    public Map<Integer, EmployeeData> editEmpData (List<Integer> employeeIDStorage, Map<Integer, EmployeeData> employeeDatabase) {
        Integer idKey;
        int editOption = 0;
        boolean exitEdit = false;
        String tempID;
        String tempFName;
        String tempMName;
        String tempLName;
        String tempGender;
        String tempBirthDate;
        String tempPosition;
        String backToMenu;
        String secondEditOption;

        while (!exitEdit) {
            System.out.print("\nInput Employee ID to Edit the Employee Information or 'm' to go back to the Menu: ");
            backToMenu = inputEdit.nextLine();
            if (backToMenu.trim().length() == 0) {
                System.out.println("\nNo data was given!");
            } else if (backToMenu.equals(MENU)) {
                exitEdit = true;
            }else{
                try{    
                    idKey = Integer.valueOf(backToMenu);
                    boolean exitSecondEditOptions = false;
                    while (!exitSecondEditOptions) {
                        viewData.viewEmployeeByID(idKey,employeeDatabase);
                        System.out.print("\nChoose Field to Edit: (1) First Name, (2) Middle Name, (3) Last Name, (4) Gender, (5) Birth Date, (6) Position, (7) Cancel : ");
                        secondEditOption = (inputEdit.nextLine()).trim();
                        if (validate.onlyNumbers(secondEditOption)){
                            editOption = Integer.parseInt(secondEditOption);
                        } else {
                            editOption = 0;
                        }
                        switch (editOption) {
                            case LASTNAME: 
                                employeeDatabase.get(idKey).setLastName(validate.name("Input Last Name: ")); 
                                break;
                            case FIRSTNAME:
                                employeeDatabase.get(idKey).setFirstName(validate.name("Input First Name: "));
                                break;
                            case MIDDLENAME:
                                employeeDatabase.get(idKey).setMiddleName(validate.name("Input Middle Name: ")); 
                                break;
                            case BIRTHDAY: 
                                employeeDatabase.get(idKey).setBirthYear(validate.birthDate("Input Birth Year 'YYYY': ", "year"));
                                employeeDatabase.get(idKey).setBirthMonth(validate.birthDate("Input Birth Month (1~12) 'M': ", "month"));
                                employeeDatabase.get(idKey).setBirthDay(validate.birthDate("Input Birth Date (1~31) 'd': ", "day"));
                                employeeDatabase.get(idKey).setBirthHour(validate.birthDate("Input Birth Hour: ", "hour"));
                                employeeDatabase.get(idKey).setBirthMinute(validate.birthDate("Input Birth Minute: ", "minutes"));
                                break;
                            case GENDER: 
                                employeeDatabase.get(idKey).setGender(validate.gender("Input Gender | 'm' for Male & 'f' for Female: ")); 
                                break;
                            case POSITION:
                                employeeDatabase.get(idKey).setPosition(validate.position("Input Position: ")); 
                                break;
                            case EXIT:
                                exitSecondEditOptions = true;
                                break;
                            default:
                                System.out.println("\nInvalid input! Please try again! ");
                                break;
                        }
                    }
                    viewData.viewEmpData(employeeIDStorage, employeeDatabase);
                } catch (NumberFormatException e) {
                        System.out.println("\nInvalid Input. Please Try Again!");
                } catch (NullPointerException e) {
                        System.out.println("\nEmployee ID does not exist");
                }
            }
        }
        return employeeDatabase;
    }

}

