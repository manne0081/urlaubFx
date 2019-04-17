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
	private static boolean absenceFromHalf;
	private Date absenceTo;
	private static boolean absenceToHalf;
	private static ArrayList<Absence> absences = new ArrayList<Absence>();

	
	/* Constructor */
	/* *********** */
	public Absence(Employee employee, Date absenceFrom, boolean absenceFromHalf) {
		this.employee = employee;
		this.absenceFrom = absenceFrom;
		this.absenceFromHalf = absenceFromHalf;
	}

	public Absence(Employee employee, Date absenceFrom, Date absenceTo, boolean absenceFromHalf, boolean absenceToHalf) {
		this(employee, absenceFrom, absenceFromHalf);
		this.absenceTo = absenceTo;
		this.absenceToHalf = absenceToHalf;
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

	public boolean getAbsenceFromHalf() {
		return absenceFromHalf;
	}
	
	public void setAbscenceFromHalf(boolean absenceFromHalf) {
		this.absenceFromHalf = absenceFromHalf;
	}
	
	public Date getAbsenceTo() {
		return absenceTo;
	}

	public void setAbsenceTo(Date absenceTo) {
		this.absenceTo = absenceTo;
	}

	public boolean getAbsenceToHalf() {
		return absenceToHalf;
	}
	
	public void setAbscenceToHalf(boolean absenceToHalf) {
		this.absenceToHalf = absenceToHalf;
	}


	/* Instance-Methods */
	/* **************** */
	public static void getAllAbsenceFromArrayList() {
		for (int i = 0; i < absences.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			String strDateFrom = sdf.format(absences.get(i).getAbsenceFrom());
			String strDateTo = "";
			if (absences.get(i).getAbsenceTo() != null) {
				strDateTo = sdf.format(absences.get(i).getAbsenceTo());
			} else {
			}
			System.out.println(absences.get(i).getEmployee().getFullName() + 
					" - " + strDateFrom + " - " + strDateTo);			
		}
	}

	public void setAbsenceToArrayList(Absence absence) {
		absences.add(absence);
	}
	
	
	/**
	 * Berechnet die Anzahl der gesamt eingetragenen Urlaubstage (incl. Wochenende)
	 * @param dateFrom Datum des ersten Urlaubstages
	 * @param dateTo Datum des letzten Urlaubstages
	 * @return Gesamt Tage
	 * @throws Exception
	 */
	public static Double getNumberDays(Date dateFrom, boolean dateFromHalf, Date dateTo, boolean dateToHalf){
		Double dblDays;

		long time = dateTo.getTime() - dateFrom.getTime(); // Difference in milli-seconds
		double days = Math.round((double) time / (24. * 60. * 60. * 1000.) + 1); // Difference in days
		dblDays = days;		
		dblDays = getRealAbsenceDays(dateFrom, dblDays);		
		if (dateFromHalf) {
			dblDays -= 0.5;
		}
		if (dateToHalf) {
			dblDays -= 0.5;
		}
		return dblDays;
	}

	public Double getNumberDays(Absence absence){
		double days;
		if (absence.getAbsenceTo() != null) {
			long time = absence.getAbsenceTo().getTime() - absence.getAbsenceFrom().getTime();  // Difference in milli-seconds
			days = Math.round((double) time / (24. * 60. * 60. * 1000.) + 1); // Difference in days
			days = getRealAbsenceDays(absence.getAbsenceFrom(), days);
		} else {
			days = 1.0;
		}		
		if (absenceFromHalf) {
			days -= 0.5;
		}
		if (absenceToHalf) {
			days -= 0.5;
		}
		return days;
	}
	
	
	/**
	 * Berechnet die tatsächlichen Urlaubstage (abzgl. Wochenende)
	 * @param dateFrom Datum des letzten Urlaubstages
	 * @param dbl Anzahl der gesamt eingetragenen Urlaubstage
	 * @return
	 */
	public static double getRealAbsenceDays(Date dateFrom, Double dbl) {
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
