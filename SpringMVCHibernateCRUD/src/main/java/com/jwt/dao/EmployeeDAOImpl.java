package com.jwt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	 

	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {

		return sessionFactory.getCurrentSession().createQuery("from Employee")
				.list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		
		System.out.println("employee" +employee);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}

	}

	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(
				Employee.class, empid);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		return employee;
	}

	@Override
	public Employee validateUser(Employee employee) {
		Session session = sessionFactory.openSession();	
		
		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("name",employee.getName()));
		crit.add(Restrictions.eq("password",employee.getPassword()));
		
		List<Employee> results = crit.list();

			Employee employee1=new Employee();
		for (Employee employee2 : results) {
			employee1=employee2;
			
		}
		return employee1;
	}

	@Override
	public Employee verify(Employee employee) {
		Session session = sessionFactory.openSession();	
		
		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("telephone",employee.getTelephone()));
		
		List<Employee> results = crit.list();

			Employee employee1=new Employee();
		for (Employee employee2 : results) {
			employee1=employee2;
			
		}
		return employee1;
	}

}