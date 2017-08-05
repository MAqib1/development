package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/17/2017.
 */
public class AddDrug {

    Stage window5;
    Label title,title1,batchNo1,batchNo2,batchNo3,batchNo4,batchNo5,batchNo6,batchNo7,batchNo8
            ,particulars1,particulars2,particulars3,particulars4,particulars5,particulars6,particulars7,particulars8
            ,expDate1,expDate2,expDate3,expDate4,expDate5,expDate6,expDate7,expDate8,qty1,qty2,qty3,qty4,qty5,qty6,qty7,qty8
            ,rate1,rate2,rate3,rate4,rate5,rate6,rate7,rate8,mrp1,mrp2,mrp3,mrp4,mrp5,mrp6,mrp7,mrp8;
    TextField batchNoField1,batchNoField2,batchNoField3,batchNoField4,batchNoField5,batchNoField6,batchNoField7,batchNoField8
            ,particularsField1,particularsField2,particularsField3,particularsField4,particularsField5,particularsField6,particularsField7
            ,particularsField8,qtyField1,qtyField2,qtyField3,qtyField4,qtyField5,qtyField6,qtyField7,qtyField8
            ,rateField1,rateField2,rateField3,rateField4,rateField5,rateField6,rateField7,rateField8
            ,mrpField1,mrpField2,mrpField3,mrpField4,mrpField5,mrpField6,mrpField7,mrpField8;
    ChoiceBox<Integer> month1,month2,month3,month4,month5,month6,month7,month8
            ,year1,year2,year3,year4,year5,year6,year7,year8;
    Button save,back;
    Scene scene5;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    public void addDrug(){

        //connection with database class
        connection=DataBaseConnection.ConnectDB();

        window5=new Stage();
        window5.setTitle("Add Drug");

        BorderPane layout=new BorderPane();

        title =new Label("Data Entry");
        title.setStyle("-fx-font-size:120%");
        title1=new Label("Add Drug");
        title1.setStyle("-fx-font-size:250%;-fx-font-weight:bold");


        VBox vBox=new VBox(7);
        vBox.setPadding(new Insets(15,10,20,10));
        vBox.getChildren().addAll(title,title1);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        layout.setStyle("-fx-background-color:gray");
        layout.setTop(vBox);

        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(10,30,10,30));
        gridPane.setVgap(10);
        gridPane.setHgap(30);

        //1st row

        batchNo1=new Label("Batch No");
        GridPane.setConstraints(batchNo1,0,1);

        batchNoField1=new TextField();
        batchNoField1.setMaxWidth(100);
        GridPane.setConstraints(batchNoField1,1,1);

        particulars1=new Label("Particulars");
        GridPane.setConstraints(particulars1,2,1);

        particularsField1=new TextField();
        particularsField1.setMaxWidth(230);
        GridPane.setConstraints(particularsField1,3,1);

        expDate1=new Label("ExpDate");
        GridPane.setConstraints(expDate1,4,1);

        month1=new ChoiceBox<>();
        month1.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month1.setValue(1);
        GridPane.setConstraints(month1,5,1);

        year1=new ChoiceBox<>();
        year1.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year1.setValue(2017);
        GridPane.setConstraints(year1,6,1);

        qty1=new Label("Qty");
        GridPane.setConstraints(qty1,7,1);

        qtyField1=new TextField();
        GridPane.setConstraints(qtyField1,8,1);
        qtyField1.setMaxWidth(70);

        rate1=new Label("Rate");
        GridPane.setConstraints(rate1,9,1);

        rateField1= new TextField();
        GridPane.setConstraints(rateField1,10,1);
        rateField1.setMaxWidth(80);

        mrp1=new Label("MRP");
        GridPane.setConstraints(mrp1,11,1);

        mrpField1= new TextField();
        mrpField1.setMaxWidth(80);
        GridPane.setConstraints(mrpField1,12,1);



        //2nd row

        batchNo2=new Label("Batch No");
        GridPane.setConstraints(batchNo2,0,2);

        batchNoField2=new TextField();
        batchNoField2.setMaxWidth(100);
        GridPane.setConstraints(batchNoField2,1,2);

