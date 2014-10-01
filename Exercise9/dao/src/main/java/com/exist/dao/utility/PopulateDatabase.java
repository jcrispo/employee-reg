package com.exist.dao.utility;

import com.exist.models.CompanyEmployeeData;
import com.exist.models.Department;
import com.exist.models.Person;
import com.exist.models.Position;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class PopulateDatabase {
    SessionFactory sessionFactory;

    public PopulateDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void populateEmployeeData() throws HibernateException{
        Session session = sessionFactory.openSession();
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

                            Person person = new Person();
                            CompanyEmployeeData companyEmployeeData = new CompanyEmployeeData();

                            companyEmployeeData.setPositionId(positionNum[random.nextInt(positionNum.length)]);
                            companyEmployeeData.setHireDate(dateFormat.parse(hireDate[random.nextInt(hireDate.length)]));
                            companyEmployeeData.setSalary(10000 + random.nextInt(20000));
                            companyEmployeeData.setEmail(email);

                            person.setFirstName(firstName);
                            person.setMiddleName(middleName);
                            person.setLastName(lastName);
                            person.setGender(gender[random.nextInt(gender.length)]);
                            person.setBirthDate(dateFormat.parse(birthDate));

                            person.setCompanyEmployeeData(companyEmployeeData);
                            companyEmployeeData.setPerson(person);

                            session.save(person);

                            if (emailNumber % 50 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                    }
                }
            }

            transaction.commit();
            System.out.println("Data Input Completed!");
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            throw e;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void populatePositions() throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        String path = "src/main/resources/positions.txt";

        try {
            transaction = session.beginTransaction();

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            for (String row; (row = br.readLine()) != null; ) {

                String[] column = row.split(",");

                Position position = new Position();
                position.setPosition(column[0]);
                position.setDepartmentId(Integer.parseInt(column[1]));

                session.save(position);
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void populateDepartments() throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        String path = "src/main/resources/departments.txt";

        try {
            transaction = session.beginTransaction();

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            for (String dept; (dept = br.readLine()) != null; ) {
                Department department = new Department();

                department.setDepartment(dept);

                session.save(department);
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
