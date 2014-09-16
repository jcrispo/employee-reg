package com.exist.database;

import com.exist.database.utility.HDBUtility;
import com.exist.storage.CompanyData;
import com.exist.storage.Departments;
import com.exist.storage.Employee;
import com.exist.storage.EmployeeData;
import com.exist.storage.Positions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class HDBInsertManager {

    public void insertEmployeeData(EmployeeData employeeData) {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Employee employee = new Employee();
            CompanyData companyData = new CompanyData();

            companyData.setPositionID(employeeData.getPosition());
            companyData.setHireDate(employeeData.getHireDate());
            companyData.setSalary(employeeData.getSalary());
            companyData.setEmail(employeeData.getEmail());

            employee.setFirstName(employeeData.getFirstName());
            employee.setMiddleName(employeeData.getMiddleName());
            employee.setLastName(employeeData.getLastName());
            employee.setGender(employeeData.getGender());
            employee.setBirthDate(employeeData.getBirthDate());

            employee.setCompanyData(companyData);
            companyData.setEmployee(employee);

            session.save(employee);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void insertPosition(String position, int deptId) {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Positions positions = new Positions();
            positions.setPosition(position);
            positions.setDeptId(deptId);

            session.save(positions);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    public void insertNewDept(String deptName) {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Departments departments = new Departments();

            departments.setDepartment(deptName);

            session.save(departments);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    public void addRandomData() {
        HDBInsertManager hdbin = new HDBInsertManager();

        hdbin.populateDepartments();
        hdbin.populatePositions();
        hdbin.populateEmployeeData();
    }

    public void populateEmployeeData() {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String[] gender = {"male", "female"};
            String[] hireDate = {"2010-05-13", "2013-09-01", "2014-01-29", "2005-04-07", "2003-08-25", "2007-07-17", "2012-03-11", "2000-12-03", "2009-07-06", "2004-10-23", "2002-06-04"};
            Integer[] positionNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 11, 12, 13};
            String path1 = "src/main/resources/firstnames.txt";
            String path2 = "src/main/resources/middlenames.txt";
            String path3 = "src/main/resources/lastnames.txt";
            String path4 = "src/main/resources/birthdates.txt";

            Random random = new Random();
            int emailNumber = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            File file1 = new File(path1);
            FileReader fr1 = new FileReader(file1);
            BufferedReader br1 = new BufferedReader(fr1);
            for (String firstName; (firstName = br1.readLine()) != null; ) {

                File file2 = new File(path2);
                FileReader fr2 = new FileReader(file2);
                BufferedReader br2 = new BufferedReader(fr2);
                for (String middleName; (middleName = br2.readLine()) != null; ) {

                    File file3 = new File(path3);
                    FileReader fr3 = new FileReader(file3);
                    BufferedReader br3 = new BufferedReader(fr3);
                    for (String lastName; (lastName = br3.readLine()) != null; ) {

                        File file4 = new File(path4);
                        FileReader fr4 = new FileReader(file4);
                        BufferedReader br4 = new BufferedReader(fr4);
                        for (String birthDate; (birthDate = br4.readLine()) != null; ) {

                            emailNumber++;
                            String email = firstName.substring(0,1) + middleName.substring(0,1) + lastName.substring(0,1) + emailNumber + "@exist.com";

                            Employee employee = new Employee();
                            CompanyData companyData = new CompanyData();

                            companyData.setPositionID(positionNum[random.nextInt(positionNum.length)]);
                            companyData.setHireDate(dateFormat.parse(hireDate[random.nextInt(hireDate.length)]));
                            companyData.setSalary(10000 + random.nextInt(20000));
                            companyData.setEmail(email);

                            employee.setFirstName(firstName);
                            employee.setMiddleName(middleName);
                            employee.setLastName(lastName);
                            employee.setGender(gender[random.nextInt(gender.length)]);
                            employee.setBirthDate(dateFormat.parse(birthDate));

                            employee.setCompanyData(companyData);
                            companyData.setEmployee(employee);

                            session.save(employee);

                            if (emailNumber % 50 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                    }
                }
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void populatePositions() {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        String path = "src/main/resources/positions.txt";

        try {
            transaction = session.beginTransaction();

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            for (String row; (row = br.readLine()) != null; ) {

                String[] column = row.split(",");

                Positions position = new Positions();
                position.setPosition(column[0]);
                position.setDeptId(Integer.parseInt(column[1]));

                session.save(position);
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void populateDepartments() {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        String path = "src/main/resources/departments.txt";

        try {
            transaction = session.beginTransaction();

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            for (String dept; (dept = br.readLine()) != null; ) {
                Departments department = new Departments();

                department.setDepartment(dept);

                session.save(department);
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}