        particulars2=new Label("Particulars");
        GridPane.setConstraints(particulars2,2,2);

        particularsField2=new TextField();
        particularsField2.setMaxWidth(230);
        GridPane.setConstraints(particularsField2,3,2);

        expDate2=new Label("ExpDate");
        GridPane.setConstraints(expDate2,4,2);

        month2=new ChoiceBox<>();
        month2.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month2.setValue(1);
        GridPane.setConstraints(month2,5,2);

        year2=new ChoiceBox<>();
        year2.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year2.setValue(2017);
        GridPane.setConstraints(year2,6,2);

        qty2=new Label("Qty");
        GridPane.setConstraints(qty2,7,2);

        qtyField2=new TextField();
        GridPane.setConstraints(qtyField2,8,2);
        qtyField2.setMaxWidth(70);

        rate2=new Label("Rate");
        GridPane.setConstraints(rate2,9,2);

        rateField2= new TextField();
        GridPane.setConstraints(rateField2,10,2);
        rateField2.setMaxWidth(80);

        mrp2=new Label("MRP");
        GridPane.setConstraints(mrp2,11,2);

        mrpField2= new TextField();
        mrpField2.setMaxWidth(80);
        GridPane.setConstraints(mrpField2,12,2);


        //3rd row

        batchNo3=new Label("Batch No");
        GridPane.setConstraints(batchNo3,0,3);

        batchNoField3=new TextField();
        batchNoField3.setMaxWidth(100);
        GridPane.setConstraints(batchNoField3,1,3);

        particulars3=new Label("Particulars");
        GridPane.setConstraints(particulars3,2,3);

        particularsField3=new TextField();
        particularsField3.setMaxWidth(230);
        GridPane.setConstraints(particularsField3,3,3);

        expDate3=new Label("ExpDate");
        GridPane.setConstraints(expDate3,4,3);

        month3=new ChoiceBox<>();
        month3.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month3.setValue(1);
        GridPane.setConstraints(month3,5,3);

        year3=new ChoiceBox<>();
        year3.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year3.setValue(2017);

        GridPane.setConstraints(year3,6,3);

        qty3=new Label("Qty");
        GridPane.setConstraints(qty3,7,3);

        qtyField3=new TextField();
        GridPane.setConstraints(qtyField3,8,3);
        qtyField3.setMaxWidth(70);

        rate3=new Label("Rate");
        GridPane.setConstraints(rate3,9,3);

        rateField3= new TextField();
        GridPane.setConstraints(rateField3,10,3);
        rateField3.setMaxWidth(80);

        mrp3=new Label("MRP");
        GridPane.setConstraints(mrp3,11,3);

        mrpField3= new TextField();
        mrpField3.setMaxWidth(80);
        GridPane.setConstraints(mrpField3,12,3);


        //4nd row

        batchNo4=new Label("Batch No");
        GridPane.setConstraints(batchNo4,0,4);

        batchNoField4=new TextField();
        batchNoField4.setMaxWidth(100);
        GridPane.setConstraints(batchNoField4,1,4);

        particulars4=new Label("Particulars");
        GridPane.setConstraints(particulars4,2,4);

        particularsField4=new TextField();
        particularsField4.setMaxWidth(230);
        GridPane.setConstraints(particularsField4,3,4);

        expDate4=new Label("ExpDate");
        GridPane.setConstraints(expDate4,4,4);

        month4=new ChoiceBox<>();
        month4.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month4.setValue(1);
        GridPane.setConstraints(month4,5,4);

        year4=new ChoiceBox<>();
        year4.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year4.setValue(2017);
        GridPane.setConstraints(year4,6,4);

        qty4=new Label("Qty");
        GridPane.setConstraints(qty4,7,4);

        qtyField4=new TextField();
        GridPane.setConstraints(qtyField4,8,4);
        qtyField4.setMaxWidth(70);

        rate4=new Label("Rate");
        GridPane.setConstraints(rate4,9,4);

        rateField4= new TextField();
        GridPane.setConstraints(rateField4,10,4);
        rateField4.setMaxWidth(80);

        mrp4=new Label("MRP");
        GridPane.setConstraints(mrp4,11,4);

