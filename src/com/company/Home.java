package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Khubaib CH on 7/30/2017.
 */
public class Home {
    Stage window1;
    Label titleLabel, linceNoLabel, addressLabel, dateLAbel, invoiceNoLabel;
    TextField dateField, invoiceField, total;
    ComboBox<String> drugName1;
    ObservableList<String> drugName;
    Menu file, edit;
    Button add, delete, viewStock, viewExpiry, viewBill, Report, goToBill, calculateTotal;
    TableView<HomeTable> billTable;
    ObservableList<HomeTable> tableData;
    Scene scene1;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String filter = "";
    QueryCreator queryCreator;

    public Home() {
        window1 = new Stage();
        queryCreator = new QueryCreator();
        window1.setTitle("Home Page");
        connection = DataBaseConnection.ConnectDB();
        home();

    }

    private void home() {
        file = new Menu("File");
        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> {
            Login object1 = new Login();
            object1.login();
            window1.close();
        });

        file.getItems().addAll(logout);

        edit = new Menu("Edit");

        MenuItem changePassword = new MenuItem("Change Password");
        changePassword.setOnAction(e -> {
            ChangePassword obj = new ChangePassword();
            obj.changePassword();
            window1.close();

        });

        MenuItem newUser = new MenuItem("Create New User");
        newUser.setOnAction(e -> {
            CreateNewUser object = new CreateNewUser();
            object.createNewUser();
            window1.close();

        });

