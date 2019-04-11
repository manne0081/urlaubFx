package application.components.componentAbsence;

import java.util.ArrayList;
import java.util.Date;

import application.components.componentEmployee.Employee;

/**
 * 
 * @author manuel
 *
 */
public class Absence {
	private Employee employee;
	private Date absenceFrom;
	private Date absenceTo;
	private ArrayList<Absence> absences = new ArrayList<Absence>();

	
	/* Constructor */
	public Absence(Employee employee, Date absenceFrom, Date absenceTo) {
		setEmployee(employee);
		setAbsenceFrom(absenceFrom);
		setAbsenceTo(absenceTo);
	}


	/* GETTER & SETTER */
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getAbsenceFrom() {
		return absenceFrom;
	}

	public void setAbsenceFrom(Date absenceFrom) {
		this.absenceFrom = absenceFrom;
	}

	public Date getAbsenceTo() {
		return absenceTo;
	}

	public void setAbsenceTo(Date absenceTo) {
		this.absenceTo = absenceTo;
	}
	
	
	
	
	
	
	
}