        mrpField4= new TextField();
        mrpField4.setMaxWidth(80);
        GridPane.setConstraints(mrpField4,12,4);

        //5th row

        batchNo5=new Label("Batch No");
        GridPane.setConstraints(batchNo5,0,5);

        batchNoField5=new TextField();
        batchNoField5.setMaxWidth(100);
        GridPane.setConstraints(batchNoField5,1,5);

        particulars5=new Label("Particulars");
        GridPane.setConstraints(particulars5,2,5);

        particularsField5=new TextField();
        particularsField5.setMaxWidth(230);
        GridPane.setConstraints(particularsField5,3,5);

        expDate5=new Label("ExpDate");
        GridPane.setConstraints(expDate5,4,5);

        month5=new ChoiceBox<>();
        month5.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month5.setValue(1);
        GridPane.setConstraints(month5,5,5);

        year5=new ChoiceBox<>();
        year5.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year5.setValue(2017);

        GridPane.setConstraints(year5,6,5);

        qty5=new Label("Qty");
        GridPane.setConstraints(qty5,7,5);

        qtyField5=new TextField();
        GridPane.setConstraints(qtyField5,8,5);
        qtyField5.setMaxWidth(70);

        rate5=new Label("Rate");
        GridPane.setConstraints(rate5,9,5);

        rateField5= new TextField();
        GridPane.setConstraints(rateField5,10,5);
        rateField5.setMaxWidth(80);

        mrp5=new Label("MRP");
        GridPane.setConstraints(mrp5,11,5);

        mrpField5= new TextField();
        mrpField5.setMaxWidth(80);
        GridPane.setConstraints(mrpField5,12,5);


        //6th row

        batchNo6=new Label("Batch No");
        GridPane.setConstraints(batchNo6,0,6);

        batchNoField6=new TextField();
        batchNoField6.setMaxWidth(100);
        GridPane.setConstraints(batchNoField6,1,6);

        particulars6=new Label("Particulars");
        GridPane.setConstraints(particulars6,2,6);

        particularsField6=new TextField();
        particularsField6.setMaxWidth(230);
        GridPane.setConstraints(particularsField6,3,6);

        expDate6=new Label("ExpDate");
        GridPane.setConstraints(expDate6,4,6);

        month6=new ChoiceBox<>();
        month6.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month6.setValue(1);
        GridPane.setConstraints(month6,5,6);

        year6=new ChoiceBox<>();
        year6.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year6.setValue(2017);
        GridPane.setConstraints(year6,6,6);

        qty6=new Label("Qty");
        GridPane.setConstraints(qty6,7,6);

        qtyField6=new TextField();
        GridPane.setConstraints(qtyField6,8,6);
        qtyField6.setMaxWidth(70);

        rate6=new Label("Rate");
        GridPane.setConstraints(rate6,9,6);

        rateField6= new TextField();
        GridPane.setConstraints(rateField6,10,6);
        rateField6.setMaxWidth(80);

        mrp6=new Label("MRP");
        GridPane.setConstraints(mrp6,11,6);

        mrpField6= new TextField();
        mrpField6.setMaxWidth(80);
        GridPane.setConstraints(mrpField6,12,6);


        //7th row

        batchNo7=new Label("Batch No");
        GridPane.setConstraints(batchNo7,0,7);

        batchNoField7=new TextField();
        batchNoField7.setMaxWidth(100);
        GridPane.setConstraints(batchNoField7,1,7);

        particulars7=new Label("Particulars");
        GridPane.setConstraints(particulars7,2,7);

        particularsField7=new TextField();
        particularsField7.setMaxWidth(230);
        GridPane.setConstraints(particularsField7,3,7);

        expDate7=new Label("ExpDate");
        GridPane.setConstraints(expDate7,4,7);

        month7=new ChoiceBox<>();
        month7.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month7.setValue(1);
        GridPane.setConstraints(month7,5,7);

        year7=new ChoiceBox<>();
        year7.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year7.setValue(2017);

        GridPane.setConstraints(year7,6,7);

        qty7=new Label("Qty");
        GridPane.setConstraints(qty7,7,7);