        edit.getItems().addAll(changePassword, newUser);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, edit);

        add = new Button("Add Items");
        add.setMinWidth(120);
        add.setOnAction(e -> {
            AddDrug object3 = new AddDrug();
            object3.addDrug();
            window1.close();

        });

        delete = new Button("Delete Items");
        delete.setMinWidth(120);
        delete.setOnAction(e -> {
            DeleteItems object4 = new DeleteItems();
            object4.deleteItems();
            window1.close();

        });

        viewStock = new Button("View Stock");
        viewStock.setMinWidth(120);
        viewStock.setOnAction(e -> {
            ViewStock object5 = new ViewStock();
            object5.viewStock();
            window1.close();

        });

        viewExpiry = new Button("View Expiry Date");
        viewExpiry.setMinWidth(120);
        viewExpiry.setOnAction(e -> {
            ViewExpiry object8 = new ViewExpiry();
            object8.viewExpiry();
            window1.close();
        });

        viewBill = new Button("View Bill");
        viewBill.setMinWidth(120);
        viewBill.setOnAction(e -> {
            ViewBills object9 = new ViewBills();
            object9.viewBills();
            window1.close();

        });

        Report = new Button("Report");
        Report.setMinWidth(120);
        Report.setOnAction(e -> {
            ViewReport object7 = new ViewReport();
            object7.viewReport();
            window1.close();
        });

        HBox hBox = new HBox(30);
        hBox.setPadding(new Insets(10, 10, 10, 80));
        hBox.getChildren().addAll(add, delete, viewStock, viewExpiry, viewBill, Report);

        titleLabel = new Label("Doctor's Pharmacy");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(titleLabel);
        stackPane.setAlignment(Pos.BASELINE_CENTER);

        linceNoLabel = new Label("D.L No 72/DCA/21/DHS-2011");
        StackPane stackPane1 = new StackPane();
        stackPane1.getChildren().addAll(linceNoLabel);
        stackPane1.setAlignment(Pos.BASELINE_CENTER);

        addressLabel = new Label("Near Doctor's Hospital Modal town C chock Bahawalpur");
        StackPane stackPane2 = new StackPane();
        stackPane2.getChildren().addAll(addressLabel);
        stackPane2.setAlignment(Pos.BASELINE_CENTER);


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(menuBar, hBox, stackPane, stackPane2, stackPane1);
        vBox.setPadding(new Insets(0, 0, 10, 0));

        BorderPane layout = new BorderPane();
        layout.setTop(vBox);


        dateLAbel = new Label("Date");
        dateField = new TextField();
        dateField.setEditable(false);
        dateField.setMaxWidth(100);

        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        dateField.setText("" + day + "-" + (month + 1) + "-" + year);

        drugName1 = new ComboBox<>();
        drugName1.setMinWidth(230);
        drugName1.setPromptText("Drug Name");
        drugName1.setEditable(false);


        drugName = FXCollections.observableArrayList();
        try {
            String sql = "select Particulars from AddDrugs";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drugName.add(
                        resultSet.getString("Particulars")

                );

                drugName1.getItems().addAll(drugName);
            }

        } catch (Exception e1) {

            JOptionPane.showMessageDialog(null, e1);
        }
        new ComboBoxAutoComplete<String>(drugName1);


        drugName1.setOnHiding(keyEvent -> {
            try {
                String medicine = drugName1.getValue();
                String sql = "select * from AddDrugs where Particulars='" + medicine + "'";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    tableData.add(new HomeTable(
                            resultSet.getString("Particulars"),
                            resultSet.getString("BatchNo"),
                            resultSet.getString("ExpDate"),
                            resultSet.getString("MRP"),
                            resultSet.getString("Rate")

                    ));
                    billTable.setItems(tableData);
                }

            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, e1);
            } finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (Exception e2) {

                }
            }

        });

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(drugName1);

        HBox hBox4 = new HBox(10);
        hBox4.getChildren().addAll(dateLAbel, dateField);
        hBox4.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox2 = new HBox(800);
        hBox2.getChildren().addAll(hBox1, hBox4);
        hBox2.setPadding(new Insets(0, 10, 0, 10));

        invoiceNoLabel = new Label("Invoice No");
        invoiceField = new TextField();
        invoiceField.setEditable(false);
        invoiceField.setMaxWidth(100);

        try {
            String sql15 = "select InvoiceNo from SaleReport order by InvoiceNo desc limit 1";
            preparedStatement = connection.prepareStatement(sql15);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                invoiceField.setText(String.valueOf((1 + Integer.parseInt((resultSet.getString("InvoiceNo"))))));
            }

        } catch (Exception e2) {

        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e3) {

            }
        }


        HBox hBox5 = new HBox(10);
        hBox5.getChildren().addAll(invoiceNoLabel, invoiceField);
        hBox5.setAlignment(Pos.BASELINE_RIGHT);
        hBox5.setPadding(new Insets(0, 10, 0, 10));


        TableColumn sn = new TableColumn<>("S.N");
        sn.setMinWidth(120);
        sn.setCellValueFactory(new PropertyValueFactory<>("nu"));
        sn.setCellFactory(TextFieldTableCell.forTableColumn());
        sn.setEditable(true);
        sn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<HomeTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<HomeTable, String> t) {
                        ((HomeTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQty(Integer.valueOf(t.getNewValue()));
                    }
                }
        );


        TableColumn Particulars = new TableColumn("Particulars");
        Particulars.setMinWidth(200);
        Particulars.setCellValueFactory(new PropertyValueFactory<>("Particulars"));

        TableColumn qtyAvailable = new TableColumn("Qty");
        qtyAvailable.setMinWidth(120);
        qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));
        qtyAvailable.setCellFactory(TextFieldTableCell.forTableColumn());
        qtyAvailable.setEditable(true);
        qtyAvailable.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<HomeTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<HomeTable, String> t) {
                        ((HomeTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQty(Integer.valueOf(t.getNewValue()));
                        int rate = Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getMrp());
                        int qty = t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getQty();
                        int totalAmount = rate * qty;
                        ((HomeTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAmount(totalAmount);


                    }
                }

        );


        TableColumn BatchNo = new TableColumn("Batch");
        BatchNo.setMinWidth(140);
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));

        TableColumn expDate = new TableColumn("EXP");
        expDate.setMinWidth(190);
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn Mrp = new TableColumn("MRP");
        Mrp.setMinWidth(120);
        Mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));

        TableColumn rate = new TableColumn("Rate");
        rate.setMinWidth(120);
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));


        TableColumn amount = new TableColumn("Amount");
        amount.setMinWidth(120);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        billTable = new TableView<>();
        tableData = FXCollections.observableArrayList();
        billTable.getColumns().addAll(sn, Particulars, qtyAvailable, BatchNo, expDate, Mrp, rate, amount);
        billTable.setEditable(true);


        VBox vBox1 = new VBox(3);
        vBox1.getChildren().addAll(hBox2, hBox5, billTable);
        vBox1.setPadding(new Insets(0, 65, 5, 50));
        layout.setCenter(vBox1);


        goToBill = new Button("Goto Billing");
        goToBill.setOnAction(event -> {
            ObservableList<HomeTable> table = billTable.getItems();
            for (HomeTable row : table) {
                queryCreator.Create(row);
                queryCreator.execute();

            }
        });


        calculateTotal = new Button("Calculate Total");
        calculateTotal.setOnAction((ActionEvent e) -> {
//
//             int Tot=0;
//            for (HomeTable homeTable:billTable.getItems()){
//
//                total.setText(String.valueOf(homeTable.getQty()));
//          }
            //    total.setText(String.valueOf(Tot));

            // total.setText(String.valueOf(qtyAvailable.getText()));


        });

        total = new TextField();
        total.setEditable(false);
        total.setMaxWidth(90);

        HBox hBox6 = new HBox(10);
        hBox6.getChildren().addAll(calculateTotal, total);
        hBox6.setAlignment(Pos.CENTER_LEFT);

        HBox hBox7 = new HBox(450);
        hBox7.getChildren().addAll(goToBill, hBox6);
        hBox7.setAlignment(Pos.CENTER_LEFT);

        hBox7.setPadding(new Insets(5, 20, 40, 510));
        layout.setBottom(hBox7);
        scene1 = new Scene(layout, 1300, 680);
        window1.setScene(scene1);
        window1.show();


    }

}
