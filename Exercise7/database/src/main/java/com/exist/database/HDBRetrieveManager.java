package com.exist.database;

import com.exist.database.utility.HDBRetrieve;
import com.exist.database.utility.HDBUtility;
import com.exist.storage.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HDBRetrieveManager implements HDBRetrieve {
    private static final String queryPropertyFileDir = "src/main/resources/hqlQuery.properties";

    public void test() {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hqlQuery = "FROM Employee";

            Query query = session.createQuery(hqlQuery);
            query.setMaxResults(10);

            List<Object> dataList = query.list();

            for (Object o : dataList) {
                Employee employee = (Employee) o;
                System.out.print("\t" + employee.getId());
                System.out.print("\t" + employee.getFirstName());
                System.out.print("\t" + employee.getMiddleName());
                System.out.print("\t" + employee.getLastName());
                System.out.print("\t" + employee.getCompanyData().getEmail());
                System.out.print("\t" + employee.getCompanyData().getPosition().getPosition());
                System.out.println("\t" + employee.getCompanyData().getPosition().getDepartment().getDepartment());
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean resultIsEmpty(String hqlQuery, Object parameter) {
        boolean retVal = true;

        SessionFactory sessionFactory = HDBUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hqlQuery);
            if (hqlQuery.contains(":id")) {
                query.setParameter("id", (Integer) parameter);
            }
            if (hqlQuery.contains(":position")) {
                query.setParameter("position", (String) parameter);
            }
            if (hqlQuery.contains(":department")) {
                query.setParameter("department", (String) parameter);
            }

            List data = query.list();

            if(!data.isEmpty()) {
                retVal = false;
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return retVal;
    }

    @Override
    public List<Object[]> retrieveObjectArray(String hqlQuery, int beginIndex, int maxResult) {
        List<Object[]> retVal = new ArrayList<Object[]>();

        SessionFactory sessionFactory = HDBUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hqlQuery);
            query.setFirstResult(beginIndex);
            query.setMaxResults(maxResult);

            retVal = query.list();

            retVal.add(query.getReturnAliases());

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return retVal;
    }

    @Override
    public List<Object> retrieveObject(String hqlQuery, int beginIndex, int maxResult) {
        List<Object> retVal = new ArrayList<Object>();

        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hqlQuery);
            query.setFirstResult(beginIndex);
            query.setMaxResults(maxResult);

            retVal = query.list();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
                session.close();
            }
        }

        return retVal;
    }

    public static String getQuery (String propertyKey) {
        String returnValue = new String();

        try {
            Properties queryProperty = new Properties();
            InputStream in = new FileInputStream(queryPropertyFileDir);

            queryProperty.load(in);

            returnValue = queryProperty.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

}
