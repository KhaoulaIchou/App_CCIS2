package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Objet extends Application implements Initializable {
    public TableView<Model> tab;
    public TableColumn<Model, String> c1;
    public TableColumn<Model, String> c2;
    public TableColumn<Model, String> c3;
    public TableColumn<Model, String> c4;
    public TableColumn<Model, String> c5;
    public TableColumn<Model, String> c6;
    public TableColumn<Model, String> c7;
    public TableColumn<Model, String> c8;
    public TableColumn<Model, String> c9;
    public TableColumn<Model, String> c10;
    public TableColumn<Model, String> c11;
    public TableColumn<Model, String> c12;
    public TableColumn<Model, String> c13;
    public TableColumn<Model, String> c14;
    public TableColumn<Model, String> c15;
    public TableColumn<Model, String> c16;
    public TableColumn<Model, String> c17;
    public TableColumn<Model, String> c18;
    public TableColumn<Model, String> c19;
    public TableColumn<Model, String> c20;
    public TableColumn<Model, String> c21;
    public TextField filterField;
    public ObservableList<PieChart.Data> data= FXCollections.observableArrayList();
    public PieChart pieChart;
    public Label nRen;
    public Label nInfo;
    public Label pRen;
    public Label pInfo;
    public Pane PaneBase;
    public Label labelComm;
    public TextField AreaComm;
    public Button addComm;
    ObservableList<Model> ob = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileInputStream inputStream=null;
        try {

            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Demarche.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                ob.add(new Model(String.valueOf(i), row.getCell(13).toString(), row.getCell(4).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(10).toString(), row.getCell(11).toString(), row.getCell(12).toString(), row.getCell(14).toString(), row.getCell(15).toString()
                        , row.getCell(16).toString(), row.getCell(18).toString(), row.getCell(19).toString(), row.getCell(20).toString(), row.getCell(21).toString(), row.getCell(22).toString(), row.getCell(23).toString(), row.getCell(24).toString()));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                IOUtils.close(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        c1.setCellValueFactory(new PropertyValueFactory<Model, String>("c1"));
        c2.setCellValueFactory(new PropertyValueFactory<Model, String>("c2"));
        c3.setCellValueFactory(new PropertyValueFactory<Model, String>("c3"));
        c4.setCellValueFactory(new PropertyValueFactory<Model, String>("c4"));
        c5.setCellValueFactory(new PropertyValueFactory<Model, String>("c5"));
        c6.setCellValueFactory(new PropertyValueFactory<Model, String>("c6"));
        c7.setCellValueFactory(new PropertyValueFactory<Model, String>("c7"));
        c8.setCellValueFactory(new PropertyValueFactory<Model, String>("c8"));
        c9.setCellValueFactory(new PropertyValueFactory<Model, String>("c9"));
        c10.setCellValueFactory(new PropertyValueFactory<Model, String>("c10"));
        c11.setCellValueFactory(new PropertyValueFactory<Model, String>("c11"));
        c12.setCellValueFactory(new PropertyValueFactory<Model, String>("c12"));
        c13.setCellValueFactory(new PropertyValueFactory<Model, String>("c13"));
        c14.setCellValueFactory(new PropertyValueFactory<Model, String>("c14"));
        c15.setCellValueFactory(new PropertyValueFactory<Model, String>("c15"));
        c16.setCellValueFactory(new PropertyValueFactory<Model, String>("c16"));
        c17.setCellValueFactory(new PropertyValueFactory<Model, String>("c17"));
        c18.setCellValueFactory(new PropertyValueFactory<Model, String>("c18"));
        c19.setCellValueFactory(new PropertyValueFactory<Model, String>("c19"));
        c20.setCellValueFactory(new PropertyValueFactory<Model, String>("c20"));
        c21.setCellValueFactory(new PropertyValueFactory<Model, String>("c21"));
        tab.setItems(ob);


        //initTable();
        FilteredList<Model> filteredData = new FilteredList<>(ob, b -> true);

        filterField.textProperty().addListener((observale, oldValue, newValue) -> {
                    filteredData.setPredicate(model -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(model.getC1()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC11().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC17().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
        );
        SortedList<Model> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);
        /***********************la partie Trier et Graphe*****************************/
        int totalVille=tab.getItems().size();
        Connection con ;
        PreparedStatement stm;
        ResultSet rst;
        tab.getSortOrder().add(c5);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","khaoula18","sirinayy");
            String query="TRUNCATE TABLE Dema";
            stm=con.prepareStatement(query);
            rst=stm.executeQuery(query);
            String query1="INSERT INTO Dema(ICE,NomPre,Lieu,Objet,Statut,Tele,Mail,Adresse,Ville,Deno,RepLegal,Site,Forme,Taille,Secteur,Activite,Document,EtatDossier,Suite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stm=con.prepareStatement(query1);
            //stm.executeUpdate(query);*/
            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Demarche.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                stm.setString(1, row.getCell(13).getStringCellValue());
                stm.setString(2, row.getCell(4).getStringCellValue());
                stm.setString(3, row.getCell(1).getStringCellValue());
                // stm.setString(4, row.getCell(2).getStringCellValue());
                stm.setString(4, row.getCell(3).getStringCellValue());
                stm.setString(5, row.getCell(5).getStringCellValue());
                stm.setString(6, row.getCell(6).getStringCellValue());
                stm.setString(7, row.getCell(7).getStringCellValue());
                stm.setString(8, row.getCell(10).getStringCellValue());
                stm.setString(9, row.getCell(11).getStringCellValue());
                stm.setString(10, row.getCell(12).getStringCellValue());
                stm.setString(11, row.getCell(14).getStringCellValue());
                stm.setString(12, row.getCell(15).getStringCellValue());
                stm.setString(13, row.getCell(16).getStringCellValue());
                stm.setString(14, row.getCell(18).getStringCellValue());
                stm.setString(15, row.getCell(19).getStringCellValue());
                stm.setString(16, row.getCell(20).getStringCellValue());
                stm.setString(17, row.getCell(21).getStringCellValue());
                stm.setString(18, row.getCell(22).getStringCellValue());
                stm.setString(19, row.getCell(23).getStringCellValue());
                // stm.setString(21, row.getCell(24).getStringCellValue());
                stm.executeUpdate();
            }


            String s1 = "Select COUNT(*) AS count FROM Dema WHERE Objet = 'Demande d’information /renseignement à propos d’un document administratif'";
            ResultSet rst1 = stm.executeQuery(s1);
            rst1.next();
            int nb = rst1.getInt("count");


            String s2 = "Select COUNT(*) AS count2 FROM Dema WHERE Objet = 'Demande de document administratif' ";
            ResultSet rst2 = stm.executeQuery(s2);
            rst2.next();
            int nb2 = rst2.getInt("count2");

            float p1 = (float) 100 * nb / totalVille;
            float p2 = (float) 100 * nb2 / totalVille;
            p1 = (float)Math.round(p1*100)/100;
            p2 = (float)Math.round(p2*100)/100;
            data.addAll(
                    new PieChart.Data("Demande d’information /renseignement à propos d’un document administratif", p1),
                    new PieChart.Data("Demande de document administratif", p2));
            pieChart.setData(data);
            for(final PieChart.Data data: pieChart.getData()){
                data.nameProperty().set(data.getName()+"\n"+(float)data.getPieValue()+"%");
            }
            stm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeRow(Sheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
        if (rowIndex == lastRowNum) {
            Row removingRow = sheet.getRow(rowIndex);
            if (removingRow != null) {
                sheet.removeRow(removingRow);
            }
        }
    }
    public void deleteAction(ActionEvent actionEvent) {
        Model Item= tab.getSelectionModel().getSelectedItem();
        String Name = Item.getC2();
        System.out.println(Name);
        ob.clear();

       /* for(int i=0;i<tab.getItems().size();i++){
            tab.getItems().clear();
        }*/

        FileInputStream inputStream=null;
        try {

            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Demarche.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                ob.add(new Model(String.valueOf(i), row.getCell(13).toString(), row.getCell(4).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(10).toString(), row.getCell(11).toString(), row.getCell(12).toString(), row.getCell(14).toString(), row.getCell(15).toString()
                        , row.getCell(16).toString(), row.getCell(18).toString(), row.getCell(19).toString(), row.getCell(20).toString(), row.getCell(21).toString(), row.getCell(22).toString(), row.getCell(23).toString(), row.getCell(24).toString()));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                IOUtils.close(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        c1.setCellValueFactory(new PropertyValueFactory<Model, String>("c1"));
        c2.setCellValueFactory(new PropertyValueFactory<Model, String>("c2"));
        c3.setCellValueFactory(new PropertyValueFactory<Model, String>("c3"));
        c4.setCellValueFactory(new PropertyValueFactory<Model, String>("c4"));
        c5.setCellValueFactory(new PropertyValueFactory<Model, String>("c5"));
        c6.setCellValueFactory(new PropertyValueFactory<Model, String>("c6"));
        c7.setCellValueFactory(new PropertyValueFactory<Model, String>("c7"));
        c8.setCellValueFactory(new PropertyValueFactory<Model, String>("c8"));
        c9.setCellValueFactory(new PropertyValueFactory<Model, String>("c9"));
        c10.setCellValueFactory(new PropertyValueFactory<Model, String>("c10"));
        c11.setCellValueFactory(new PropertyValueFactory<Model, String>("c11"));
        c12.setCellValueFactory(new PropertyValueFactory<Model, String>("c12"));
        c13.setCellValueFactory(new PropertyValueFactory<Model, String>("c13"));
        c14.setCellValueFactory(new PropertyValueFactory<Model, String>("c14"));
        c15.setCellValueFactory(new PropertyValueFactory<Model, String>("c15"));
        c16.setCellValueFactory(new PropertyValueFactory<Model, String>("c16"));
        c17.setCellValueFactory(new PropertyValueFactory<Model, String>("c17"));
        c18.setCellValueFactory(new PropertyValueFactory<Model, String>("c18"));
        c19.setCellValueFactory(new PropertyValueFactory<Model, String>("c19"));
        c20.setCellValueFactory(new PropertyValueFactory<Model, String>("c20"));
        c21.setCellValueFactory(new PropertyValueFactory<Model, String>("c21"));
        tab.setItems(ob);

        try {
            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Demarche.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipSecureFile.setMinInflateRatio(0);
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = wb.getSheetName(0);
        Sheet sheet = wb.getSheet(name);
        TableView<Model> up=  tab.getSelectionModel().getTableView();
        //int id=up.getSelectionModel().getSelectedIndex();
        int id=0;
        for(int i=0;i<tab.getItems().size();i++){
            if(tab.getItems().get(i).getC2().equals(Name)){
                tab.getItems().removeAll(tab.getItems().get(i));
                id=i;
            }
        }
        int lastRowNum = sheet.getLastRowNum();
        int rowIndex=id+2;
        removeRow(sheet, rowIndex);
        FilteredList<Model> filteredData = new FilteredList<>(ob, b -> true);

        filterField.textProperty().addListener((observale, oldValue, newValue) -> {
                    filteredData.setPredicate(model -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(model.getC1()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC11().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC17().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
        );
        SortedList<Model> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);

    }

    public void editAction(ActionEvent actionEvent) {
    }

    public void addAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Demarche.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }

    public void refreshAction(ActionEvent actionEvent) {
        ob.clear();
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Demarche.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                ob.add(new Model(String.valueOf(i), row.getCell(13).toString(), row.getCell(4).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(10).toString(), row.getCell(11).toString(), row.getCell(12).toString(), row.getCell(14).toString(), row.getCell(15).toString()
                        , row.getCell(16).toString(), row.getCell(18).toString(), row.getCell(19).toString(), row.getCell(20).toString(), row.getCell(21).toString(), row.getCell(22).toString(), row.getCell(23).toString(), row.getCell(24).toString()));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                IOUtils.close(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        c1.setCellValueFactory(new PropertyValueFactory<Model, String>("c1"));
        c2.setCellValueFactory(new PropertyValueFactory<Model, String>("c2"));
        c3.setCellValueFactory(new PropertyValueFactory<Model, String>("c3"));
        c4.setCellValueFactory(new PropertyValueFactory<Model, String>("c4"));
        c5.setCellValueFactory(new PropertyValueFactory<Model, String>("c5"));
        c6.setCellValueFactory(new PropertyValueFactory<Model, String>("c6"));
        c7.setCellValueFactory(new PropertyValueFactory<Model, String>("c7"));
        c8.setCellValueFactory(new PropertyValueFactory<Model, String>("c8"));
        c9.setCellValueFactory(new PropertyValueFactory<Model, String>("c9"));
        c10.setCellValueFactory(new PropertyValueFactory<Model, String>("c10"));
        c11.setCellValueFactory(new PropertyValueFactory<Model, String>("c11"));
        c12.setCellValueFactory(new PropertyValueFactory<Model, String>("c12"));
        c13.setCellValueFactory(new PropertyValueFactory<Model, String>("c13"));
        c14.setCellValueFactory(new PropertyValueFactory<Model, String>("c14"));
        c15.setCellValueFactory(new PropertyValueFactory<Model, String>("c15"));
        c16.setCellValueFactory(new PropertyValueFactory<Model, String>("c16"));
        c17.setCellValueFactory(new PropertyValueFactory<Model, String>("c17"));
        c18.setCellValueFactory(new PropertyValueFactory<Model, String>("c18"));
        c19.setCellValueFactory(new PropertyValueFactory<Model, String>("c19"));
        c20.setCellValueFactory(new PropertyValueFactory<Model, String>("c20"));
        c21.setCellValueFactory(new PropertyValueFactory<Model, String>("c21"));
        tab.setItems(ob);
        data.clear();
        int totalVille=tab.getItems().size();
        Connection con ;
        PreparedStatement stm;
        ResultSet rst;
        tab.getSortOrder().add(c5);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","khaoula18","sirinayy");
            String query="TRUNCATE TABLE Dema";
            stm=con.prepareStatement(query);
            rst=stm.executeQuery(query);
            String query1="INSERT INTO Dema(ICE,NomPre,Lieu,Objet,Statut,Tele,Mail,Adresse,Ville,Deno,RepLegal,Site,Forme,Taille,Secteur,Activite,Document,EtatDossier,Suite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stm=con.prepareStatement(query1);
            //stm.executeUpdate(query);*/
            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\Demarche.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                stm.setString(1, row.getCell(13).getStringCellValue());
                stm.setString(2, row.getCell(4).getStringCellValue());
                stm.setString(3, row.getCell(1).getStringCellValue());
                // stm.setString(4, row.getCell(2).getStringCellValue());
                stm.setString(4, row.getCell(3).getStringCellValue());
                stm.setString(5, row.getCell(5).getStringCellValue());
                stm.setString(6, row.getCell(6).getStringCellValue());
                stm.setString(7, row.getCell(7).getStringCellValue());
                stm.setString(8, row.getCell(10).getStringCellValue());
                stm.setString(9, row.getCell(11).getStringCellValue());
                stm.setString(10, row.getCell(12).getStringCellValue());
                stm.setString(11, row.getCell(14).getStringCellValue());
                stm.setString(12, row.getCell(15).getStringCellValue());
                stm.setString(13, row.getCell(16).getStringCellValue());
                stm.setString(14, row.getCell(18).getStringCellValue());
                stm.setString(15, row.getCell(19).getStringCellValue());
                stm.setString(16, row.getCell(20).getStringCellValue());
                stm.setString(17, row.getCell(21).getStringCellValue());
                stm.setString(18, row.getCell(22).getStringCellValue());
                stm.setString(19, row.getCell(23).getStringCellValue());
                // stm.setString(21, row.getCell(24).getStringCellValue());
                stm.executeUpdate();
            }


            String s1 = "Select COUNT(*) AS count FROM Dema WHERE Objet = 'Demande d’information /renseignement à propos d’un document administratif'";
            ResultSet rst1 = stm.executeQuery(s1);
            rst1.next();
            int nb = rst1.getInt("count");


            String s2 = "Select COUNT(*) AS count2 FROM Dema WHERE Objet = 'Demande de document administratif' ";
            ResultSet rst2 = stm.executeQuery(s2);
            rst2.next();
            int nb2 = rst2.getInt("count2");

            float p1 = (float) 100 * nb / totalVille;
            float p2 = (float) 100 * nb2 / totalVille;
            p1 = (float)Math.round(p1*100)/100;
            p2 = (float)Math.round(p2*100)/100;
            data.addAll(
                    new PieChart.Data("Demande d’information /renseignement à propos d’un document administratif", p1),
                    new PieChart.Data("Demande de document administratif", p2));
            pieChart.setData(data);
            for(final PieChart.Data data: pieChart.getData()){
                data.nameProperty().set(data.getName()+"\n"+(float)data.getPieValue()+"%");
            }
            stm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FilteredList<Model> filteredData = new FilteredList<>(ob, b -> true);

        filterField.textProperty().addListener((observale, oldValue, newValue) -> {
                    filteredData.setPredicate(model -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(model.getC1()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC11().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (model.getC17().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
        );
        SortedList<Model> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);
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

    public void addCommAction(ActionEvent actionEvent) {
        String Comm=AreaComm.getText();
        PaneBase.getChildren().remove(AreaComm);
        PaneBase.getChildren().remove(addComm);
        labelComm.setText(Comm);
    }
}

