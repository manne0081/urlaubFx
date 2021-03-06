package application.components.componentLogin;

import java.io.IOException;

import application.components.componentEmployee.Employee;
import application.helper.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author manuel
 *
 */
public class LoginViewModel {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Label loginStatus;
	
	
	/**
	 *
	 */
	public void initialize() {
	}
	
	
	/**
	 * 
	 * @param event
	 */
	@FXML
	public void checkLoginData(ActionEvent event) {
		Employee employee = Employee.checkUserData(this.username.getText(), this.password.getText());

		if (employee != null) {
			Session session = new Session(employee);
			openMainView(event);
		} else {
			this.loginStatus.setText("Anmeldung fehlgeschlagen!");
			Session.setEmployee(null);
		}
		
		if (employee != null) {
			System.out.println("Login Name: " + Session.getEmployee().getFullName());
		}

	}
	
	
	/**
	 * 
	 * @param event
	 */
	public void openMainView(ActionEvent event) {
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
	
	
	
}
