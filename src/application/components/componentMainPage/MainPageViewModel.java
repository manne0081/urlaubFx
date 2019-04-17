package application.components.componentMainPage;

import application.helper.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 */
public class MainPageViewModel {
	@FXML
	private Label userName;


	/**
	 *
	 */
	public void initialize() {
		setUserName(Session.getEmployee().getFullName());
	}

	
	/* GETTER & SETTER */
	public Label getUserName() {
		return userName;
	}

	public void setUserName(String string) {
		this.userName.setText(string);
	}
	

	/* Methods */
	public void openAbsence(ActionEvent event) {
		try {
			// Create the scene
			Parent root = FXMLLoader.load(this.getClass().getResource("../componentAbsence/AbsenceView.fxml"));
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


	public void logout(ActionEvent event) {
		try {
			// Create the scene
			Parent root = FXMLLoader.load(this.getClass().getResource("../componentLogin/LoginView.fxml"));
			Scene scene = new Scene(root);

			// Gets the stage based on the ActionEvent
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			// Push the Scene in the Window and show
			stage.setScene(scene);
			stage.show();

			Session.setEmployee(null);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
