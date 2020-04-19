/**
 * 
 */
package com.amsidh.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.amsidh.mvc.dto.Employee;

/**
 * @author VIRU
 * 
 */
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.amsidh.mvc.dao.EmployeeDao#createEmployee(com.amsidh.mvc.dto.Employee
	 * )
	 */
	@Override
	public void createEmployee(Employee employee) {
		// TODO Auto-generated method stub

		try {
			String sql = new String(
					"INSERT INTO EMPLOYEE ( EMPLOYEEID , EMPLOYEENAME ) VALUES ( ? , ? )");
			getJdbcTemplate().update(
					sql,
					new Object[] { employee.getEmployeeId(),
							employee.getEmployeeName() });
			//throw new RuntimeException("simulate Error condition");
			throw new ArrayIndexOutOfBoundsException();
		} catch (DataAccessException ex) {
			System.out.println("Error in creating record, rolling back");
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amsidh.mvc.dao.EmployeeDao#removeEmployee(java.lang.Integer)
	 */
	@Override
	public void removeEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		String sql = new String("DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?");
		getJdbcTemplate().update(sql, new Object[] { employeeId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.amsidh.mvc.dao.EmployeeDao#updateEmployee(com.amsidh.mvc.dto.Employee
	 * )
	 */
	@Override
	public void updateEmployee(Employee employee) {
		String sql = new String(
				"UPDATE EMPLOYEE SET EMPLOYEENAME=? WHERE EMPLOYEEID=?");
		getJdbcTemplate().update(
				sql,
				new Object[] { employee.getEmployeeName(),
						employee.getEmployeeId() });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amsidh.mvc.dao.EmployeeDao#retriveEmployee(java.lang.Integer)
	 */
	@Override
	public Employee retriveEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		String sql = new String("SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?");
		Employee employee = getJdbcTemplate().queryForObject(sql,
				new Object[] { employeeId }, new EmployeeRowMapper());
		return employee != null ? employee : new Employee();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amsidh.mvc.dao.EmployeeDao#getAllEmployee()
	 */
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		String sql = new String("SELECT * FROM EMPLOYEE");
		List<Employee> employeeList = getJdbcTemplate().query(sql,
				new EmployeeRowMapper());
		return employeeList.size() > 0 ? employeeList : null;
	}

	/**
	 * @author VIRU
	 * 
	 */
	class EmployeeRowMapper implements RowMapper<Employee> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
		 * int)
		 */
		@Override
		public Employee mapRow(ResultSet rst, int rownum) throws SQLException {
			Employee employee = new Employee();
			employee.setEmployeeId(rst.getInt("EMPLOYEEID"));
			employee.setEmployeeName(rst.getString("EMPLOYEENAME"));
			return employee;
		}

	}
}
