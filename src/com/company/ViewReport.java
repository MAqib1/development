package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.table.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/22/2017.
 */
public class ViewReport {
    Stage window10;
    Label titleLabel,startDateLabel,endDateLabel,saleLabel,tillLabel;
    TextField startDayField,endDayField;
    ChoiceBox<Integer> startMonthChoiceBox,endMonthChoiceBox,startYearChoiceBox,endYearChoiceBox;
    Button ok,back;
    TableView<ViewReportTable> reportTable;
    ObservableList<ViewReportTable> tableData;

    Scene scene10;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void viewReport(){
        window10=new Stage();
        window10.setTitle("View Report");

        connection=DataBaseConnection.ConnectDB();

        titleLabel=new Label("View Report");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");


        StackPane stackPane=new StackPane();
        stackPane.setAlignment(Pos.BASELINE_CENTER);
        stackPane.getChildren().add(titleLabel);

        startDateLabel=new Label("Date");
        startDateLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        endDateLabel=new Label("Date");
        endDateLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        HBox hBox=new HBox(328);
        hBox.setPadding(new Insets(10,10,10,150));
        hBox.getChildren().addAll(startDateLabel,endDateLabel);

        saleLabel=new Label("Total Sale From");
        saleLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        startDayField= new TextField();
        startDayField.setMaxWidth(50);

        startMonthChoiceBox=new ChoiceBox<>();
        startMonthChoiceBox.setMinWidth(115);
        startMonthChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        startMonthChoiceBox.setValue(1);
        startYearChoiceBox=new ChoiceBox<>();
        startYearChoiceBox.setMinWidth(80);
        startYearChoiceBox.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        startYearChoiceBox.setValue(2017);
        HBox hBox1=new HBox(15);
        hBox1.getChildren().addAll(startDayField,startMonthChoiceBox,startYearChoiceBox);

        tillLabel=new Label("till");
        tillLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        endDayField= new TextField();
        endDayField.setMaxWidth(50);

        endMonthChoiceBox=new ChoiceBox<>();
        endMonthChoiceBox.setMinWidth(115);
        endMonthChoiceBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        endMonthChoiceBox.setValue(1);

        endYearChoiceBox=new ChoiceBox<>();
        endYearChoiceBox.setMinWidth(80);
        endYearChoiceBox.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        endYearChoiceBox.setValue(2017);
        HBox hBox2=new HBox(15);
        hBox2.getChildren().addAll(endDayField,endMonthChoiceBox,endYearChoiceBox);

        ok=new Button("OK");
        ok.setMinWidth(70);
        ok.setOnAction(e->{
            try {

                String startday = startDayField.getText();
                Integer startmonth = startMonthChoiceBox.getValue();
                Integer startyear = startYearChoiceBox.getValue();
                String startdate = startday + "-" + startmonth + "-" + startyear;

                String endday = endDayField.getText();
                Integer endmonth = endMonthChoiceBox.getValue();
                Integer endyear = endYearChoiceBox.getValue();
                String enddate = endday + "-" + endmonth + "-" + endyear;

                String sql = "Select * from SaleReport where InvoiceDate between '" +startdate+ "' and '" +enddate+ "'";
                preparedStatement = connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()){
                    tableData.add(new ViewReportTable(
                            resultSet.getString("InvoiceNo"),
                            resultSet.getString("Amount"),
                            resultSet.getString("InvoiceDate")

                    ));
                    reportTable.setItems(tableData);
                }



            }catch (Exception e8){
                System.out.println(e8);

            }finally {
                try {

                }catch (Exception e9){

                }
            }
        });

        HBox hBox3=new HBox(35);
        hBox3.getChildren().addAll(saleLabel,hBox1,tillLabel,hBox2,ok);

        VBox vBox=new VBox(10);
        vBox.setPadding(new Insets(30,10,10,50));
        vBox.getChildren().addAll(stackPane,hBox,hBox3);

        BorderPane layout=new BorderPane();
        layout.setStyle("-fx-background-color:gray");
        layout.setTop(vBox);

        TableColumn invoiceNo=new TableColumn("INVOICE NO");
        invoiceNo.setMinWidth(150);
        invoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoice"));

        TableColumn amount=new TableColumn("AMOUNT");
        amount.setMinWidth(150);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn date=new TableColumn("DATE");
        date.setMinWidth(220);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        reportTable=new TableView<>();
        tableData= FXCollections.observableArrayList();
        reportTable.getColumns().addAll(invoiceNo,amount,date);

        VBox vBox1=new VBox();
        vBox1.setPadding(new Insets(10,280,10,200));
        vBox1.getChildren().addAll(reportTable);
        layout.setCenter(vBox1);

        back=new Button("Back");
        back.setMinWidth(100);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window10.close();

        });

        HBox hBox4=new HBox();
        hBox4.setPadding(new Insets(20,10,20,50));
        hBox4.getChildren().addAll(back);
        layout.setBottom(hBox4);


        scene10=new Scene(layout,1000,650);
        window10.setScene(scene10);
        window10.show();

    }
}
