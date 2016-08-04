package com.myrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.myrest.pojo.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	public Employee findByName(String name);

//	@Query(value = "select * from Employee b where b.name=?1")
//	List<Employee> findByNameSQL(String name);

//	List<Employee> findByNameAndEmplNumber(String name, Integer emplNumber);

//	@Query(value = "select * from #{#entityName} b where b.name=?1", nativeQuery = true)
//	List<Employee> findByNameNative(String name);

//	@Query(value = "select name,author,price from Book b where b.price>?1 and b.price<?2")
//<Employee> findByPriceRange(long price1, long price2);

//	@Query(value = "select name from Employee b where b.name like %:name%")
//	List<Employee> findByNameMatch(@Param("name") String name);

}
