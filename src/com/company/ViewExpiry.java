package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/22/2017.
 */
public class ViewExpiry {
    Stage window11;
    Label titleLabel,epiryDateLabel,beforeLabel;
    ChoiceBox<Integer> MonthChoiceBox,YearChoiceBox;
    Button check,back,clear;
    TableView<ViewStockTable> expiryTable;
    ObservableList<ViewStockTable> tableData;
    Scene scene11;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public void viewExpiry(){

        window11=new Stage();
        window11.setTitle("View Report");

        connection=DataBaseConnection.ConnectDB();

        titleLabel=new Label("View Expiry Date");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");


        StackPane stackPane=new StackPane();
        stackPane.setAlignment(Pos.BASELINE_CENTER);
        stackPane.getChildren().add(titleLabel);

        epiryDateLabel=new Label("Enter Expiry Date");
        epiryDateLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        beforeLabel=new Label("Before");
        beforeLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        MonthChoiceBox=new ChoiceBox<>();
        MonthChoiceBox.setMinWidth(115);
        MonthChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        MonthChoiceBox.setValue(1);

        YearChoiceBox=new ChoiceBox<>();
        YearChoiceBox.setMinWidth(80);
        YearChoiceBox.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        YearChoiceBox.setValue(2017);

        check=new Button("Check");
        check.setMinWidth(70);
        check.setOnAction(e->{
        try {
            Integer month=MonthChoiceBox.getValue();
            Integer year=YearChoiceBox.getValue();
            String sql="select * from AddDrugs where (Month<='"+month+"' and year<='"+year+"') or Year<'"+year+"'";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                tableData.add(new ViewStockTable(
                        resultSet.getString("Particulars"),
                        resultSet.getString("Qty"),
                        resultSet.getString("BatchNo"),
                        resultSet.getString("MRP"),
                        resultSet.getString("Rate"),
                        resultSet.getString("ExpDate")



                ));
                expiryTable.setItems(tableData);
            }

        }catch (Exception e1){

            JOptionPane.showMessageDialog(null,e1);
        }finally {
            try {
                preparedStatement.close();
                resultSet.close();
            }catch (Exception e2){

            }
        }
    });


    HBox hBox1=new HBox(20);
        hBox1.getChildren().addAll(MonthChoiceBox,YearChoiceBox,check);

        HBox hBox2=new HBox(30);
        hBox2.getChildren().addAll(epiryDateLabel,beforeLabel,hBox1);

        VBox vBox=new VBox(30);
        vBox.setPadding(new Insets(30,10,10,60));
        vBox.getChildren().addAll(stackPane,hBox2);

        BorderPane layout=new BorderPane();
        layout.setStyle("-fx-background-color:gray");
        layout.setTop(vBox);

        TableColumn Particulars=new TableColumn("Particulars");
        Particulars.setMinWidth(230);
        Particulars.setCellValueFactory(new PropertyValueFactory<>("Particulars"));

        TableColumn qtyAvailable=new TableColumn("Qty");
        qtyAvailable.setMinWidth(120);
        qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));


        TableColumn BatchNo=new TableColumn("Batch No");
        BatchNo.setMinWidth(140);
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));

        TableColumn Mrp=new TableColumn("MRP");
        Mrp.setMinWidth(120);
        Mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));

        TableColumn Rate=new TableColumn<>("Rate");
        Rate.setMinWidth(120);
        Rate.setCellValueFactory(new PropertyValueFactory<>("rate"));

        TableColumn expDate=new TableColumn("Expiry Date");
        expDate.setMinWidth(190);
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        expiryTable=new TableView<>();
        tableData= FXCollections.observableArrayList();
        expiryTable.getColumns().addAll(Particulars,qtyAvailable,BatchNo,Mrp,Rate,expDate);

        VBox vBox1=new VBox();
        vBox1.setPadding(new Insets(25,60,10,60));
        vBox1.getChildren().addAll(expiryTable);
        layout.setCenter(vBox1);

        back=new Button("Back");
        back.setMinWidth(100);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window11.close();

        });

        clear=new Button("Clear");
        clear.setMinWidth(100);
        clear.setOnAction(e->{
            tableData.clear();
        });

        HBox hBox3=new HBox(260);
        hBox3.setPadding(new Insets(20,10,30,60));
        hBox3.getChildren().addAll(back,clear);

        layout.setBottom(hBox3);



        scene11=new Scene(layout,1040,650);
        window11.setScene(scene11);
        window11.show();


    }
}
