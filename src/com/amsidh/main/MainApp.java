/**
 * 
 */
package com.amsidh.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amsidh.mvc.dao.EmployeeDao;
import com.amsidh.mvc.dto.Employee;

/**
 * @author VIRU
 *
 */
public class MainApp {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     AbstractApplicationContext context=new ClassPathXmlApplicationContext("resource/spring.xml");
     EmployeeDao employeedao=(EmployeeDao)context.getBean("employeeDao");
     Employee employee=new Employee(455,"Lokhande A B");
    try
    {
    	employeedao.createEmployee(employee);
    }catch(Exception ex)
    {
    	ex.printStackTrace();
    }
    for(Employee emp:employeedao.getAllEmployee())
    {
    	System.out.println(emp.getEmployeeId()+":"+emp.getEmployeeName());
    }
	}

}