        qtyField7=new TextField();
        GridPane.setConstraints(qtyField7,8,7);
        qtyField7.setMaxWidth(70);

        rate7=new Label("Rate");
        GridPane.setConstraints(rate7,9,7);

        rateField7= new TextField();
        GridPane.setConstraints(rateField7,10,7);
        rateField7.setMaxWidth(80);

        mrp7=new Label("MRP");
        GridPane.setConstraints(mrp7,11,7);

        mrpField7= new TextField();
        mrpField7.setMaxWidth(80);
        GridPane.setConstraints(mrpField7,12,7);


        //8th row

        batchNo8=new Label("Batch No");
        GridPane.setConstraints(batchNo8,0,8);

        batchNoField8=new TextField();
        batchNoField8.setMaxWidth(100);
        GridPane.setConstraints(batchNoField8,1,8);

        particulars8=new Label("Particulars");
        GridPane.setConstraints(particulars8,2,8);

        particularsField8=new TextField();
        particularsField8.setMaxWidth(230);
        GridPane.setConstraints(particularsField8,3,8);

        expDate8=new Label("ExpDate");
        GridPane.setConstraints(expDate8,4,8);

        month8=new ChoiceBox<>();
        month8.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        month8.setValue(1);
        GridPane.setConstraints(month8,5,8);

        year8=new ChoiceBox<>();
        year8.getItems().addAll(2017,2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);
        year8.setValue(2017);
        GridPane.setConstraints(year8,6,8);

        qty8=new Label("Qty");
        GridPane.setConstraints(qty8,7,8);

        qtyField8=new TextField();
        GridPane.setConstraints(qtyField8,8,8);
        qtyField8.setMaxWidth(70);

        rate8=new Label("Rate");
        GridPane.setConstraints(rate8,9,8);

        rateField8= new TextField();
        GridPane.setConstraints(rateField8,10,8);
        rateField8.setMaxWidth(80);

        mrp8=new Label("MRP");
        GridPane.setConstraints(mrp8,11,8);

        mrpField8= new TextField();
        mrpField8.setMaxWidth(80);
        GridPane.setConstraints(mrpField8,12,8);


        gridPane.getChildren().addAll(batchNo1,batchNoField1,particulars1,particularsField1,expDate1,month1,year1,qty1,qtyField1,rate1,rateField1,mrp1,mrpField1,
                batchNo2,batchNoField2,particulars2,particularsField2,expDate2,month2,year2,qty2,qtyField2,rate2,rateField2,mrp2,mrpField2,
                batchNo3,batchNoField3,particulars3,particularsField3,expDate3,month3,year3,qty3,qtyField3,rate3,rateField3,mrp3,mrpField3,
                batchNo4,batchNoField4,particulars4,particularsField4,expDate4,month4,year4,qty4,qtyField4,rate4,rateField4,mrp4,mrpField4,
                batchNo5,batchNoField5,particulars5,particularsField5,expDate5,month5,year5,qty5,qtyField5,rate5,rateField5,mrp5,mrpField5,
                batchNo6,batchNoField6,particulars6,particularsField6,expDate6,month6,year6,qty6,qtyField6,rate6,rateField6,mrp6,mrpField6,
                batchNo7,batchNoField7,particulars7,particularsField7,expDate7,month7,year7,qty7,qtyField7,rate7,rateField7,mrp7,mrpField7,
                batchNo8,batchNoField8,particulars8,particularsField8,expDate8,month8,year8,qty8,qtyField8,rate8,rateField8,mrp8,mrpField8);
        layout.setCenter(gridPane);

        save=new Button("Save");
        save.setMinWidth(120);
        save.setOnAction(e->{
            String sql="Insert into AddDrugs(BatchNo,Particulars,ExpDate,Qty,Rate,MRP,Month,Year)Values(?,?,?,?,?,?,?,?)";
            try {

                //store first row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField1.getText())));
                preparedStatement.setString(2,particularsField1.getText());
                Integer months1=month1.getValue();
                Integer years1=year1.getValue();
                String date1=months1+"-"+years1;
                preparedStatement.setString(3,date1);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField1.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField1.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField1.getText())));
                preparedStatement.setString(7,String.valueOf(months1));
                preparedStatement.setString(8,String.valueOf(years1));
                preparedStatement.execute();



