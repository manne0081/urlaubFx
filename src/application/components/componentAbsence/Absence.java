package application.components.componentAbsence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	private static ArrayList<Absence> absences = new ArrayList<Absence>();

	
	/* Constructor */
	/* *********** */
	public Absence(Employee employee) {
		setEmployee(employee);
	}

	public Absence(Employee employee, Date absenceFrom, Date absenceTo) {
		setEmployee(employee);
		setAbsenceFrom(absenceFrom);
		setAbsenceTo(absenceTo);
	}


	/* GETTER & SETTER */
	/* *************** */
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

	
	/* private & public Methods */
	/* ************************ */
	public static void getAllAbsenceFromArrayList() {
		for (int i = 0; i < absences.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			String strDateFrom = sdf.format(absences.get(i).getAbsenceFrom());
			String strDateTo = sdf.format(absences.get(i).getAbsenceTo());
			System.out.println(absences.get(i).getEmployee().getFullName() + 
					" - " + strDateFrom + " - " + strDateTo);			
		}
	}

	public void setAbsenceToArrayList(Absence absence) {
		absences.add(absence);
	}
	
	
	/**
	 * Berechnet die Anzahl der Gesamt-Urlaubstage (incl. Wochenende)
	 * @param dateFrom Datum des ersten Urlaubstages
	 * @param dateTo Datum des letzten Urlaubstages
	 * @return
	 * @throws Exception
	 */
	public Double getNumberDays(Date dateFrom, Date dateTo) throws Exception {
		Double dblDays;

		long time = dateTo.getTime() - dateFrom.getTime(); // Difference in milli-seconds
		double days = Math.round((double) time / (24. * 60. * 60. * 1000.) + 1); // Difference in days
		dblDays = days;
		
		dblDays = getRealAbsenceDays(dateFrom, dblDays);
		
		return dblDays;
	}

	
	/**
	 * Berechnet die tatsächlichen Urlaubstage (abzgl. Wochenende)
	 * @param dateFrom Datum des letzten Urlaubstages
	 * @param dbl Anzahl der gesamt eingetragenen Urlaubstage
	 * @return
	 */
	public double getRealAbsenceDays(Date dateFrom, Double dbl) {
		int n = dbl.intValue();
		int weekDay;
		int absenceDays = 0;
		
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.setTime(dateFrom);	
		weekDay = calendarFrom.get(Calendar.DAY_OF_WEEK);
		
		for (int a = 1; a <= n; a++) {
			if (weekDay == 7) {  // Check Saturday
			} else if (weekDay == 1) {  // Check Sunday
			} else {
				absenceDays += 1;  // Urlaubstage addieren
			}
			calendarFrom.add(Calendar.DATE, 1);
			weekDay = calendarFrom.get(Calendar.DAY_OF_WEEK);
		}
		return absenceDays;
	}

	
}
