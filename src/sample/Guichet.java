package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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

public class Guichet extends Application implements Initializable {
    public DatePicker Date4;
    public ComboBox ComboLieu2;
    public RadioButton Demande2;
    public ToggleGroup Objt;
    public TextField NomPrenom2;
    public TextField Tel2;
    public TextField Email2;
    public TextField Adresse2;
    public TextField Ville2;
    public TextField Deno2;
    public TextField ICE2;
    public TextField site2;
    public TextField A2;
    public CheckBox accepter2;
    public CheckBox certifier2;
    public RadioButton PP2;
    public RadioButton SARL2;
    public RadioButton SA2;
    public RadioButton AutoE2;
    public RadioButton Petite2;
    public RadioButton Moyenne2;
    public RadioButton Grande2;
    public CheckBox Industrie2;
    public CheckBox Commerce2;
    public CheckBox Services2;
    public TextField Activité2;
    public TextField RepCCIS2;
    public TextField qualité2;
    public TextArea Obrev2;
    public TextField NomRep2;
    public RadioButton Entre2;
    public ToggleGroup statut;
    public RadioButton Porteur2;
    public RadioButton Demande1;

    public void NomDeno(KeyEvent keyEvent) {
        String Nom = NomPrenom2.getText();
        Deno2.setText(Nom);
        NomRep2.setText(Nom);
    }


    public void validerGuichet(ActionEvent actionEvent) {
        if(validerEmail()&&validerCode()&&validerNumero()&&validerText()&&validercheckbox()&&validerSite()) {
            write();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboLieu2.getItems().add("MARRAKECH");
        ComboLieu2.getItems().add("ESSAOUIRA");
        ComboLieu2.getItems().add("EL KELAA DES SRAGHNA");
        ComboLieu2.getItems().add("SAFI");
        accepter2.setSelected(true);
        certifier2.setSelected(true);
        Date4.setValue(LocalDate.now());
        RepCCIS2.setText("Rachid BNINHA");
        qualité2.setText("Chef DA");
    }
    public void write() {
        ZipSecureFile.setMinInflateRatio(0);
        String excelFilePath = "C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Guichet.xlsx";
        String dateC = Date4.getValue().toString();
        String Lieu = ComboLieu2.getValue().toString();
        String Objet = "";
        if (Demande1.isSelected()) {
            Objet += Demande1.getText();
        }
        if (Demande2.isSelected()) {
            Objet += Demande2.getText();
        }


        String NomPre = NomPrenom2.getText();
        String statut = "";
        if (Entre2.isSelected()) {
            statut += Entre2.getText();
        }
        if (Porteur2.isSelected()) {
            statut += Porteur2.getText();
        }
        String Tele = Tel2.getText();
        String mail = Email2.getText();
        String adr = Adresse2.getText();
        String ville = Ville2.getText();
        String deno = Deno2.getText();
        String ice = ICE2.getText();
        String Site = site2.getText();
        String Rep = NomRep2.getText();
        String FormeJur = "";
        if (PP2.isSelected()) {
            FormeJur += PP2.getText();
        }
        if (SARL2.isSelected()) {
            FormeJur += SARL2.getText();
        }
        if (SA2.isSelected()) {
            FormeJur += SA2.getText();
        }
        if (AutoE2.isSelected()) {
            FormeJur += AutoE2.getText();
        }// else {
        // FormeJur += A.getText();
        //}
        String Taille = "";
        if (Petite2.isSelected()) {
            Taille += Petite2.getText();
        }
        if (Moyenne2.isSelected()) {
            Taille += Moyenne2.getText();
        }
        if (Grande2.isSelected()) {
            Taille += Grande2.getText();
        }
        String Secteur = "";
        if (Industrie2.isSelected()) {
            Secteur += Industrie2.getText();
        }
        if (Commerce2.isSelected()) {
            Secteur += Commerce2.getText();
        }
        if (Services2.isSelected()) {
            Secteur += Services2.getText();
        }
        String Activite = Activité2.getText();
        String E1, E2;
        if (accepter2.isSelected()) {
            E1 = "OUI";
            E2 = "OUI";
        } else {
            E1 = "NON";
            E2 = "NON";
        }

        String RepCC = RepCCIS2.getText();
        String qlt = qualité2.getText();
        String Obser = Obrev2.getText();
        Connection con;
        Statement stm;
        ResultSet rst;
        FileInputStream inputStream = null;
        FileOutputStream fileOut = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "khaoula18", "sirinayy");
            String query = "INSERT INTO Guichet (Prestation,Lieu,DateC,Objet,NomPre,Statut,Tele,Email,Adresse,Ville,Deno,ICE,RepLegal,Site,Forme,Taille,Secteur,Activite,AccepR,AccepE) VALUES('Guichet','" + Lieu + "',TO_DATE('" + dateC + "','YYYY-MM-DD'),'" + Objet + "','" + NomPre + "','" + statut + "','" + Tele + "','" + mail + "','" + adr + "','" + ville + "','" + deno + "','" + ice + "','" + Rep + "','" + Site + "','" + FormeJur + "','" + Taille + "','" + Secteur + "','" + Activite + "','"+E1+"','"+E2+"')";
            stm = con.prepareStatement(query);
            stm.executeQuery(query);
            String query1 = "SELECT * FROM Guichet WHERE ICE ='" + ice + "'";
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
    /*******validate email***************/
    private boolean validerEmail(){
        Pattern p= Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m=p.matcher(Email2.getText());
        if(m.find() && m.group().equals(Email2.getText())){
            return true;
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Entrez un mail valide");
            alert.showAndWait();
            return false;
        }
    }
    /***validate ICE***/
    private boolean validerCode(){
        Pattern p=Pattern.compile("[0-9]+");
        Matcher m=p.matcher(ICE2.getText());
        if(m.find()&& m.group().equals(ICE2.getText())){
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
        Matcher m=p.matcher(Tel2.getText());
        if(m.find()&& m.group().equals(Tel2.getText())){
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
        if(site2.getText().contains("www")){
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
        if( NomPrenom2.getText().isEmpty()| Tel2.getText().isEmpty()| Email2.getText().isEmpty()| Adresse2.getText().isEmpty()|
                Ville2.getText().isEmpty()| Deno2.getText().isEmpty()| ICE2.getText().isEmpty()| site2.getText().isEmpty()| NomRep2.getText().isEmpty()| Activité2.getText().isEmpty()|
                RepCCIS2.getText().isEmpty()| qualité2.getText().isEmpty()){
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
        if(!Demande1.isSelected()| Demande2.isSelected()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Séléctionnez un document demandé");
            alert.showAndWait();
            return false;
        }
        if(!Industrie2.isSelected()| Commerce2.isSelected()| Services2.isSelected()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Séléctionnez un secteur d'activité");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
