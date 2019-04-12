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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class AbsenceViewModel {
	@FXML
	private Button backButton;
	@FXML
	private DatePicker absenceFromDatePicker, absenceToDatePicker;
	@FXML
	private CheckBox absenceFromHalfCheckBox, absenceToHalfCheckBox;
	@FXML
	private Button soughtVacationButton;
	@FXML
	private Label soughtVacationLabel;


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
		/* LocalDate from DatePicker */
		LocalDate localDateFrom = this.absenceFromDatePicker.getValue();
		LocalDate localDateTo = this.absenceToDatePicker.getValue();
		
		/* DatePicker to Date */
		Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
				
		/* Number Vacation Days */
//		Long range = ChronoUnit.DAYS.between(localDateFrom, localDateTo) + 1;
        
		/* ADD Absence and put it into ArrayList */
		Absence absence = new Absence(Session.getEmployee(), dateFrom, dateTo);
		absence.setAbsenceToArrayList(absence);
		
		/* Get the Number of Vacation-Days  */
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.soughtVacationLabel.setText("vom: " + sdf.format(dateFrom) + 
				" bis: " + sdf.format(dateTo) + 
				" Dauer: " + absence.getNumberDays(dateFrom, dateTo) + 
				" Tage");
	}

}
