package com.company;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/22/2017.
 */
public class ViewBills {
    Stage window12;
    Label titleLabel,invoiceNoLabel;
    TextField invoiceNoField1,invoiceNoField2,totalAmountField;
    Button search,back;
    TableView<ViewBillsTable> billTable;
    ObservableList<ViewBillsTable> tableData;
    Scene scene12;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void viewBills(){
        window12=new Stage();
        window12.setTitle("View Bills");

        connection=DataBaseConnection.ConnectDB();

        titleLabel=new Label("View Previous Bills/Invoice");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");

        invoiceNoField1=new TextField();
        invoiceNoField1.setEditable(false);

        HBox hBox=new HBox(200);
        hBox.setPadding(new Insets(10,10,10,400));
        hBox.getChildren().addAll(titleLabel,invoiceNoField1);

        invoiceNoLabel=new Label("Enter Invoice No");
        invoiceNoLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        invoiceNoField2=new TextField();

        search=new Button("Search");
        search.setMinWidth(100);
        search.setOnAction(e->{
            try {
                String invoiceno=invoiceNoField2.getText();
                String sql="select * from Bills where BillNo='"+invoiceno+"' order by SN";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    invoiceNoField1.setText("Invoice No. "+resultSet.getString("BillNo"));
                    totalAmountField.setText("Total Amount "+resultSet.getString("TotalAmount"));
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
                }catch (Exception e6){

            }finally {
                try {

                }catch (Exception e7){

                }
            }
        });

        HBox hBox1=new HBox(20);
        hBox1.getChildren().addAll(invoiceNoLabel,invoiceNoField2,search);

        totalAmountField=new TextField();
        totalAmountField.setEditable(false);

        HBox hBox2=new HBox(570);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.getChildren().addAll(hBox1,totalAmountField);

        VBox vBox=new VBox(20);
        vBox.setPadding(new Insets(30,10,10,40));
        vBox.getChildren().addAll(hBox,hBox2);

        BorderPane layout=new BorderPane();
        layout.setStyle("-fx-background-color:gray");
        layout.setTop(vBox);

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

        TableColumn date=new TableColumn("Date");
        date.setMinWidth(150);
        date.setCellValueFactory(new PropertyValueFactory<>("invoicedate"));


        billTable=new TableView<>();
        tableData= FXCollections.observableArrayList();
        billTable.getColumns().addAll(sn,Particulars,qtyAvailable,BatchNo,expDate,Mrp,amount,date);

        VBox vBox1=new VBox();
        vBox1.setPadding(new Insets(20,20,10,40));
        vBox1.getChildren().addAll(billTable);
        layout.setCenter(vBox1);


        back=new Button("Back");
        back.setMinWidth(100);
        back.setOnAction(e->{

            HomePage object2=new HomePage();
            object2.homePage();
            window12.close();

        });

        HBox hBox3=new HBox();
        hBox3.setPadding(new Insets(20,10,30,70));
        hBox3.getChildren().addAll(back);

        layout.setBottom(hBox3);

        scene12=new Scene(layout,1200,650);
        window12.setScene(scene12);
        window12.show();



    }
}
