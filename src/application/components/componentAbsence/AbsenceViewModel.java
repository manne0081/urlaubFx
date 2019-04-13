package application.components.componentAbsence;

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
import sun.misc.ExtensionInstallationException;

import java.io.IOException;
import java.text.SimpleDateFormat;
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


	/**
	 *
	 * @param event
	 */
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

//		ViewLoadHelper.loadMainView(event);

	}


	/**
	 *
	 * @param event
	 * @throws Exception 
	 */
	public void applyForVacation(ActionEvent event) throws Exception {

		LocalDate localDateFrom = this.absenceFrom.getValue();
		Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());

		/* Number Vacation Days */
//		Long range = ChronoUnit.DAYS.between(localDateFrom, localDateTo) + 1;

		if (this.absenceTo.getValue() == null) {
//			System.out.println("absenceTo ist null");
			Absence absence = new Absence(Session.getEmployee(), dateFrom);
			absenceToArrayList(absence);
			setText(absence, dateFrom);
		} else {
//			System.out.println("absenceTo isnt null");
			LocalDate localDateTo = this.absenceTo.getValue();
			Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

			Absence absence = new Absence(Session.getEmployee(), dateFrom, dateTo);
			absenceToArrayList(absence);
			setText(absence, dateFrom, dateTo);
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
	 * @param absence
	 * @param dateFrom
	 * @param dateTo
	 */
	private void setText(Absence absence, Date dateFrom, Date dateTo) {
		/* Show Vacation-Time and Number Vacation-Days  */
		double numberDays = absence.getNumberDays(dateFrom, dateTo);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.showVacation.setText("vom: " + sdf.format(dateFrom) +
				" bis: " + sdf.format(dateTo) +
				" Dauer: " + numberDays +
				" Tage");
	}

	private void setText(Absence absence, Date dateFrom) {
		/* Show Vacation-Time and Number Vacation-Days  */
		double numberDays = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.showVacation.setText("am: " + sdf.format(dateFrom) +
				" Dauer: " + numberDays +
				" Tage");
	}

}
