package com.exist.dao;

import com.exist.dao.utility.EmployeeDAO;
import com.exist.dao.utility.PopulateDatabase;
import com.exist.models.CompanyEmployeeData;
import com.exist.models.Department;
import com.exist.models.Employee;
import com.exist.models.Person;
import com.exist.models.Position;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO{
    private SessionFactory sessionFactory;
    private PopulateDatabase populateDatabase;
    private List<Object[]> employeeData;
    private Employee employee;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Object[]> getEmployeeData(String hqlQuery, int beginIndex, int maxResult) {
        List<Object[]> retVal = new ArrayList<Object[]>();

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
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return retVal;
    }

    @Override
    public void setEmployee(Employee employee) throws HibernateException {
        this.employee = employee;

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Person person = new Person();
            CompanyEmployeeData companyEmployeeData = new CompanyEmployeeData();

            person.setFirstName(employee.getFirstName());
            person.setMiddleName(employee.getMiddleName());
            person.setLastName(employee.getLastName());
            person.setGender(employee.getGender());
            person.setBirthDate(employee.getBirthDate());

            companyEmployeeData.setPositionId(employee.getPositionId());
            companyEmployeeData.setHireDate(employee.getHireDate());
            companyEmployeeData.setSalary(employee.getSalary());
            companyEmployeeData.setEmail(employee.getEmail());

            person.setCompanyEmployeeData(companyEmployeeData);
            companyEmployeeData.setPerson(person);

            session.save(person);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void modifyEmployeeData(String hqlQuery, Map<String, Object> namedParameter) throws HibernateException {
        Session session = sessionFactory.openSession();
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
            throw e;
        } finally {
            session.close();
        }

    }

    public Integer getLastId() {
        Integer retVal = new Integer(0);

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        String hqlQuery = "SELECT P.id FROM Person AS P ORDER BY P.id DESC";

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hqlQuery);
            query.setFirstResult(0);
            query.setMaxResults(1);

            retVal = ((Integer) query.list().get(0));

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

    public void populateDB() throws HibernateException {
        populateDatabase.populateDepartments();
        populateDatabase.populatePositions();
        populateDatabase.populateEmployeeData();
    }

    public Employee getEmployee() {
        System.out.println(employee.toString());
        return employee;
    }

    public void setPopulateDatabase(PopulateDatabase populateDatabase) {
        this.populateDatabase = populateDatabase;
    }

}