                //store 2nd row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField2.getText())));
                preparedStatement.setString(2,particularsField2.getText());
                Integer months2=month1.getValue();
                Integer years2=year1.getValue();
                String date2=months1+"-"+years1;
                preparedStatement.setString(3,date2);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField2.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField2.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField2.getText())));
                preparedStatement.setString(7,String.valueOf(months2));
                preparedStatement.setString(8,String.valueOf(years2));
                preparedStatement.execute();

                //store 3rd row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField3.getText())));
                preparedStatement.setString(2,particularsField3.getText());
                Integer months3=month3.getValue();
                Integer years3=year3.getValue();
                String date3=months3+"-"+years3;
                preparedStatement.setString(3,date3);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField3.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField3.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField3.getText())));
                preparedStatement.setString(7,String.valueOf(months3));
                preparedStatement.setString(8,String.valueOf(years3));
                preparedStatement.execute();

                //store 4th row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField4.getText())));
                preparedStatement.setString(2,particularsField4.getText());
                Integer months4=month4.getValue();
                Integer years4=year4.getValue();
                String date4=months4+"-"+years4;
                preparedStatement.setString(3,date4);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField4.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField4.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField4.getText())));
                preparedStatement.setString(7,String.valueOf(months4));
                preparedStatement.setString(8,String.valueOf(years4));
                preparedStatement.execute();

                //store 5th row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField5.getText())));
                preparedStatement.setString(2,particularsField5.getText());
                Integer months5=month5.getValue();
                Integer years5=year5.getValue();
                String date5=months5+"-"+years5;
                preparedStatement.setString(3,date5);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField5.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField5.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField5.getText())));
                preparedStatement.setString(7,String.valueOf(months5));
                preparedStatement.setString(8,String.valueOf(years5));
                preparedStatement.execute();

                //store 6th row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField6.getText())));
                preparedStatement.setString(2,particularsField6.getText());
                Integer months6=month6.getValue();
                Integer years6=year6.getValue();
                String date6=months6+"-"+years6;
                preparedStatement.setString(3,date6);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField6.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField6.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField6.getText())));
                preparedStatement.setString(7,String.valueOf(months6));
                preparedStatement.setString(8,String.valueOf(years6));
                preparedStatement.execute();

                //store 7th row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField7.getText())));
                preparedStatement.setString(2,particularsField7.getText());
                Integer months7=month7.getValue();
                Integer years7=year7.getValue();
                String date7=months7+"-"+years7;
                preparedStatement.setString(3,date7);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField7.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField7.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField7.getText())));
                preparedStatement.setString(7,String.valueOf(months7));
                preparedStatement.setString(8,String.valueOf(years7));
                preparedStatement.execute();

                //store 8th row data into database

                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(Integer.parseInt(batchNoField8.getText())));
                preparedStatement.setString(2,particularsField8.getText());
                Integer months8=month8.getValue();
                Integer years8=year8.getValue();
                String date8=months8+"-"+years8;
                preparedStatement.setString(3,date8);
                preparedStatement.setString(4,String.valueOf(Integer.parseInt(qtyField8.getText())));
                preparedStatement.setString(5,String.valueOf(Integer.parseInt(rateField8.getText())));
                preparedStatement.setString(6,String.valueOf(Integer.parseInt(mrpField8.getText())));
                preparedStatement.setString(7,String.valueOf(months8));
                preparedStatement.setString(8,String.valueOf(years8));
                preparedStatement.execute();



            }catch (Exception ex){

            }finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                }catch (Exception e1){

                }

                JOptionPane.showMessageDialog(null,"Drugs Add Successfully");

            }
        });

        back=new Button("Back");
        back.setMinWidth(120);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window5.close();

        });

        HBox hBox=new HBox(70);
        hBox.setPadding(new Insets(10,10,60,10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(back,save);
        layout.setBottom(hBox);

        scene5=new Scene(layout,1300,550);
        window5.setScene(scene5);
        window5.show();
    }
}
