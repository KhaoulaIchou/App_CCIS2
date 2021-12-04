package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class StatutEE extends Application implements Initializable {
    @FXML
    public TableView<ModelDA> tab;
    public TableColumn<ModelDA, String> c1;
    public TableColumn<ModelDA, String> c2;
    public TableColumn<ModelDA, String> c3;
    public TableColumn<ModelDA, String> c4;
    public TableColumn<ModelDA, String> c5;
    public TableColumn<ModelDA, String> c6;
    public TableColumn<ModelDA, String> c7;
    public TableColumn<ModelDA, String> c8;
    public TableColumn<ModelDA, String> c9;
    public TableColumn<ModelDA, String> c10;
    public TableColumn<ModelDA, String> c11;
    public TableColumn<ModelDA, String> c12;
    public TableColumn<ModelDA, String> c13;
    public TableColumn<ModelDA, String> c14;
    public TableColumn<ModelDA, String> c15;
    public TableColumn<ModelDA, String> c16;
    public TableColumn<ModelDA, String> c17;
    public Label nInd;
    public Label nCom;
    public Label pInd;
    public Label pCom;
    public TextField filterField;
    public ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    public PieChart pieChart;
    public Pane PaneBase;
    public Label labelComm;
    public TextField AreaComm;
    public Button addComm;
    ObservableList<ModelDA> ob = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\EE.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                ob.add(new ModelDA(String.valueOf(i), row.getCell(13).toString(), row.getCell(4).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(10).toString(), row.getCell(11).toString(), row.getCell(12).toString(), row.getCell(14).toString(), row.getCell(15).toString()
                        , row.getCell(16).toString(), row.getCell(18).toString(), row.getCell(19).toString(), row.getCell(20).toString()));

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
        c1.setCellValueFactory(new PropertyValueFactory("s1"));
        c2.setCellValueFactory(new PropertyValueFactory("s2"));
        c3.setCellValueFactory(new PropertyValueFactory("s3"));
        c4.setCellValueFactory(new PropertyValueFactory("s4"));
        c5.setCellValueFactory(new PropertyValueFactory("s5"));
        c6.setCellValueFactory(new PropertyValueFactory("s6"));
        c7.setCellValueFactory(new PropertyValueFactory("s7"));
        c8.setCellValueFactory(new PropertyValueFactory("s8"));
        c9.setCellValueFactory(new PropertyValueFactory("s9"));
        c10.setCellValueFactory(new PropertyValueFactory("s10"));
        c11.setCellValueFactory(new PropertyValueFactory("s11"));
        c12.setCellValueFactory(new PropertyValueFactory("s12"));
        c13.setCellValueFactory(new PropertyValueFactory("s13"));
        c14.setCellValueFactory(new PropertyValueFactory("s14"));
        c15.setCellValueFactory(new PropertyValueFactory("s15"));
        c16.setCellValueFactory(new PropertyValueFactory("s16"));
        c17.setCellValueFactory(new PropertyValueFactory("s17"));
        tab.setItems(ob);


        //initTable();
        FilteredList<ModelDA> filteredData = new FilteredList<>(ob, b -> true);

        filterField.textProperty().addListener((observale, oldValue, newValue) -> {
                    filteredData.setPredicate(modelDA -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(modelDA.getS1()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS11().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS17().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
        );
        SortedList<ModelDA> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);
        /***********************la partie Trier et Graphe*****************************/
        Trier();
    }
    public void Trier() {
        FileInputStream inputStream = null;
        int totalVille = tab.getItems().size();
        Connection con;
        PreparedStatement stm;
        ResultSet rst;
        tab.getSortOrder().add(c3);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "khaoula18", "sirinayy");
            String query = "TRUNCATE TABLE EE";
            stm = con.prepareStatement(query);
            rst = stm.executeQuery(query);
            String query1 = "INSERT INTO EE(ICE,NomPre,Lieu,Objet,Statut,Tele,Email,Adresse,Ville,Deno,RepLegal,Site,Forme,Taille,Secteur,Activite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(query1);
            //stm.executeUpdate(query);*/
            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\EE.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
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
                // stm.setString(21, row.getCell(24).getStringCellValue());
                stm.executeUpdate();
            }

            String s1 = "Select COUNT(*) AS count FROM EE WHERE Statut = 'Entrepreneur' ";
            ResultSet rst1 = stm.executeQuery(s1);
            rst1.next();
            int nb = rst1.getInt("count");


            String s2 = "Select COUNT(*) AS count2 FROM EE WHERE LOWER(Statut)= 'porteur de projet' ";
            ResultSet rst2 = stm.executeQuery(s2);
            rst2.next();
            int nb2 = rst2.getInt("count2");

            float p1 = (float) 100 * nb / totalVille;
            float p2 = (float) 100 * nb2 / totalVille;
            p1 = (float)Math.round(p1*100)/100;
            p2 = (float)Math.round(p2*100)/100;
            data.addAll(
                    new PieChart.Data("Entrepreneur", p1),
                    new PieChart.Data("Porteur de projet", p2));
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
        ModelDA Item= tab.getSelectionModel().getSelectedItem();
        String Name = Item.getS2();
        System.out.println(Name);
        ob.clear();
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\EE.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                ob.add(new ModelDA(String.valueOf(i), row.getCell(13).toString(), row.getCell(4).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(10).toString(), row.getCell(11).toString(), row.getCell(12).toString(), row.getCell(14).toString(), row.getCell(15).toString()
                        , row.getCell(16).toString(), row.getCell(18).toString(), row.getCell(19).toString(), row.getCell(20).toString()));

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
        c1.setCellValueFactory(new PropertyValueFactory("s1"));
        c2.setCellValueFactory(new PropertyValueFactory("s2"));
        c3.setCellValueFactory(new PropertyValueFactory("s3"));
        c4.setCellValueFactory(new PropertyValueFactory("s4"));
        c5.setCellValueFactory(new PropertyValueFactory("s5"));
        c6.setCellValueFactory(new PropertyValueFactory("s6"));
        c7.setCellValueFactory(new PropertyValueFactory("s7"));
        c8.setCellValueFactory(new PropertyValueFactory("s8"));
        c9.setCellValueFactory(new PropertyValueFactory("s9"));
        c10.setCellValueFactory(new PropertyValueFactory("s10"));
        c11.setCellValueFactory(new PropertyValueFactory("s11"));
        c12.setCellValueFactory(new PropertyValueFactory("s12"));
        c13.setCellValueFactory(new PropertyValueFactory("s13"));
        c14.setCellValueFactory(new PropertyValueFactory("s14"));
        c15.setCellValueFactory(new PropertyValueFactory("s15"));
        c16.setCellValueFactory(new PropertyValueFactory("s16"));
        c17.setCellValueFactory(new PropertyValueFactory("s17"));
        tab.setItems(ob);
        try {
            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\EE.xlsx");
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
        // TableView<ModelDA> up=  tab.getSelectionModel().getTableView();
        //int id=up.getSelectionModel().getSelectedIndex();
        int id=0;
        for(int i=0;i<tab.getItems().size();i++){
            if(tab.getItems().get(i).getS2().equals(Name)){
                tab.getItems().removeAll(tab.getItems().get(i));
                id=i;
            }
        }
        int lastRowNum = sheet.getLastRowNum();
        int rowIndex = id+3;
        removeRow(sheet, rowIndex);
        // System.out.println(id);
        //removeRow(sheet,id+2);

        // System.out.println(id);

        //int response= JOptionPane.showConfirmDialog(null,"Voulez vous supprimer ce ligne?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       /* if(response==JOptionPane.NO_OPTION){
            site.setDisable(true);
        }else{
            site.setOnMouseClicked(mouseEvent -> {site.setCursor(Cursor.TEXT);});
        }

       /* try {
            inputStream = new FileInputStream("C:\\Users\\hp\\Desktop\\Demarche.xlsx");
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
        int id=up.getSelectionModel().getSelectedIndex();
        removeRow(sheet,id+2);*/
        FilteredList<ModelDA> filteredData = new FilteredList<>(ob, b -> true);

        filterField.textProperty().addListener((observale, oldValue, newValue) -> {
                    filteredData.setPredicate(modelDA -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(modelDA.getS1()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS11().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS17().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
        );
        SortedList<ModelDA> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedData);
    }

    public void editAction(ActionEvent actionEvent) {
    }

    public void addAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EE.fxml"));
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

            inputStream = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\App_CCIS2\\src\\sample\\DocImg\\EE.xlsx");
            ZipSecureFile.setMinInflateRatio(0);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            String name = wb.getSheetName(0);
            Sheet sheet = wb.getSheet(name);
            Row row = null;
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                ob.add(new ModelDA(String.valueOf(i), row.getCell(13).toString(), row.getCell(4).toString(), row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(5).toString(), row.getCell(6).toString(), row.getCell(7).toString(), row.getCell(10).toString(), row.getCell(11).toString(), row.getCell(12).toString(), row.getCell(14).toString(), row.getCell(15).toString()
                        , row.getCell(16).toString(), row.getCell(18).toString(), row.getCell(19).toString(), row.getCell(20).toString()));

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
        c1.setCellValueFactory(new PropertyValueFactory("s1"));
        c2.setCellValueFactory(new PropertyValueFactory("s2"));
        c3.setCellValueFactory(new PropertyValueFactory("s3"));
        c4.setCellValueFactory(new PropertyValueFactory("s4"));
        c5.setCellValueFactory(new PropertyValueFactory("s5"));
        c6.setCellValueFactory(new PropertyValueFactory("s6"));
        c7.setCellValueFactory(new PropertyValueFactory("s7"));
        c8.setCellValueFactory(new PropertyValueFactory("s8"));
        c9.setCellValueFactory(new PropertyValueFactory("s9"));
        c10.setCellValueFactory(new PropertyValueFactory("s10"));
        c11.setCellValueFactory(new PropertyValueFactory("s11"));
        c12.setCellValueFactory(new PropertyValueFactory("s12"));
        c13.setCellValueFactory(new PropertyValueFactory("s13"));
        c14.setCellValueFactory(new PropertyValueFactory("s14"));
        c15.setCellValueFactory(new PropertyValueFactory("s15"));
        c16.setCellValueFactory(new PropertyValueFactory("s16"));
        c17.setCellValueFactory(new PropertyValueFactory("s17"));
        tab.setItems(ob);
        data.clear();
        Trier();
        FilteredList<ModelDA> filteredData = new FilteredList<>(ob, b -> true);

        filterField.textProperty().addListener((observale, oldValue, newValue) -> {
                    filteredData.setPredicate(modelDA -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (String.valueOf(modelDA.getS1()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS11().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (modelDA.getS17().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
        );
        SortedList<ModelDA> sortedData = new SortedList<>(filteredData);
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
