package application.components.componentAbsence;

import application.helper.session.Helper;
import application.helper.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class AbsenceViewModel {
	@FXML
	private Button goBack;
	@FXML
	private DatePicker absenceFrom, absenceTo;
	@FXML
	private CheckBox absenceFromHalf, absenceToHalf;
	@FXML
	private Button soughtVacation;
	@FXML
	private Label showVacation;
	@FXML
	private Label vacationRest;
	@FXML
	private Label absencePreview;

	
	public void initialize() {
		this.vacationRest.setText("Aktueller Urlaubsstand: " + Session.getEmployee().getVacationRest());
	}
	
	
	/* Methods */
	public void backToMainView(ActionEvent event) {
		try {
			// Create the scene
			Parent root = FXMLLoader.load(this.getClass().getResource("../componentMainPage/MainPageView.fxml"));
			Scene scene = new Scene(root);

			// Gets the stage based on the ActionEvent
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			// Push the Scene in the Window and show
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void applyForVacation(ActionEvent event) throws Exception {

		/* Get Date as LocalDate from DatePicker and convert to Date */
		LocalDate localDateFrom = this.absenceFrom.getValue();
		Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		/* Number Vacation Days */
//		Long range = ChronoUnit.DAYS.between(localDateFrom, localDateTo) + 1;

		if (this.absenceTo.getValue() == null) {
			/* When take only one Day */
			Absence absence = new Absence(Session.getEmployee(), dateFrom, absenceFromHalf.isSelected()); // create new Absence
			absenceToArrayList(absence); // add Absence to ArrayList
			absence.getEmployee().substractVacationTime(absence.getNumberDays(absence));
			
//			setText(absence);
		} else {
			/* When take more then one Day */
			/* Get Date as LocalDate from DatePicker and convert to Date */
			LocalDate localDateTo = this.absenceTo.getValue();
			Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			// create new Absence
			Absence absence = new Absence(Session.getEmployee(), dateFrom, dateTo, this.absenceFromHalf.isSelected(), this.absenceToHalf.isSelected()); 
			// add Absence to ArrayList
			absenceToArrayList(absence);
			// calculate vacation-time and new rest-vacation-days
			absence.getEmployee().substractVacationTime(absence.getNumberDays(absence));
			
			setText(absence);
			this.absencePreview.setText("Urlaub beantragt: ");
		}
	}

	
	private void absenceToArrayList(Absence absence){
		absence.setAbsenceToArrayList(absence);
	}

	
	public void vacationPreview () {
		String string = "";
		
		LocalDate localDateFrom = this.absenceFrom.getValue();
		Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		boolean boolHalfFrom = this.absenceFromHalf.isSelected();
		
		if (this.absenceFrom.getValue() != null) {
			string = "Abwesend von: " + Helper.dateToString(dateFrom);
		}
		if (this.absenceFromHalf.isSelected()) {
			string += " - halber Tag: " + boolHalfFrom + "\n";
		} else {
			string += "\n";
		}
				
		if (this.absenceTo.getValue() != null) {			
			LocalDate localDateTo = this.absenceTo.getValue();
			Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
			string += "Abwesend bis: " + Helper.dateToString(dateTo);
			
			boolean boolHalfTo = this.absenceToHalf.isSelected();
			if (this.absenceToHalf.isSelected()) {
				string += " - halber Tag: " + boolHalfTo + "\n";
			} else {
				string += "\n";
			}
			
			boolean dateFromHalf = this.absenceFromHalf.isSelected();
			boolean dateToHalf = this.absenceToHalf.isSelected();
			double days = Absence.getNumberDays(dateFrom, dateFromHalf, dateTo, dateToHalf);
			string += "Anzahl Urlaubstage: " + days;
		}
		this.showVacation.setText(string);		
	}
	
	
	private void setText(Absence absence) {
		/* Show Vacation-Time and Number Vacation-Days  */
		Date dateFrom = absence.getAbsenceFrom();
		Date dateTo = absence.getAbsenceTo();
		double numberDays = absence.getNumberDays(absence);
		this.showVacation.setText(
				"vom: " + Helper.dateToString(dateFrom) + "\n" + 
				"bis: " + Helper.dateToString(dateTo) + "\n" + 
				"Dauer: " + numberDays + " Tage" + "\n" + 
				"Resturlaub: " + absence.getEmployee().getVacationRest());
	}
	
}
