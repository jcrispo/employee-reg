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
import java.util.Map;
import java.util.Properties;

public class HDBRetrieveManager implements HDBRetrieve {
    private static final String queryPropertyFileDir = "src/main/resources/hqlQuery.properties";

    public boolean resultIsEmpty(String hqlQuery, Map<String, Object> namedParameter) {
        boolean retVal = true;

        SessionFactory sessionFactory = HDBUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hqlQuery);

            for (String key : namedParameter.keySet()) {
                String parameter = key.split(":")[1];

                query.setParameter(parameter, namedParameter.get(key));
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

    public static List<String> getNamedParameters(String hqlQuery) {
        List<String> returnValue = new ArrayList<String>();

        while (hqlQuery.contains(":")) {
            String sub1 = hqlQuery.substring(hqlQuery.indexOf(":"));
            String[] query = sub1.split(" ", 2);

            returnValue.add(query[0]);

            if (query.length == 2) {
                hqlQuery = query[1];
            } else {
                hqlQuery = " ";
            }
        }

        return returnValue;
    }

}
