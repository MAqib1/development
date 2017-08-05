package com.company;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/24/2017.
 */
public class Bill{

    Stage window13;
    Label titleLabel,TAmountLabel,linceNoLabel,pharmacyNameLabel,addressLabel,dateLAbel,invoiceNoLabel;
    TextField tAmountField,dateField,invoiceField;
    Button back,print;
    TableView<ViewBillsTable> billTable;
    ObservableList<ViewBillsTable> tableData;
    Scene scene13;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    BorderPane layout;


    public void bills(String no){
        window13=new Stage();
        window13.setTitle("Billing");

        connection=DataBaseConnection.ConnectDB();

        back=new Button("Back");
        back.setMinWidth(90);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window13.close();

        });

        TAmountLabel=new Label("Total Amount");
        TAmountLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        tAmountField=new TextField();
        tAmountField.setMaxWidth(100);
        tAmountField.setEditable(false);

        HBox hBox=new HBox(10);
        hBox.getChildren().addAll(TAmountLabel,tAmountField);

        print=new Button("Print");
        print.setMinWidth(90);
        print.setOnAction((ActionEvent e) ->{
            try {

            }catch (Exception e1){

            }
        });

        HBox hBox1=new HBox(20);
        hBox1.getChildren().addAll(hBox,print);

        HBox hBox2=new HBox(550);
        hBox2.getChildren().addAll(back,hBox1);

        titleLabel=new Label("Billing");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");


        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(titleLabel);
        stackPane.setAlignment(Pos.BASELINE_CENTER);

        VBox vBox=new VBox(10);
        vBox.getChildren().addAll(hBox2,stackPane);
        vBox.setPadding(new Insets(20,50,10,50));

        BorderPane layout=new BorderPane();
        layout.setTop(vBox);

        linceNoLabel=new Label("D.L No 72/DCA/21/DHS-2011");

        pharmacyNameLabel=new Label("Doctor's Pharmacy");
        pharmacyNameLabel.setStyle("-fx-font-size:150%;-fx-font-weight:bold");


        HBox hBox3=new HBox(175);
        hBox3.getChildren().addAll(linceNoLabel,pharmacyNameLabel);
        hBox3.setPadding(new Insets(10,10,0,100));

        addressLabel=new Label("Near Doctor's Hospital Modal town C chock Bahawalpur");

        StackPane stackPane1=new StackPane();
        stackPane1.getChildren().addAll(addressLabel);
        stackPane1.setAlignment(Pos.BASELINE_CENTER);

        dateLAbel=new Label("Date");

        dateField=new TextField();
        dateField.setMaxWidth(100);

        HBox hBox4=new HBox(10);
        hBox4.getChildren().addAll(dateLAbel,dateField);
        hBox4.setAlignment(Pos.BASELINE_RIGHT);
        hBox4.setPadding(new Insets(0,10,0,10));

        invoiceNoLabel=new Label("Invoice No");

        invoiceField=new TextField();
        invoiceField.setMaxWidth(100);
        invoiceField.setText(no);

        HBox hBox5=new HBox(10);
        hBox5.getChildren().addAll(invoiceNoLabel,invoiceField);
        hBox5.setAlignment(Pos.BASELINE_RIGHT);
        hBox5.setPadding(new Insets(0,10,0,10));

        TableColumn sn=new TableColumn<>("S.N");
        sn.setMinWidth(120);
        sn.setCellValueFactory(new PropertyValueFactory<>("sn"));


        TableColumn Particulars=new TableColumn("Particulars");
        Particulars.setMinWidth(200);
        Particulars.setCellValueFactory(new PropertyValueFactory<>("Particulars"));

        TableColumn qtyAvailable=new TableColumn("Qty");
        qtyAvailable.setMinWidth(120);
        qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));


        TableColumn BatchNo=new TableColumn("Batch");
        BatchNo.setMinWidth(140);
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));

        TableColumn expDate=new TableColumn("EXP");
        expDate.setMinWidth(190);
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn Mrp=new TableColumn("MRP");
        Mrp.setMinWidth(120);
        Mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));

        TableColumn amount=new TableColumn("Amount");
        amount.setMinWidth(120);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        billTable=new TableView<>();
        tableData= FXCollections.observableArrayList();

        billTable.getColumns().addAll(sn,Particulars,qtyAvailable,BatchNo,expDate,Mrp,amount);
// to store data in the table and display

        try {
            String invoiceno=invoiceField.getText();
            String sql="select * from Bills where BillNo='"+invoiceno+"' order by SN";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                tAmountField.setText(resultSet.getString("TotalAmount"));
                dateField.setText(resultSet.getString("BillDate"));
                tableData.add(new ViewBillsTable(
                        resultSet.getString("SN"),
                        resultSet.getString("DrugName"),
                        resultSet.getString("Qty"),
                        resultSet.getString("BatchNo"),
                        resultSet.getString("ExpDate"),
                        resultSet.getString("MRP"),
                        resultSet.getString("Amount"),
                        resultSet.getString("BillDate")


                ));
                billTable.setItems(tableData);
            }



        }catch (Exception e5){

        }finally {
            try {

                preparedStatement.close();
                resultSet.close();

            }catch (Exception e6){

            }
        }


        VBox vBox1=new VBox(3);
        vBox1.getChildren().addAll(hBox3,stackPane1,hBox4,hBox5,billTable);
        vBox1.setPadding(new Insets(0,65,5,50));
        layout.setCenter(vBox1);


        scene13=new Scene(layout,1100,650);
        window13.setScene(scene13);
        window13.show();

    }
}
