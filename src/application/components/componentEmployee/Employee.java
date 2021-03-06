package application.components.componentEmployee;

import application.components.componentDepartments.Department;

/**
 * 
 * @author manuel
 *
 */
public class Employee {
	private String nameI;
	private String nameII;
	private String username;
	private String password;
	private double weeklyWorkTime;
	private double vacationClaim;
	private double vacationRest;
	
	private Department department;


	/* simulate Data */
	private static Employee[] employeeList = {
		new Employee("Daniel", "Düsentrieb", "daniel", "pass", 30),
		new Employee("Kater", "Karlo", "kater", "pass",25),
		new Employee("Goofy", "goofy", "pass", 30)
	};


	/* Constructor */
	/* *********** */
	public Employee(String nameI, String user, String pass) {
		this.setNameI(nameI);
		this.setUserName(user);
		this.setPassword(pass);
		this.setVacationRest(vacationClaim);
	}
	
	public Employee (String nameI, String user, String pass, int vacationClaim) {
		this(nameI, user, pass);
		setVacationClaim(vacationClaim);
	}
	
	public Employee(String nameI, String nameII, String user, String pass) {
		this(nameI, user, pass);
		setVacationRest(vacationClaim);
		setNameII(nameII);
	}

	public Employee(String nameI, String nameII, String user, String pass, int vacationClaim) {
		this(nameI, nameII, user, pass);
		setVacationRest(vacationClaim);
		setVacationClaim(vacationClaim);
	}

	
	/* GETTER & SETTER */
	/* *************** */
	public String getNameI() {
		return nameI;
	}

	public void setNameI(String nameI) {
		this.nameI = nameI;
	}

	public String getNameII() {
		return nameII;
	}

	public void setNameII(String nameII) {
		this.nameII = nameII;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getWeeklyWorkTime() {
		return weeklyWorkTime;
	}

	public void setWeeklyWorkTime(double weeklyWorkTime) {
		this.weeklyWorkTime = weeklyWorkTime;
	}

	public double getVacationClaim() {
		return vacationClaim;
	}

	public void setVacationClaim(double vacationClaim) {
		this.vacationClaim = vacationClaim;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public double getVacationRest() {
		return vacationRest;
	}

	public void setVacationRest(double vacationRest) {
		this.vacationRest = vacationRest;
	}
	
	
	/* Instance-Methods */
	public static Employee checkUserData(String userName, String password) {
		Employee employee = null;
		for (Employee e : employeeList) {
			if (e.getUserName().equals(userName)) {
				if (e.getPassword().equals(password)) {
					employee = e;
				}
			}			
		}
		return employee;
	}	
	
	public String getFullName() {
		String strAll = "";
		if (this.getNameI() != "") {
			strAll = this.getNameI();
		}
		if (this.getNameII() != null) {
			strAll += " " + this.getNameII();
		}
		return strAll;
	}
	
	public double substractVacationTime(double vacationDays) {
		setVacationRest(this.vacationRest - vacationDays);
		return this.vacationRest;
	}

	
	
}
