package com.exist.database;

import com.exist.database.utility.HDBUtility;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Map;

public class HDBUpdateDeleteManager {

    public void updateDB(String hqlQuery, Map<String, Object> namedParameter) {
        Session session = HDBUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hqlQuery);

            for (String key : namedParameter.keySet()) {
                String parameter = key.split(":")[1];

                query.setParameter(parameter, namedParameter.get(key));
            }

            query.executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
