package sample;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller extends Application implements Initializable {
    public Parent fxml;
    public AnchorPane root;
    public Button DAdministrative;
    public VBox minimenu;
    public VBox VboxBase;
    public Button Lieu;
    public Label Menu;
    public Label menuBack;
    public AnchorPane slider;
    public VBox VboxDA;
    public VBox VboxEE;
    public VBox VboxGuichet;
    public Button EEntrepriseAction;
    public Button Guichet;


    public static void main(String[] args) {
        Application.launch(args);
    }
  @FXML
    public void DeAdministrativeAction(ActionEvent actionEvent) {
     VboxBase.getChildren().remove(DAdministrative);
      VboxBase.getChildren().add(DAdministrative);
      VboxBase.getChildren().remove(VboxDA);
      VboxBase.getChildren().add(VboxDA);
      VboxBase.getChildren().remove(EEntrepriseAction);
      VboxBase.getChildren().add(EEntrepriseAction);
      VboxBase.getChildren().remove(VboxEE);
      VboxBase.getChildren().remove(Guichet);
      VboxBase.getChildren().add(Guichet);
      VboxBase.getChildren().remove(VboxGuichet);
      //VboxBase.getChildren().add(VboxEE);
      //VboxBase.getChildren().add(VboxGuichet);
     // VboxDA.setVisible(true);
       // minimenu.setVisible(true);
        //minimenu.getOnScrollStarted();
       /* try {
            fxml= FXMLLoader.load(getClass().getResource("TabDemarche.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }*/

    }

    public void EspaceEntreprise(ActionEvent actionEvent) {
        VboxBase.getChildren().remove(DAdministrative);
        VboxBase.getChildren().add(DAdministrative);
        VboxBase.getChildren().remove(VboxDA);
        VboxBase.getChildren().remove(EEntrepriseAction);
        VboxBase.getChildren().add(EEntrepriseAction);
        VboxBase.getChildren().remove(VboxEE);
        VboxBase.getChildren().add(VboxEE);
        VboxBase.getChildren().remove(Guichet);
        VboxBase.getChildren().add(Guichet);
        VboxBase.getChildren().remove(VboxGuichet);
    }

    public void Guichet(ActionEvent actionEvent) {
        VboxBase.getChildren().remove(DAdministrative);
        VboxBase.getChildren().add(DAdministrative);
        VboxBase.getChildren().remove(VboxDA);
        VboxBase.getChildren().remove(EEntrepriseAction);
        VboxBase.getChildren().add(EEntrepriseAction);
        VboxBase.getChildren().remove(VboxEE);
        VboxBase.getChildren().remove(Guichet);
        VboxBase.getChildren().add(Guichet);
       VboxBase.getChildren().remove(VboxGuichet);
        VboxBase.getChildren().add(VboxGuichet);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VboxBase.getChildren().remove(VboxDA);
        VboxBase.getChildren().remove(VboxEE);
        VboxBase.getChildren().remove(VboxGuichet);


    }

    public void PageLieu(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Lieu.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void docAction(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Document.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void ObjetAction(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Objet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void StatutAction(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Statut.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void FormeAction(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Forme.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void TailleAction(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Taille.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void SecteurAction(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("Secteur.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void PageLieuEE(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("LieuEE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void ObjetActionEE(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("ObjetEE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void StatutActionEE(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("StatutEE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void FormeActionEE(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("FormeEE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void TailleActionEE(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("TailleEE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void SecteurActionEE(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("SecteurEE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void PageLieuGuichet(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("LieuGuichet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void ObjetActionGuichet(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("ObjetGuichet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void StatutActionGuichet(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("StatutGuichet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void FormeActionGuichet(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("FormeGuichet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void TailleActionGuichet(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("TailleGuichet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void SecteurActionGuichet(ActionEvent actionEvent) {
        try {
            fxml= FXMLLoader.load(getClass().getResource("SecteurGuichet.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        }catch (IOException ex) {
            ex.toString();
        }
    }

    public void logOutAction(ActionEvent actionEvent) {
       int response= JOptionPane.showConfirmDialog(null,"Voulez vous vraiment se déconnecter ?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
            try {
                ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                System.out.println("Can't load new window");
            }

        }else{
            return;
        }
    }
}
