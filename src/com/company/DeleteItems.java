package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.soap.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Created by Khubaib ch on 7/19/2017.
 */
public class DeleteItems {
    Stage window8;
    Label title,drugName,qtyAvailable,expDate,batchNo,mrp,rate,siNo,deleteAllItems,deleteEveryThing,deleteBills,deleteReports;
    ComboBox<String> drugNames;
    ObservableList<String> Data;
    TextField qtyAvailableField,expDateField,batchNoField,mrpField,rateField;
    Button refresh,delete,deleteAllButton,deleteEveryButton,deleteBillsButton,deleteReportButton,back;
    Scene scene8;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void deleteItems(){

        window8=new Stage();
        window8.setTitle("Delete Items");

        BorderPane layout =new BorderPane();
        layout.setStyle("-fx-background-color:gray");

        title=new Label("Delete Items");
        title.setStyle("-fx-font-size:250%;-fx-font-weight:bold");

        connection=DataBaseConnection.ConnectDB();

        refresh=new Button("Refresh");
        refresh.setMinWidth(120);
        refresh.setOnAction(e->{
            window8.close();
            DeleteItems obj=new DeleteItems();
            obj.deleteItems();
        });

        HBox hBox=new HBox(180);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setPadding(new Insets(30,160,10,10));
        hBox.getChildren().addAll(title,refresh);

        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(20,10,10,40));
        gridPane.setHgap(40);
        gridPane.setVgap(10);

        drugName=new Label("Drug Name");
        drugName.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(drugName,1,2);

        qtyAvailable=new Label("QTY Available");
        qtyAvailable.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(qtyAvailable,2,2);

        expDate=new Label("Exp Date");
        expDate.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(expDate,3,2);

        batchNo =new Label("Batch No");
        batchNo.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(batchNo,4,2);

        mrp=new Label("MRP");
        mrp.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(mrp,5,2);

        rate=new Label("Rate");
        rate.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(rate,6,2);

        siNo=new Label("SI No");
        siNo.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo,0,4);

        drugNames=new ComboBox<>();
        drugNames.setMinWidth(230);
        drugNames.setEditable(true);
        GridPane.setConstraints(drugNames,1,4);

        Data = FXCollections.observableArrayList();
        try {
            String sql = "select Particulars from AddDrugs";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Data.add(
                        resultSet.getString("Particulars")

                );

                drugNames.getItems().addAll(Data);

            }

        } catch (Exception e1) {

            JOptionPane.showMessageDialog(null, e1);
        }

        drugNames.setOnAction(e->{
            try {
                String medicine=drugNames.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    qtyAvailableField.setText(resultSet.getString("Qty"));
                    expDateField.setText(resultSet.getString("ExpDate"));
                    batchNoField.setText(resultSet.getString("BatchNo"));
                    mrpField.setText(resultSet.getString("MRP"));
                    rateField.setText(resultSet.getString("Rate"));
                }

            }catch (Exception e2){

            }finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                }catch (Exception e3){

                }
            }
        });



        qtyAvailableField= new TextField();
        qtyAvailableField.setMaxWidth(80);
        GridPane.setConstraints(qtyAvailableField,2,4);

        expDateField= new TextField();
        expDateField.setMaxWidth(100);
        GridPane.setConstraints(expDateField,3,4);

        batchNoField=new TextField();
        batchNoField.setMaxWidth(80);
        GridPane.setConstraints(batchNoField,4,4);

        mrpField=new TextField();
        mrpField.setMaxWidth(80);
        GridPane.setConstraints(mrpField,5,4);

        rateField=new TextField();
        rateField.setMaxWidth(80);
        GridPane.setConstraints(rateField,6,4);

        delete=new Button("Delete");
        delete.setMinWidth(100);
        GridPane.setConstraints(delete,7,4);
        delete.setOnAction(e->{
            try {
                String medicine=drugNames.getValue();
                String sql="delete from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null,"Drug Deleted");

            }catch (Exception e1){

                JOptionPane.showMessageDialog(null,e1);
            }finally {
                try {
                    preparedStatement.close();
                }catch (Exception e2){

                }
            }
        });

        gridPane.getChildren().addAll(drugName,qtyAvailable,expDate,batchNo,mrp,rate,
                siNo,drugNames,qtyAvailableField,expDateField,batchNoField,mrpField,rateField,delete);

        deleteAllItems=new Label("Delete all items which have zero(0) quantity available");
        deleteAllItems.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        deleteAllButton=new Button("Delete");
        deleteAllButton.setMinWidth(100);

        HBox hBox1=new HBox(50);
        hBox1.getChildren().addAll(deleteAllItems,deleteAllButton);

        deleteEveryThing=new Label("Delete EveryThing!");
        deleteEveryThing.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        deleteEveryButton=new Button("Delete");
        deleteEveryButton.setMinWidth(100);

        HBox hBox2=new HBox(283);
        hBox2.getChildren().addAll(deleteEveryThing,deleteEveryButton);

        deleteBills=new Label("Delete Bills");
        deleteBills.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        deleteBillsButton=new Button("Delete");
        deleteBillsButton.setMinWidth(100);

        HBox hBox3=new HBox(335);
        hBox3.getChildren().addAll(deleteBills,deleteBillsButton);

        deleteReports=new Label("Delete Reports");
        deleteReports.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        deleteReportButton=new Button("Delete");
        deleteReportButton.setMinWidth(100);

        HBox hBox4=new HBox(310);
        hBox4.getChildren().addAll(deleteReports,deleteReportButton);

        back=new Button("Back");
        back.setMinWidth(100);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window8.close();

        });

        HBox hBox5=new HBox();
        hBox5.getChildren().addAll(back);
        hBox5.setPadding(new Insets(30,0,0,10));

        VBox vBox=new VBox(20);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5);
        vBox.setPadding(new Insets(50,10,80,40));
        vBox.setAlignment(Pos.BASELINE_LEFT);

        layout.setTop(hBox);
        layout.setCenter(gridPane);
        layout.setBottom(vBox);

        scene8=new Scene(layout,1200,550);
        window8.setScene(scene8);
        window8.show();

    }
}
