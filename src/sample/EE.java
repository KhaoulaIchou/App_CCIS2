package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EE extends Application implements Initializable {
    public DatePicker Date3;
    public ComboBox ComboLieu1;
    public TextField NomPrenom1;
    public TextField Tel1;
    public TextField Email1;
    public TextField Adresse1;
    public TextField Ville1;
    public TextField Deno1;
    public TextField ICE1;
    public TextField site1;
    public TextField A1;
    public CheckBox accepter1;
    public CheckBox certifier1;
    public RadioButton PP1;
    public ToggleGroup forme;
    public RadioButton SARL1;
    public RadioButton SA1;
    public RadioButton AutoE1;
    public RadioButton Petite1;
    public ToggleGroup taille;
    public RadioButton Moyenne1;
    public RadioButton Grande1;
    public CheckBox Industrie1;
    public CheckBox Commerce1;
    public CheckBox Services1;
    public TextField Activité1;
    public TextField RepCCIS1;
    public TextField qualité1;
    public TextArea Obrev1;
    public TextField NomRep1;
    public RadioButton Entre1;
    public ToggleGroup statut;
    public RadioButton Porteur1;
    public CheckBox Program;
    public CheckBox Annuaire;
    public CheckBox Demarche;
    public CheckBox Repertoire;


    public void NomDeno(KeyEvent keyEvent) {
        String Nom = NomPrenom1.getText();
        Deno1.setText(Nom);
        NomRep1.setText(Nom);
    }


    public void validerEE(ActionEvent actionEvent) {
        if(validerEmail()&&validerCode()&&validerNumero()&&validerText()&&validercheckbox()&&validerSite()) {
            Connection con;
            Statement stm;
            ResultSet rst;
            ZipSecureFile.setMinInflateRatio(0);
            String excelFilePath = "C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\EE.xlsx";
            String dateC = Date3.getValue().toString();
            String Lieu = ComboLieu1.getValue().toString();
            String Objet = "";
            // String Objet = Objt.getElements().nextElement().getText();
            if (Program.isSelected()) {
                Objet += Program.getText();
            }
            if (Demarche.isSelected()) {
                Objet += Demarche.getText();
            }
            if (Annuaire.isSelected()) {
                Objet += Annuaire.getText();
            }
            if (Repertoire.isSelected()) {
                Objet += Repertoire.getText();
            }

            String NomPre = NomPrenom1.getText();
            String statut = "";
            if (Entre1.isSelected()) {
                statut += Entre1.getText();
            }
            if (Porteur1.isSelected()) {
                statut += Porteur1.getText();
            }
            String Tele = Tel1.getText();
            String mail = Email1.getText();
            String adr = Adresse1.getText();
            String ville = Ville1.getText();
            String deno = Deno1.getText();
            String ice = ICE1.getText();
            String Site = site1.getText();
            String Rep = NomRep1.getText();
            String FormeJur = "";
            if (PP1.isSelected()) {
                FormeJur += PP1.getText();
            }
            if (SARL1.isSelected()) {
                FormeJur += SARL1.getText();
            }
            if (SA1.isSelected()) {
                FormeJur += SA1.getText();
            }
            if (AutoE1.isSelected()) {
                FormeJur += AutoE1.getText();
            }// else {
            // FormeJur += A.getText();
            //}
            String Taille = "";
            if (Petite1.isSelected()) {
                Taille += Petite1.getText();
            }
            if (Moyenne1.isSelected()) {
                Taille += Moyenne1.getText();
            }
            if (Grande1.isSelected()) {
                Taille += Grande1.getText();
            }
            String Secteur = "";
            if (Industrie1.isSelected()) {
                Secteur += Industrie1.getText();
            }
            if (Commerce1.isSelected()) {
                Secteur += Commerce1.getText();
            }
            if (Services1.isSelected()) {
                Secteur += Services1.getText();
            }
            String Activite = Activité1.getText();
            String E1, E2;
            if (accepter1.isSelected()) {
                E1 = "OUI";
                E2 = "OUI";
            } else {
                E1 = "NON";
                E2 = "NON";
            }

            String RepCC = RepCCIS1.getText();
            String qlt = qualité1.getText();
            String Obser = Obrev1.getText();
            FileInputStream inputStream = null;
            FileOutputStream fileOut = null;
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "khaoula18", "sirinayy");
                String query = "INSERT INTO EE (Prestation,Lieu,DateC,Objet,NomPre,Statut,Tele,Email,Adresse,Ville,Deno,ICE,RepLegal,Site,Forme,Taille,Secteur,Activite,AccepR,AccepE) VALUES('EE','" + Lieu + "',TO_DATE('" + dateC + "','YYYY-MM-DD'),'" + Objet + "','" + NomPre + "','" + statut + "','" + Tele + "','" + mail + "','" + adr + "','" + ville + "','" + deno + "','" + ice + "','" + Rep + "','" + Site + "','" + FormeJur + "','" + Taille + "','" + Secteur + "','" + Activite + "','"+E1+"','"+E2+"')";
                stm = con.prepareStatement(query);
                stm.executeQuery(query);
                String query1 = "SELECT * FROM EE WHERE ICE ='" + ice + "'";
                stm = con.prepareStatement(query1);
                rst = stm.executeQuery(query1);
                inputStream = new FileInputStream(excelFilePath);
                ZipSecureFile.setMinInflateRatio(0);

                XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                // XSSFWorkbook wb= new XSSFWorkbook();
                //XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
                String name = wb.getSheetName(0);
                Sheet sheet = wb.getSheet(name);
           /* System.out.println(sheet.getSheetName());
            System.out.println(sheet.getLastRowNum());
            System.out.println(sheet.getPhysicalNumberOfRows());*/

                // XSSFRow header = sheet.getRow(0);
                int index = sheet.getLastRowNum();
                while (rst.next()) {
                    //JOptionPane.showMessageDialog(null,"Connection bien établie");
                    XSSFRow row = (XSSFRow) sheet.createRow(index);
                    row.createCell(0).setCellValue(rst.getString("Prestation"));
                    row.createCell(1).setCellValue(rst.getString("Lieu"));
                    row.createCell(2).setCellValue(rst.getString("DateC"));
                    row.createCell(3).setCellValue(rst.getString("Objet"));
                    row.createCell(4).setCellValue(rst.getString("NomPre"));
                    row.createCell(5).setCellValue(rst.getString("Statut"));
                    row.createCell(6).setCellValue(rst.getString("Tele"));
                    row.createCell(7).setCellValue(rst.getString("Email"));
                    row.createCell(8).setCellValue(rst.getString("AccepE"));
                    row.createCell(9).setCellValue(rst.getString("AccepR"));
                    row.createCell(10).setCellValue(rst.getString("Adresse"));
                    row.createCell(11).setCellValue(rst.getString("Ville"));
                    row.createCell(12).setCellValue(rst.getString("Deno"));
                    row.createCell(13).setCellValue(rst.getString("ICE"));
                    row.createCell(14).setCellValue(rst.getString("RepLegal"));
                    row.createCell(15).setCellValue(rst.getString("Site"));
                    row.createCell(16).setCellValue(rst.getString("Forme"));
                    row.createCell(18).setCellValue(rst.getString("Taille"));
                    row.createCell(19).setCellValue(rst.getString("Secteur"));
                    row.createCell(20).setCellValue(rst.getString("Activite"));
                    index++;
                }
                fileOut = new FileOutputStream(excelFilePath);
                wb.write(fileOut);

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("les informations sont bien envoyées");
                alert.showAndWait();

                stm.close();
                rst.close();
            } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    IOUtils.close(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    IOUtils.close(fileOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /*******validate email***************/
    private boolean validerEmail(){
        Pattern p= Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m=p.matcher(Email1.getText());
        if(m.find() && m.group().equals(Email1.getText())){
            return true;
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Entrez un mail valide");
            alert.showAndWait();
            //JFXDialogLayout dialoglayout=new JFXDialogLayout();
            return false;
        }
    }
    /***validate ICE***/
    private boolean validerCode(){
        Pattern p=Pattern.compile("[0-9]+");
        Matcher m=p.matcher(ICE1.getText());
        if(m.find()&& m.group().equals(ICE1.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Entrez un code ICE valide");
            alert.showAndWait();
            return false;
        }
    }
    /**********validate Tele*********/
    private boolean validerNumero(){
        Pattern p=Pattern.compile("[0-9]+");
        Matcher m=p.matcher(Tel1.getText());
        if(m.find()&& m.group().equals(Tel1.getText())){
            return true;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Entrez un numéro téléphone valide");
            alert.showAndWait();
            return false;
        }
    }
    /**validate site web**/
    private boolean validerSite(){
        if(site1.getText().contains("www")){
            return true; }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Entrez un site web valide");
            alert.showAndWait();
            return false;
        }
    }
    /***validate textfield****/
    private boolean validerText(){
        if( NomPrenom1.getText().isEmpty()| Tel1.getText().isEmpty()| Email1.getText().isEmpty()| Adresse1.getText().isEmpty()|
                Ville1.getText().isEmpty()| Deno1.getText().isEmpty()| ICE1.getText().isEmpty()| site1.getText().isEmpty()| NomRep1.getText().isEmpty()| Activité1.getText().isEmpty()|
                RepCCIS1.getText().isEmpty()| qualité1.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champ vide");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    /****validate checkbox*****/
    private boolean validercheckbox(){
        if(!Program.isSelected()| Annuaire.isSelected()| Repertoire.isSelected()| Demarche.isSelected() /* | Autre1.isSelected()*/){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Séléctionnez un document demandé");
            alert.showAndWait();
            return false;
        }
        if(!Industrie1.isSelected()| Commerce1.isSelected()| Services1.isSelected()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Séléctionnez un secteur d'activité");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboLieu1.getItems().add("MARRAKECH");
        ComboLieu1.getItems().add("ESSAOUIRA");
        ComboLieu1.getItems().add("EL KELAA DES SRAGHNA");
        ComboLieu1.getItems().add("SAFI");
        accepter1.setSelected(true);
        certifier1.setSelected(true);
        Date3.setValue(LocalDate.now());
        RepCCIS1.setText("Rachid BNINHA");
        qualité1.setText("Chef DA");
    }
}