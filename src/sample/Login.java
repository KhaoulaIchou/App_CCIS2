package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class Login extends Application implements Initializable {
    public Label pwdLabel;
    public Label UserLabel;
    public TextField UserField;
    public PasswordField pwdField;
    public Parent fxml;

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginAction(ActionEvent actionEvent) {
        Connection con;
        Statement stm;
        ResultSet rst;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","khaoula18","sirinayy");
            String email=UserField.getText();
            String pass=pwdField.getText();
            String sql="select EMAIL,PWD from CCIS";
            stm=con.prepareStatement(sql);
            rst=stm.executeQuery(sql);
            if (rst.next()){
                if(email.equals(rst.getString("EMAIL"))&&pass.equals(rst.getString("PWD"))){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                        ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow().hide();
                    } catch (Exception e) {
                        System.out.println("Can't load new window");
                    }
                }
                else{
                    UserField.setText("");
                    pwdField.setText("");
                    Alert alert=new Alert(Alert.AlertType.ERROR,"l'email ou le mot de passe saisi est incorrect",javafx.scene.control.ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
    public void closeStage(){
        EventObject event = null;
        ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
    }
    public void loadStage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }

    public void SignAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }

    }

   /* public void UserAction(MouseEvent mouseEvent) {
        String User=UserField.getPromptText();
        UserLabel.setText(User);
        UserField.setPromptText("");
    }*

    /*public void pwdAction(MouseEvent mouseEvent) {
        String pwd = pwdField.getPromptText();
        pwdLabel.setText(pwd);
        pwdField.setPromptText("");
    }*/
}
