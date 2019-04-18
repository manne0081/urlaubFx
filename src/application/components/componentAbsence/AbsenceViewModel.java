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


	/**
	 *
	 * @param event
	 * @throws Exception
	 */
	public void applyForVacation(ActionEvent event) throws Exception {

		String string;

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

			getVacationRest(absence);
			this.absencePreview.setText("Urlaub beantragt: ");
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

			string = absenceToString();
			string += getVacationRest(absence);
			this.showVacation.setText(string);
			this.absencePreview.setText("Urlaub beantragt: ");

			this.absenceFrom.setDisable(true);
			this.absenceTo.setDisable(true);
			this.absenceFromHalf.setDisable(true);
			this.absenceToHalf.setDisable(true);
			this.soughtVacation.setDisable(true);
		}
	}


	/**
	 *
	 * @param absence
	 */
	private void absenceToArrayList(Absence absence){
		absence.setAbsenceToArrayList(absence);
	}


	/**
	 *
	 */
	public void vacationPreview () {
		String string;

//		prüfen, ob es überschneidungen von eingetragenen urlaubstagen gibt
//		******************************************************************
		LocalDate localDateFrom = this.absenceFrom.getValue();
		Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		string = "" + localDateFrom.getMonthValue() + localDateFrom.getDayOfMonth();

		if (Helper.isSetDatePicker(this.absenceTo)) {
			LocalDate localDateTo = this.absenceTo.getValue();
			Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
			string += " - " + localDateTo.getMonthValue() + localDateTo.getDayOfMonth();
		}

		System.out.println(string);


//		ausgewählten zeitpunkt/zeitraum als vorschau anzeigen
//		*****************************************************
		this.showVacation.setText(absenceToString());
	}


	/**
	 *
	 * @return
	 */
	private String absenceToString() {
		String string = "";
		double numberDays;

		if (this.absenceFrom.getValue() != null && this.absenceTo.getValue() == null) {
			// only one day
			boolean boolHalfFrom = this.absenceFromHalf.isSelected();

			LocalDate localDateFrom = this.absenceFrom.getValue();
			Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());

			string += "Abwesend am: " + Helper.dateToString(dateFrom);
			if (!boolHalfFrom) {string += "\n";}

			numberDays = 1.0;

			if (boolHalfFrom) {
				string += " - halber Tag: \n";
				numberDays -= 0.5;
			}
			string += "Anzahl Urlaubstage: " + numberDays;

		} else if (this.absenceFrom.getValue() != null && this.absenceTo.getValue() != null) {
			// more then one day
			boolean boolHalfFrom = this.absenceFromHalf.isSelected();
			boolean boolHalfTo = this.absenceToHalf.isSelected();

			LocalDate localDateFrom = this.absenceFrom.getValue();
			Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
			LocalDate localDateTo = this.absenceTo.getValue();
			Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

			numberDays = Absence.getNumberDays(dateFrom, boolHalfFrom, dateTo, boolHalfTo);

			string += "Abwesend vom: " + Helper.dateToString(dateFrom);
			if (!boolHalfFrom) {string += "\n";}
			if (boolHalfFrom) {
				string += " - halber Tag: \n";
			}
			string += "Abwesend bis: " + Helper.dateToString(dateTo);
			if (!boolHalfTo) {string += "\n";}
			if (boolHalfTo) {
				string += " - halber Tag: \n";
			}
			string += "Anzahl Urlaubstage: " + numberDays;
		}

		return string;
	}


	/**
	 *
	 * @param absence
	 * @return
	 */
	private String getVacationRest(Absence absence) {
		/* Show Vacation-Time and Number Vacation-Days  */
		String string = "";
		string += "\nResturlaub: " + absence.getEmployee().getVacationRest();

		return string;
	}
	
}
