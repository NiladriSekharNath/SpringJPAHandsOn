package com.cognizant;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.ormlearn.service.exception.DepartmentNotFoundException;
import com.cognizant.ormlearn.service.exception.EmployeeNotFoundException;
import com.cognizant.ormlearn.service.exception.SkillNotFoundException;
import com.cognizant.ormlearn.service.exception.StockNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static StockService stockService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(OrmLearnApplication.class, args);
		stockService = applicationContext.getBean(StockService.class);
		employeeService = applicationContext.getBean(EmployeeService.class);
		departmentService = applicationContext.getBean(DepartmentService.class);
		skillService = applicationContext.getBean(SkillService.class);

		// StockService Class
		LOGGER.info("STARTED MAIN for StockService");

		try {
			displayListByCodeAndDate("FB", new SimpleDateFormat("yyyy-mm-dd").parse("2019-09-01"),
					new SimpleDateFormat("yyyy-mm-dd").parse("2019-09-30"));
			getStockByCodeAndClose("GOOGL", new BigDecimal("1250"));
			getTop3StockByVolume();
			getByCodeBottom3LowestStock("NFLX");

		} catch (ParseException e) {

			e.printStackTrace();
		}
		LOGGER.info("MAIN ENDS");
		// StockService Class Ends
		/*-----------------------------------------------------------------*/
		// EmployeeService Class
		testGetEmployee();
		testAddEmployee();
		testUpdateEmployee();
		testGetAllPermanentEmployees();
		testGetAverageSalary(4);
		testGetAllEmloyeesNative();
		// EmployeeService Class Ends
		/*----------------------------------------------------------------*/
		// DepartmentService Class
		testGetDepartment();
		// DepartmentService Class Ends
		/*----------------------------------------------------------------*/
		// SkillService CLass
		testAddSkillToEmployee();
		// SkillService Class Ends
		/*-----------------------------------------------------------------*/
	}

	private static void displayListByCodeAndDate(String name, Date dateStart, Date dateEnd) {
		LOGGER.info("Fetching Results by Code and Date");
		List<Stock> stockListByCodeAndDate = null;
		try {
			stockListByCodeAndDate = stockService.getStockByCodeAndDate(name, dateStart, dateEnd);
		} catch (StockNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("st-code" + "\t" + "st_date" + "\t" + "st-open" + "\t" + "st_close" + "\t" + "st_volume");
		for (Stock stock : stockListByCodeAndDate)
			System.out.println(stock.getCode() + "\t" + stock.getDate() + "\t" + stock.getOpen() + "\t"
					+ stock.getClose() + "\t" + stock.getVolume());

		LOGGER.info("END");
	}

	private static void getStockByCodeAndClose(String code, BigDecimal close) {
		LOGGER.info("Fetching Results by Code and Date");
		List<Stock> stockListByCodeAndClose = null;
		try {
			stockListByCodeAndClose = stockService.getStockByCodeAndClose(code, close);
		} catch (StockNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("st-code" + "\t" + "st_date" + "\t" + "st-open" + "\t" + "st_close" + "\t" + "st_volume");
		for (Stock stock : stockListByCodeAndClose)
			System.out.println(stock.getCode() + "\t" + stock.getDate() + "\t" + stock.getOpen() + "\t"
					+ stock.getClose() + "\t" + stock.getVolume());

		LOGGER.info("END");

	}

	private static void getTop3StockByVolume() {
		LOGGER.info("Fetching Results by Code and Date");
		List<Stock> stockListByVolume = null;
		try {
			stockListByVolume = stockService.getTop3StockByVolume();
		} catch (StockNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("st-code" + "\t" + "st_date" + "\t" + "st-open" + "\t" + "st_close" + "\t" + "st_volume");
		for (Stock stock : stockListByVolume)
			System.out.println(stock.getCode() + "\t" + stock.getDate() + "\t" + stock.getOpen() + "\t"
					+ stock.getClose() + "\t" + stock.getVolume());

		LOGGER.info("END");

	}

	private static void getByCodeBottom3LowestStock(String code) {
		LOGGER.info("Fetching Results by Code and Date");
		List<Stock> stockListByCode = null;
		try {
			stockListByCode = stockService.getByCodeBottom3LowestStock(code);
		} catch (StockNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("st-code" + "\t" + "st_date" + "\t" + "st-open" + "\t" + "st_close" + "\t" + "st_volume");
		for (Stock stock : stockListByCode)
			System.out.println(stock.getCode() + "\t" + stock.getDate() + "\t" + stock.getOpen() + "\t"
					+ stock.getClose() + "\t" + stock.getVolume());

		LOGGER.info("END");

	}

	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = null;
		try {
			employee = employeeService.get(1);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");

	}

	private static void testAddEmployee() {
		LOGGER.info("Start");
		Employee employee = null;
		try {
			employee = new Employee();
			employee.setName("MR X");
			employee.setDateOfBirth(new SimpleDateFormat("yyyy-mm-dd").parse("2020-11-21"));
			employee.setSalary(300000);
			employee.setPermanent(true);
			employee.setDepartment(departmentService.get(1));

		} catch (ParseException | DepartmentNotFoundException e) {
			e.printStackTrace();
		}
		employeeService.save(employee);
		LOGGER.debug("Employee Saved, ID ", employee.getId());
		LOGGER.info("End");

	}

	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee = null;

		try {
			employee = employeeService.get(1);
			Department department = departmentService.get(2);
			employee.setDepartment(department);
			employeeService.save(employee);
		} catch (EmployeeNotFoundException | DepartmentNotFoundException e) {

		}
		LOGGER.debug("Employee={} ", employee);
		LOGGER.info("End");
	}

	private static void testGetDepartment() {
		LOGGER.info("Start");
		Department department = null;
		try {
			department = departmentService.get(1);
		} catch (DepartmentNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("Department:{}", department);
		LOGGER.debug("Employee List:{}", department.getEmployeeList());
		LOGGER.info("End");
	}

	public static void testAddSkillToEmployee() {
		LOGGER.info("Start");
		Employee employee = null;
		Skill skill = null;
		try {
			employee = employeeService.get(2);
			skill = skillService.get(3);
			employee.getSkillList().add(skill);
			employeeService.save(employee);
		} catch (EmployeeNotFoundException | SkillNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}

	public static void testGetAllPermanentEmployees() {
		LOGGER.info("Start");
		List<Employee> employees = null;
		try {
			employees = employeeService.getAllPermanentEmployees();
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}

	public static void testGetAverageSalary(int id) {
		LOGGER.info("Start");
		try {
			LOGGER.debug("Average Salary: ", employeeService.getAverageSalary(id));
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}

	public static void testGetAllEmloyeesNative() {
		LOGGER.info("Start");
		List<Employee> employeeList = null;
		try {
			employeeList=employeeService.getAllEmployeesNative();
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();

		}
		for(Employee employee:employeeList)
			System.out.println(employee);
		LOGGER.info("End");
	}
}
