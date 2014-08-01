package com.exist.employeeInfoPackage;

import java.util.Calendar;
import java.util.GregorianCalendar;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class EmployeeData{
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String position;
    private Integer employeeID;
    private static Integer eID = 0;
    private Calendar birthDate = new GregorianCalendar();

    public void setFirstName(String fName){
        firstName = fName;
    }

    public void setMiddleName(String mName){
        middleName = mName;
    }

    public void setLastName(String lName){
        lastName = lName;
    }

    public void setBirthDay(Integer bDay){
        birthDate.set(DATE, bDay);
    }

    public void setBirthMonth(Integer bMonth){
        birthDate.set(MONTH, bMonth-1);
    }

    public void setBirthYear(Integer bYear){
        birthDate.set(YEAR, bYear);
    }

    public void setBirthHour(Integer bHour){
        birthDate.set(HOUR_OF_DAY, bHour);
    }

    public void setBirthMinute(Integer bMinute){
        birthDate.set(MINUTE, bMinute);
    }

    public void setGender(String tempGender){
        gender = tempGender;
    }

    public void setPosition(String tempPosition){
        position = tempPosition;
    }

    public void setEmployeeID(Integer tempEmployeeID){
        employeeID = tempEmployeeID;
    }

    public void setID(){
        eID++; employeeID = eID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getMiddleName(){
        return middleName;
    }

    public String getLastName(){
        return lastName;
    }

    public Calendar getBirthDate(){
        return birthDate;
    }

    public String getGender(){
        return gender;
    }
	
    public String getPosition(){
        return position;
    }
	
    public Integer getEmployeeID(){
        return employeeID;
    }

}
