package application.components.componentAbsence;

import application.helper.session.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class AbsenceViewModel {
	@FXML
	private Button backButton;
	@FXML
	private DatePicker absenceFromDatePicker, datePickerToDatePicker;
	@FXML
	private CheckBox absenceFromHalfCheckBox, absenceToHalfCheckBox;
	@FXML
	private Button soughtVacationButton;
	@FXML
	private Label soughtVacationLabel;


	public void showDate(ActionEvent event) {
		LocalDate localDate = this.absenceFromDatePicker.getValue();
		System.out.println(localDate.toString());
	}



	/**
	 *
	 * @return
	 */
	public Label getSoughtVacationLabel() {
		return soughtVacationLabel;
	}


	/**
	 *
	 * @param absence
	 */
	private void setSoughtVacationLabel(Absence absence) {
		String string = "";

		LocalDate localDate = this.absenceFromDatePicker.getValue();
		System.out.println(localDate.toString());
		string = localDate.toString();

		Date date = new Date();
		Instant instant = date.toInstant();
		LocalDate localDate2 = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(date + "\n" + instant + "\n" + localDate2);




//		string += "Urlaubnehmer: " + Session.getEmployee().getFullName();
//		string += " - Urlaub von: " + Absence.getAbsenceFrom();

		this.soughtVacationLabel.setText(string);
	}


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
	 */
	public void applyForVacation(ActionEvent event) {
		Absence absence = new Absence(Session.getEmployee());
		absence.setAbsenceToArrayList(absence);
		setSoughtVacationLabel(absence);
		showDate(event);
	}

}
