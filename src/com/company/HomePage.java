package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.YEAR;

/**
 * Created by Khubaib ch on 7/15/2017.
 */
public class HomePage {

    Stage window1;

    Label title,billNo,date,DrugName,expDate,batchNo,mrp,rate,amount,siNo1,siNo2
            ,siNo3,siNo4,siNo5,siNo6,siNo7,siNo8,siNo9,siNo10;
    TextField billNoTextField,dateTextField,qty1,totalQty1,expDate1,batchNo1,mrp1,rate1,amount1
            ,qty2,totalQty2,expDate2,batchNo2,mrp2,rate2,amount2,qty3,totalQty3,expDate3,batchNo3,mrp3,rate3,amount3
            ,qty4,totalQty4,expDate4,batchNo4,mrp4,rate4,amount4,qty5,totalQty5,expDate5,batchNo5,mrp5,rate5,amount5
            ,qty6,totalQty6,expDate6,batchNo6,mrp6,rate6,amount6,qty7,totalQty7,expDate7,batchNo7,mrp7,rate7,amount7
            ,qty8,totalQty8,expDate8,batchNo8,mrp8,rate8,amount8,qty9,totalQty9,expDate9,batchNo9,mrp9,rate9,amount9
            ,qty10,totalQty10,expDate10,batchNo10,mrp10,rate10,amount10,total;
    ComboBox<String> drugName1,drugName2,drugName3,drugName4,drugName5,drugName6,drugName7,
    drugName8,drugName9,drugName10;
    ObservableList<String> drugName;
    Menu file,edit;
    Button add,delete,viewStock,viewExpiry,viewBill,Report,remove1,remove2,remove3,remove4,remove5,remove6,remove7
            ,remove8,remove9,remove10,addMore,calculateAmountR,calculateAmountW,goToBill,calculateTotal;
    Scene scene1;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    Total object=new Total();

    public void homePage(){

        window1=new Stage();
        window1.setTitle("Home Page");
//        drugName1.setEditable(true);
//        TextFields.bindAutoCompletion(drugName1.getEditor(), drugName1.getItems());
        connection=DataBaseConnection.ConnectDB();

        file=new Menu("File");

        MenuItem logout=new MenuItem("Logout");
        logout.setOnAction(e->{
            Login object1=new Login();
            object1.login();
            window1.close();
        });

        file.getItems().addAll(logout);

        edit=new Menu("Edit");

        MenuItem changePassword=new MenuItem("Change Password");
        changePassword.setOnAction(e->{
            ChangePassword obj=new ChangePassword();
            obj.changePassword();
            window1.close();

        });

        MenuItem newUser=new MenuItem("Create New User");
        newUser.setOnAction(e->{
            CreateNewUser object=new CreateNewUser();
            object.createNewUser();
            window1.close();

        });

        edit.getItems().addAll(changePassword,newUser);


        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(file,edit);

        add=new Button("Add Items");
        add.setMinWidth(120);
        add.setOnAction(e->{
            AddDrug object3=new AddDrug();
            object3.addDrug();
            window1.close();

        });

        delete=new Button("Delete Items");
        delete.setMinWidth(120);
        delete.setOnAction(e->{
            DeleteItems object4=new DeleteItems();
            object4.deleteItems();
            window1.close();

        });

        viewStock=new Button("View Stock");
        viewStock.setMinWidth(120);
        viewStock.setOnAction(e->{
            ViewStock object5=new ViewStock();
            object5.viewStock();
            window1.close();

        });

        viewExpiry=new Button("View Expiry Date");
        viewExpiry.setMinWidth(120);
        viewExpiry.setOnAction(e->{
            ViewExpiry object8=new ViewExpiry();
            object8.viewExpiry();
            window1.close();
        });

        viewBill=new Button("View Bill");
        viewBill.setMinWidth(120);
        viewBill.setOnAction(e->{
            ViewBills object9=new ViewBills();
            object9.viewBills();
            window1.close();

        });

        Report=new Button("Report");
        Report.setMinWidth(120);
        Report.setOnAction(e->{
            ViewReport object7=new ViewReport();
            object7.viewReport();
            window1.close();
        });

        HBox hBox=new HBox(30);
        hBox.setPadding(new Insets(10,10,10,80));
        hBox.getChildren().addAll(add,delete,viewStock,viewExpiry,viewBill,Report);

        title=new Label("Retail Sale");
        HBox hBox1=new HBox();
        hBox1.getChildren().addAll(title);
        hBox1.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size:250%;-fx-font-weight:bold");

        billNo=new Label("Bill No");
        billNo.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        billNoTextField=new TextField();
        billNoTextField.setEditable(false);
        billNoTextField.setMaxWidth(90);

        try {
            String sql15="select InvoiceNo from SaleReport order by InvoiceNo desc limit 1";
            preparedStatement=connection.prepareStatement(sql15);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){

                billNoTextField.setText(String.valueOf((1+Integer.parseInt((resultSet.getString("InvoiceNo"))))));
            }

        }catch (Exception e2){

        }finally {
            try {
                preparedStatement.close();
                resultSet.close();
            }catch (Exception e3){

            }
        }

        date=new Label("Date");
        date.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


            Calendar calendar=new GregorianCalendar();
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            int month=calendar.get(Calendar.MONTH);
            int year=calendar.get(Calendar.YEAR);





        dateTextField=new TextField();
        dateTextField.setEditable(false);
        dateTextField.setMaxWidth(90);
        dateTextField.setText(""+day+"-"+(month+1)+"-"+year);



        HBox hBox2=new HBox(10);
        hBox2.getChildren().addAll(billNo,billNoTextField,date,dateTextField);
        hBox2.setAlignment(Pos.BASELINE_RIGHT);
        hBox2.setPadding(new Insets(0,80,0,0));


        VBox vBox=new VBox(10);
        vBox.getChildren().addAll(menuBar,hBox,hBox1,hBox2);

        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(10,30,0,30));
        gridPane.setVgap(10);
        gridPane.setHgap(50);

        DrugName=new Label("Drug Name");
        DrugName.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(DrugName,1,1);

        JLabel label=new JLabel(new ImageIcon(""));
//        qty.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
//        GridPane.setConstraints(qty,2,1);

        expDate=new Label("Exp Date");
        expDate.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(expDate,3,1);

        batchNo=new Label("Batch No");
        batchNo.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        mrp=new Label("MRP");
        mrp.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        rate=new Label("Rate");
        rate.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        HBox hBox3=new HBox(45);
        hBox3.getChildren().addAll(batchNo,mrp,rate);
        GridPane.setConstraints(hBox3,4,1);

        amount=new Label("Amount(Retail)");
        amount.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(amount,5,1);


        //1st row
        siNo1=new Label("SI No 1");
        siNo1.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo1,0,2);


        drugName1=new ComboBox<>();

        drugName1.setTooltip(new Tooltip());


        drugName1.setMinWidth(230);
        drugName1.setEditable(true);
        GridPane.setConstraints(drugName1,1,2);
        drugName1.setOnAction(e->{
            try {
                String medicine=drugName1.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty1.setText(resultSet.getString("Qty"));
                    expDate1.setText(resultSet.getString("ExpDate"));
                    batchNo1.setText(resultSet.getString("BatchNo"));
                    mrp1.setText(resultSet.getString("MRP"));
                    rate1.setText(resultSet.getString("Rate"));
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


        qty1=new TextField();
        qty1.setMaxWidth(70);
        qty1.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty1.getText());
                int marketRate = Integer.parseInt(mrp1.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount1.setText(totalamount);
            }catch (Exception es){

            }
        });


        totalQty1=new TextField();
        totalQty1.setMaxWidth(70);
        totalQty1.setStyle("-fx-background-color:gray");
        totalQty1.setEditable(false);

        HBox hBox4=new HBox(10);
        hBox4.getChildren().addAll(qty1,totalQty1);
        GridPane.setConstraints(hBox4,2,2);

        expDate1=new TextField();
        expDate1.setMaxWidth(100);
        GridPane.setConstraints(expDate1,3,2);

        batchNo1=new TextField();
        batchNo1.setMaxWidth(80);
        mrp1=new TextField();
        mrp1.setMaxWidth(80);
        rate1=new TextField();
        rate1.setMaxWidth(80);

        HBox hBox5=new HBox(10);
        hBox5.getChildren().addAll(batchNo1,mrp1,rate1);
        GridPane.setConstraints(hBox5,4,2);

        amount1=new TextField();
        amount1.setMaxWidth(90);
        amount1.setText("0");

        remove1=new Button("Remove");
        remove1.setOnAction(e->{
            drugName1.setValue("");
            qty1.clear();
            totalQty1.clear();
            expDate1.clear();
            batchNo1.clear();
            mrp1.clear();
            rate1.clear();
            amount1.clear();
        });

        HBox hBox6=new HBox(10);
        hBox6.getChildren().addAll(amount1,remove1);
        GridPane.setConstraints(hBox6,5,2);
//2nd row
        siNo2=new Label("SI No 2");
        siNo2.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo2,0,3);


        drugName2=new ComboBox<>();
        drugName2.setMinWidth(230);
        drugName2.setEditable(true);
        GridPane.setConstraints(drugName2,1,3);
        drugName2.setOnAction(e->{
            try {
                String medicine=drugName2.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty2.setText(resultSet.getString("Qty"));
                    expDate2.setText(resultSet.getString("ExpDate"));
                    batchNo2.setText(resultSet.getString("BatchNo"));
                    mrp2.setText(resultSet.getString("MRP"));
                    rate2.setText(resultSet.getString("Rate"));
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

        qty2=new TextField();
        qty2.setMaxWidth(70);
        qty2.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty2.getText());
                int marketRate = Integer.parseInt(mrp2.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount2.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty2=new TextField();
        totalQty2.setMaxWidth(70);
        totalQty2.setStyle("-fx-background-color:gray");
        totalQty2.setEditable(false);

        HBox hBox7=new HBox(10);
        hBox7.getChildren().addAll(qty2,totalQty2);
        GridPane.setConstraints(hBox7,2,3);

        expDate2=new TextField();
        expDate2.setMaxWidth(100);
        GridPane.setConstraints(expDate2,3,3);

        batchNo2=new TextField();
        batchNo2.setMaxWidth(80);
        mrp2=new TextField();
        mrp2.setMaxWidth(80);
        rate2=new TextField();
        rate2.setMaxWidth(80);

        HBox hBox8=new HBox(10);
        hBox8.getChildren().addAll(batchNo2,mrp2,rate2);
        GridPane.setConstraints(hBox8,4,3);

        amount2=new TextField();
        amount2.setMaxWidth(90);
        amount2.setText("0");

        remove2=new Button("Remove");
        remove2.setOnAction(e->{
            drugName2.setValue("");
            qty2.clear();
            totalQty2.clear();
            expDate2.clear();
            batchNo2.clear();
            mrp2.clear();
            rate2.clear();
            amount2.clear();
        });

        HBox hBox9=new HBox(10);
        hBox9.getChildren().addAll(amount2,remove2);
        GridPane.setConstraints(hBox9,5,3);

        //3nd row
        siNo3=new Label("SI No 3");
        siNo3.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo3,0,4);


        drugName3=new ComboBox<>();
        drugName3.setMinWidth(230);
        drugName3.setEditable(true);
        GridPane.setConstraints(drugName3,1,4);
        drugName3.setOnAction(e->{
            try {
                String medicine=drugName1.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty3.setText(resultSet.getString("Qty"));
                    expDate3.setText(resultSet.getString("ExpDate"));
                    batchNo3.setText(resultSet.getString("BatchNo"));
                    mrp3.setText(resultSet.getString("MRP"));
                    rate3.setText(resultSet.getString("Rate"));
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

        qty3=new TextField();
        qty3.setMaxWidth(70);
        qty3.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty3.getText());
                int marketRate = Integer.parseInt(mrp3.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount3.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty3=new TextField();
        totalQty3.setMaxWidth(70);
        totalQty3.setStyle("-fx-background-color:gray");
        totalQty3.setEditable(false);

        HBox hBox10=new HBox(10);
        hBox10.getChildren().addAll(qty3,totalQty3);
        GridPane.setConstraints(hBox10,2,4);

        expDate3=new TextField();
        expDate3.setMaxWidth(100);
        GridPane.setConstraints(expDate3,3,4);

        batchNo3=new TextField();
        batchNo3.setMaxWidth(80);
        mrp3=new TextField();
        mrp3.setMaxWidth(80);
        rate3=new TextField();
        rate3.setMaxWidth(80);

        HBox hBox11=new HBox(10);
        hBox11.getChildren().addAll(batchNo3,mrp3,rate3);
        GridPane.setConstraints(hBox11,4,4);

        amount3=new TextField();
        amount3.setMaxWidth(90);
        amount3.setText("0");

        remove3=new Button("Remove");
        remove3.setOnAction(e->{
            drugName3.setValue("");
            qty3.clear();
            totalQty3.clear();
            expDate3.clear();
            batchNo3.clear();
            mrp3.clear();
            rate3.clear();
            amount3.clear();
        });

        HBox hBox12=new HBox(10);
        hBox12.getChildren().addAll(amount3,remove3);
        GridPane.setConstraints(hBox12,5,4);

        //4nd row
        siNo4=new Label("SI No 4");
        siNo4.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo4,0,5);


        drugName4=new ComboBox<>();
        drugName4.setMinWidth(230);
        drugName4.setEditable(true);
        GridPane.setConstraints(drugName4,1,5);
        drugName4.setOnAction(e->{
            try {
                String medicine=drugName4.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty4.setText(resultSet.getString("Qty"));
                    expDate4.setText(resultSet.getString("ExpDate"));
                    batchNo4.setText(resultSet.getString("BatchNo"));
                    mrp4.setText(resultSet.getString("MRP"));
                    rate4.setText(resultSet.getString("Rate"));
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

        qty4=new TextField();
        qty4.setMaxWidth(70);
        qty4.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty4.getText());
                int marketRate = Integer.parseInt(mrp4.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount4.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty4=new TextField();
        totalQty4.setMaxWidth(70);
        totalQty4.setStyle("-fx-background-color:gray");
        totalQty4.setEditable(false);

        HBox hBox13=new HBox(10);
        hBox13.getChildren().addAll(qty4,totalQty4);
        GridPane.setConstraints(hBox13,2,5);

        expDate4=new TextField();
        expDate4.setMaxWidth(100);
        GridPane.setConstraints(expDate4,3,5);

        batchNo4=new TextField();
        batchNo4.setMaxWidth(80);
        mrp4=new TextField();
        mrp4.setMaxWidth(80);
        rate4=new TextField();
        rate4.setMaxWidth(80);

        HBox hBox14=new HBox(10);
        hBox14.getChildren().addAll(batchNo4,mrp4,rate4);
        GridPane.setConstraints(hBox14,4,5);

        amount4=new TextField();
        amount4.setMaxWidth(90);
        amount4.setText("0");

        remove4=new Button("Remove");
        remove4.setOnAction(e->{
            drugName4.setValue("");
            qty4.clear();
            totalQty4.clear();
            expDate4.clear();
            batchNo4.clear();
            mrp4.clear();
            rate4.clear();
            amount4.clear();
        });

        HBox hBox15=new HBox(10);
        hBox15.getChildren().addAll(amount4,remove4);
        GridPane.setConstraints(hBox15,5,5);

        //5nd row
        siNo5=new Label("SI No 5");
        siNo5.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo5,0,6);


        drugName5=new ComboBox<>();
        drugName5.setMinWidth(230);
        drugName5.setEditable(true);
        GridPane.setConstraints(drugName5,1,6);
        drugName5.setOnAction(e->{
            try {
                String medicine=drugName5.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty5.setText(resultSet.getString("Qty"));
                    expDate5.setText(resultSet.getString("ExpDate"));
                    batchNo5.setText(resultSet.getString("BatchNo"));
                    mrp5.setText(resultSet.getString("MRP"));
                    rate5.setText(resultSet.getString("Rate"));
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
        qty5=new TextField();
        qty5.setMaxWidth(70);
        qty5.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty5.getText());
                int marketRate = Integer.parseInt(mrp5.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount5.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty5=new TextField();
        totalQty5.setMaxWidth(70);
        totalQty5.setStyle("-fx-background-color:gray");
        totalQty5.setEditable(false);

        HBox hBox16=new HBox(10);
        hBox16.getChildren().addAll(qty5,totalQty5);
        GridPane.setConstraints(hBox16,2,6);

        expDate5=new TextField();
        expDate5.setMaxWidth(100);
        GridPane.setConstraints(expDate5,3,6);

        batchNo5=new TextField();
        batchNo5.setMaxWidth(80);
        mrp5=new TextField();
        mrp5.setMaxWidth(80);
        rate5=new TextField();
        rate5.setMaxWidth(80);

        HBox hBox17=new HBox(10);
        hBox17.getChildren().addAll(batchNo5,mrp5,rate5);
        GridPane.setConstraints(hBox17,4,6);

        amount5=new TextField();
        amount5.setMaxWidth(90);
        amount5.setText("0");

        remove5=new Button("Remove");
        remove5.setOnAction(e->{
            drugName5.setValue("");
            qty5.clear();
            totalQty5.clear();
            expDate5.clear();
            batchNo5.clear();
            mrp5.clear();
            rate5.clear();
            amount5.clear();
        });

        HBox hBox18=new HBox(10);
        hBox18.getChildren().addAll(amount5,remove5);
        GridPane.setConstraints(hBox18,5,6);

        //6nd row
        siNo6=new Label("SI No 6");
        siNo6.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo6,0,7);


        drugName6=new ComboBox<>();
        drugName6.setMinWidth(230);
        drugName6.setEditable(true);
        GridPane.setConstraints(drugName6,1,7);
        drugName6.setOnAction(e->{
            try {
                String medicine=drugName6.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty6.setText(resultSet.getString("Qty"));
                    expDate6.setText(resultSet.getString("ExpDate"));
                    batchNo6.setText(resultSet.getString("BatchNo"));
                    mrp6.setText(resultSet.getString("MRP"));
                    rate6.setText(resultSet.getString("Rate"));
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

        qty6=new TextField();
        qty6.setMaxWidth(70);
        qty6.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty6.getText());
                int marketRate = Integer.parseInt(mrp6.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount6.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty6=new TextField();
        totalQty6.setMaxWidth(70);
        totalQty6.setStyle("-fx-background-color:gray");
        totalQty6.setEditable(false);

        HBox hBox19=new HBox(10);
        hBox19.getChildren().addAll(qty6,totalQty6);
        GridPane.setConstraints(hBox19,2,7);

        expDate6=new TextField();
        expDate6.setMaxWidth(100);
        GridPane.setConstraints(expDate6,3,7);

        batchNo6=new TextField();
        batchNo6.setMaxWidth(80);
        mrp6=new TextField();
        mrp6.setMaxWidth(80);
        rate6=new TextField();
        rate6.setMaxWidth(80);

        HBox hBox20=new HBox(10);
        hBox20.getChildren().addAll(batchNo6,mrp6,rate6);
        GridPane.setConstraints(hBox20,4,7);

        amount6=new TextField();
        amount6.setMaxWidth(90);
        amount6.setText("0");

        remove6=new Button("Remove");
        remove6.setOnAction(e->{
            drugName6.setValue("");
            qty6.clear();
            totalQty6.clear();
            expDate6.clear();
            batchNo6.clear();
            mrp6.clear();
            rate6.clear();
            amount6.clear();
        });

        HBox hBox21=new HBox(10);
        hBox21.getChildren().addAll(amount6,remove6);
        GridPane.setConstraints(hBox21,5,7);


        //7nd row
        siNo7=new Label("SI No 7");
        siNo7.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo7,0,8);


        drugName7=new ComboBox<>();
        drugName7.setMinWidth(230);
        drugName7.setEditable(true);
        GridPane.setConstraints(drugName7,1,8);
        drugName7.setOnAction(e->{
            try {
                String medicine=drugName7.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty7.setText(resultSet.getString("Qty"));
                    expDate7.setText(resultSet.getString("ExpDate"));
                    batchNo7.setText(resultSet.getString("BatchNo"));
                    mrp7.setText(resultSet.getString("MRP"));
                    rate7.setText(resultSet.getString("Rate"));
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

        qty7=new TextField();
        qty7.setMaxWidth(70);
        qty7.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty7.getText());
                int marketRate = Integer.parseInt(mrp7.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount7.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty7=new TextField();
        totalQty7.setMaxWidth(70);
        totalQty7.setStyle("-fx-background-color:gray");
        totalQty7.setEditable(false);

        HBox hBox22=new HBox(10);
        hBox22.getChildren().addAll(qty7,totalQty7);
        GridPane.setConstraints(hBox22,2,8);

        expDate7=new TextField();
        expDate7.setMaxWidth(100);
        GridPane.setConstraints(expDate7,3,8);

        batchNo7=new TextField();
        batchNo7.setMaxWidth(80);
        mrp7=new TextField();
        mrp7.setMaxWidth(80);
        rate7=new TextField();
        rate7.setMaxWidth(80);

        HBox hBox23=new HBox(10);
        hBox23.getChildren().addAll(batchNo7,mrp7,rate7);
        GridPane.setConstraints(hBox23,4,8);

        amount7=new TextField();
        amount7.setMaxWidth(90);
        amount7.setText("0");

        remove7=new Button("Remove");
        remove7.setOnAction(e->{
            drugName7.setValue("");
            qty7.clear();
            totalQty7.clear();
            expDate7.clear();
            batchNo7.clear();
            mrp7.clear();
            rate7.clear();
            amount7.clear();
        });

        HBox hBox24=new HBox(10);
        hBox24.getChildren().addAll(amount7,remove7);
        GridPane.setConstraints(hBox24,5,8);


        //8nd row
        siNo8=new Label("SI No 8");
        siNo8.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo8,0,9);


        drugName8=new ComboBox<>();
        drugName8.setMinWidth(230);
        drugName8.setEditable(true);
        GridPane.setConstraints(drugName8,1,9);
        drugName8.setOnAction(e->{
            try {
                String medicine=drugName8.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty8.setText(resultSet.getString("Qty"));
                    expDate8.setText(resultSet.getString("ExpDate"));
                    batchNo8.setText(resultSet.getString("BatchNo"));
                    mrp8.setText(resultSet.getString("MRP"));
                    rate8.setText(resultSet.getString("Rate"));
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

        qty8=new TextField();
        qty8.setMaxWidth(70);
        qty8.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty8.getText());
                int marketRate = Integer.parseInt(mrp8.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount8.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty8=new TextField();
        totalQty8.setMaxWidth(70);
        totalQty8.setStyle("-fx-background-color:gray");
        totalQty8.setEditable(false);

        HBox hBox25=new HBox(10);
        hBox25.getChildren().addAll(qty8,totalQty8);
        GridPane.setConstraints(hBox25,2,9);

        expDate8=new TextField();
        expDate8.setMaxWidth(100);
        GridPane.setConstraints(expDate8,3,9);

        batchNo8=new TextField();
        batchNo8.setMaxWidth(80);
        mrp8=new TextField();
        mrp8.setMaxWidth(80);
        rate8=new TextField();
        rate8.setMaxWidth(80);

        HBox hBox26=new HBox(10);
        hBox26.getChildren().addAll(batchNo8,mrp8,rate8);
        GridPane.setConstraints(hBox26,4,9);

        amount8=new TextField();
        amount8.setMaxWidth(90);
        amount8.setText("0");

        remove8=new Button("Remove");
        remove8.setOnAction(e->{
            drugName8.setValue("");
            qty8.clear();
            totalQty8.clear();
            expDate8.clear();
            batchNo8.clear();
            mrp8.clear();
            rate8.clear();
            amount8.clear();
        });

        HBox hBox27=new HBox(10);
        hBox27.getChildren().addAll(amount8,remove8);
        GridPane.setConstraints(hBox27,5,9);


        //9nd row
        siNo9=new Label("SI No 9");
        siNo9.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo9,0,10);


        drugName9=new ComboBox<>();
        drugName9.setMinWidth(230);
        drugName9.setEditable(true);
        GridPane.setConstraints(drugName9,1,10);
        drugName9.setOnAction(e->{
            try {
                String medicine=drugName9.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty9.setText(resultSet.getString("Qty"));
                    expDate9.setText(resultSet.getString("ExpDate"));
                    batchNo9.setText(resultSet.getString("BatchNo"));
                    mrp9.setText(resultSet.getString("MRP"));
                    rate9.setText(resultSet.getString("Rate"));
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

        qty9=new TextField();
        qty9.setMaxWidth(70);
        qty9.setOnKeyPressed((KeyEvent e) ->{
            Total obj=new Total();
            try {
                int quantity = Integer.parseInt(qty9.getText());
                int marketRate = Integer.parseInt(mrp9.getText());
                String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                amount9.setText(totalamount);
            }catch (Exception es){

            }
        });

        totalQty9=new TextField();
        totalQty9.setMaxWidth(70);
        totalQty9.setStyle("-fx-background-color:gray");
        totalQty9.setEditable(false);

        HBox hBox28=new HBox(10);
        hBox28.getChildren().addAll(qty9,totalQty9);
        GridPane.setConstraints(hBox28,2,10);

        expDate9=new TextField();
        expDate9.setMaxWidth(100);
        GridPane.setConstraints(expDate9,3,10);

        batchNo9=new TextField();
        batchNo9.setMaxWidth(80);
        mrp9=new TextField();
        mrp9.setMaxWidth(80);
        rate9=new TextField();
        rate9.setMaxWidth(80);

        HBox hBox29=new HBox(10);
        hBox29.getChildren().addAll(batchNo9,mrp9,rate9);
        GridPane.setConstraints(hBox29,4,10);

        amount9=new TextField();
        amount9.setMaxWidth(90);
        amount9.setText("0");

        remove9=new Button("Remove");
        remove9.setOnAction(e->{
            drugName9.setValue("");
            qty9.clear();
            totalQty9.clear();
            expDate9.clear();
            batchNo9.clear();
            mrp9.clear();
            rate9.clear();
            amount9.clear();
        });

        HBox hBox30=new HBox(10);
        hBox30.getChildren().addAll(amount9,remove9);
        GridPane.setConstraints(hBox30,5,10);

        //10nd row
        siNo10=new Label("SI No 10");
        siNo10.setStyle("-fx-font-size:120%;-fx-font-weight:bold");
        GridPane.setConstraints(siNo10,0,11);


        drugName10=new ComboBox<>();
        drugName10.setMinWidth(230);
        GridPane.setConstraints(drugName10,1,11);
        drugName10.setOnAction(e->{
            try {
                String medicine=drugName10.getValue();
                String sql="select * from AddDrugs where Particulars='"+medicine+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();

                while (resultSet.next()) {
                    totalQty10.setText(resultSet.getString("Qty"));
                    expDate10.setText(resultSet.getString("ExpDate"));
                    batchNo10.setText(resultSet.getString("BatchNo"));
                    mrp10.setText(resultSet.getString("MRP"));
                    rate10.setText(resultSet.getString("Rate"));
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

        qty10=new TextField();
        qty10.setMaxWidth(70);
        qty10.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Total obj=new Total();
                try {
                    int quantity = Integer.parseInt(qty10.getText());
                    int marketRate = Integer.parseInt(mrp10.getText());
                    String totalamount = String.valueOf(obj.amounts(quantity, marketRate));
                    amount10.setText(totalamount);
                }catch (Exception es){

                }

            }
        }
        );





        drugName10.setEditable(true);
        totalQty10=new TextField();
        totalQty10.setMaxWidth(70);
        totalQty10.setStyle("-fx-background-color:gray");
        totalQty10.setEditable(false);

        HBox hBox31=new HBox(10);
        hBox31.getChildren().addAll(qty10,totalQty10);
        GridPane.setConstraints(hBox31,2,11);

        expDate10=new TextField();
        expDate10.setMaxWidth(100);
        GridPane.setConstraints(expDate10,3,11);

        batchNo10=new TextField();
        batchNo10.setMaxWidth(80);
        mrp10=new TextField();
        mrp10.setMaxWidth(80);
        rate10=new TextField();
        rate10.setMaxWidth(80);

        HBox hBox32=new HBox(10);
        hBox32.getChildren().addAll(batchNo10,mrp10,rate10);
        GridPane.setConstraints(hBox32,4,11);

        amount10=new TextField();
        amount10.setMaxWidth(90);
        amount10.setText("0");

        remove10=new Button("Remove");
        remove10.setOnAction(e->{
            drugName10.setValue("");
            qty10.clear();
            totalQty10.clear();
            expDate10.clear();
            batchNo10.clear();
            mrp10.clear();
            rate10.clear();
            amount10.clear();
        });

        HBox hBox33=new HBox(10);
        hBox33.getChildren().addAll(amount10,remove10);
        GridPane.setConstraints(hBox33,5,11);

        //Store the medicine name in array and ten add in comboBox

        drugName= FXCollections.observableArrayList();
        try {
            String sql="select Particulars from AddDrugs";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                drugName.add(
                        resultSet.getString("Particulars")

                );

                drugName1.getItems().addAll(drugName);
                drugName2.getItems().addAll(drugName);
                drugName3.getItems().addAll(drugName);
                drugName4.getItems().addAll(drugName);
                drugName5.getItems().addAll(drugName);
                drugName6.getItems().addAll(drugName);
                drugName7.getItems().addAll(drugName);
                drugName8.getItems().addAll(drugName);
                drugName9.getItems().addAll(drugName);
                drugName10.getItems().addAll(drugName);

            }

        }catch (Exception e1){

            JOptionPane.showMessageDialog(null,e1);
        }
        drugName1.getItems().addAll(drugName);
        drugName1.onKeyPressedProperty();
        drugName1.setOnKeyPressed(event -> System.out.print("i am pressed"));
        new ComboBoxAutoComplete<String>(drugName1);
//add the children in gridpane layout

        gridPane.getChildren().addAll(DrugName,expDate,hBox3,amount,siNo1,drugName1,hBox4,expDate1,hBox5,hBox6
                ,siNo2,drugName2,hBox7,expDate2,hBox8,hBox9,siNo3,drugName3,hBox10,expDate3,hBox11,hBox12
                ,siNo4,drugName4,hBox13,expDate4,hBox14,hBox15,siNo5,drugName5,hBox16,expDate5,hBox17,hBox18
                ,siNo6,drugName6,hBox19,expDate6,hBox20,hBox21,siNo7,drugName7,hBox22,expDate7,hBox23,hBox24
                ,siNo8,drugName8,hBox25,expDate8,hBox26,hBox27,siNo9,drugName9,hBox28,expDate9,hBox29,hBox30
                ,siNo10,drugName10,hBox31,expDate10,hBox32,hBox33);

        addMore=new Button("Add More Items");

        calculateAmountR=new Button("Calculate Amount for Retail");

        calculateAmountW=new Button("Calculate Amount for Wholesale");

        VBox vBox1=new VBox(10);
        vBox1.getChildren().addAll(addMore,calculateAmountR,calculateAmountW);
        vBox1.setAlignment(Pos.BASELINE_LEFT);

        goToBill=new Button("Goto Billing");
//        goToBill.setOnAction(e->{
//
//            try {
//
//                //store 1st row data into database
//
//                String sql1="Insert into SaleReport (InvoiceNo,Amount,InvoiceDate)values(?,?,?)";
//                preparedStatement=connection.prepareStatement(sql1);
//                preparedStatement.setString(1, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.setString(2, String.valueOf(total.getText()));
//                preparedStatement.setString(3,String.valueOf(dateTextField.getText()));
//                preparedStatement.execute();
//
//
//                String sql="Insert into Bills (SN,DrugName,Qty,ExpDate,BatchNo,MRP,Rate,Amount,TotalAmount,BillDate,BillNo)Values(?,?,?,?,?,?,?,?,?,?,?)";
//
//                Integer tot=Integer.parseInt(totalQty1.getText());
//
//                if(tot>0) {
//
////Update total amount of medicine
//                Integer remain1=(Integer.parseInt(totalQty1.getText()))-(Integer.parseInt(qty1.getText()));
//                String sql2="update AddDrugs set Qty='"+remain1+"' where Particulars='"+drugName1.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql2);
//                preparedStatement.execute();
//                }else {
//                    JOptionPane.showMessageDialog(null,"Product is end ");
//                }
//
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si1=1;
//                preparedStatement.setString(1, String.valueOf(si1));
//                preparedStatement.setString(2,String.valueOf(drugName1.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty1.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate1.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo1.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp1.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate1.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount1.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer tot1=Integer.parseInt(totalQty2.getText());
//
//                if(tot1>0) {
//                    Integer remain2 = (Integer.parseInt(totalQty2.getText())) - (Integer.parseInt(qty2.getText()));
//                    String sql3 = "update AddDrugs set Qty='" + remain2 + "' where Particulars='" + drugName2.getValue() + "'";
//                    preparedStatement = connection.prepareStatement(sql3);
//                    preparedStatement.execute();
//                }else {
//                    JOptionPane.showMessageDialog(null,"Product is end ");
//                }
//
//                //store 2nd row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si2=2;
//                preparedStatement.setString(1, String.valueOf(si2));
//                preparedStatement.setString(2,String.valueOf(drugName2.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty2.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate2.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo2.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp2.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate2.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount2.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain3=(Integer.parseInt(totalQty3.getText()))-(Integer.parseInt(qty3.getText()));
//                String sql4="update AddDrugs set Qty='"+remain3+"' where Particulars='"+drugName3.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql4);
//                preparedStatement.execute();
//
//
//                //store 3rd row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si3=3;
//                preparedStatement.setString(1, String.valueOf(si3));
//                preparedStatement.setString(2,String.valueOf(drugName3.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty3.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate3.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo3.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp3.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate3.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount3.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain4=(Integer.parseInt(totalQty4.getText()))-(Integer.parseInt(qty4.getText()));
//                String sql5="update AddDrugs set Qty='"+remain4+"' where Particulars='"+drugName4.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql5);
//                preparedStatement.execute();
//
//
//                //store 4th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si4=4;
//                preparedStatement.setString(1, String.valueOf(si4));
//                preparedStatement.setString(2,String.valueOf(drugName4.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty4.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate4.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo4.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp4.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate4.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount4.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain5=(Integer.parseInt(totalQty5.getText()))-(Integer.parseInt(qty5.getText()));
//                String sql6="update AddDrugs set Qty='"+remain5+"' where Particulars='"+drugName5.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql6);
//                preparedStatement.execute();
//
//
//                //store 5th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si5=5;
//                preparedStatement.setString(1, String.valueOf(si5));
//                preparedStatement.setString(2,String.valueOf(drugName5.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty5.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate5.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo5.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp5.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate5.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount5.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain6=(Integer.parseInt(totalQty6.getText()))-(Integer.parseInt(qty6.getText()));
//                String sql7="update AddDrugs set Qty='"+remain6+"' where Particulars='"+drugName6.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql7);
//                preparedStatement.execute();
//
//
//                //store 6th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si6=6;
//                preparedStatement.setString(1, String.valueOf(si6));
//                preparedStatement.setString(2,String.valueOf(drugName6.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty6.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate6.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo6.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp6.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate6.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount6.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain7=(Integer.parseInt(totalQty7.getText()))-(Integer.parseInt(qty7.getText()));
//                String sql8="update AddDrugs set Qty='"+remain7+"' where Particulars='"+drugName7.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql8);
//                preparedStatement.execute();
//
//
//                //store 7th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si7=7;
//                preparedStatement.setString(1, String.valueOf(si7));
//                preparedStatement.setString(2,String.valueOf(drugName7.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty7.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate7.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo7.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp7.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate7.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount7.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain8=(Integer.parseInt(totalQty8.getText()))-(Integer.parseInt(qty8.getText()));
//                String sql9="update AddDrugs set Qty='"+remain8+"' where Particulars='"+drugName8.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql9);
//                preparedStatement.execute();
//
//
//                //store 8th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si8=8;
//                preparedStatement.setString(1, String.valueOf(si8));
//                preparedStatement.setString(2,String.valueOf(drugName8.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty8.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate8.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo8.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp8.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate8.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount8.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain9=(Integer.parseInt(totalQty9.getText()))-(Integer.parseInt(qty9.getText()));
//                String sql10="update AddDrugs set Qty='"+remain9+"' where Particulars='"+drugName9.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql10);
//                preparedStatement.execute();
//
//
//                //store 9th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si9=9;
//                preparedStatement.setString(1, String.valueOf(si9));
//                preparedStatement.setString(2,String.valueOf(drugName9.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty9.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate9.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo9.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp9.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate9.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount9.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//                //Update total amount of medicine
//
//                Integer remain10=(Integer.parseInt(totalQty10.getText()))-(Integer.parseInt(qty10.getText()));
//                String sql11="update AddDrugs set Qty='"+remain10+"' where Particulars='"+drugName10.getValue()+"'";
//                preparedStatement=connection.prepareStatement(sql11);
//                preparedStatement.execute();
//
//
//                //store 10th row data into database
//
//                preparedStatement=connection.prepareStatement(sql);
//                Integer si10=10;
//                preparedStatement.setString(1, String.valueOf(si10));
//                preparedStatement.setString(2,String.valueOf(drugName10.getValue()));
//                preparedStatement.setString(3, String.valueOf(Integer.parseInt(qty10.getText())));
//                preparedStatement.setString(4,String.valueOf(expDate10.getText()));
//                preparedStatement.setString(5, String.valueOf(Integer.parseInt(batchNo10.getText())));
//                preparedStatement.setString(6, String.valueOf(Integer.parseInt(mrp10.getText())));
//                preparedStatement.setString(7, String.valueOf(Integer.parseInt(rate10.getText())));
//                preparedStatement.setString(8, String.valueOf(Integer.parseInt(amount10.getText())));
//                preparedStatement.setString(9, String.valueOf(total.getText()));
//                preparedStatement.setString(10,String.valueOf(dateTextField.getText()));
//                preparedStatement.setString(11, String.valueOf(Integer.parseInt(billNoTextField.getText())));
//                preparedStatement.execute();
//
//
//            }catch (Exception e3){
//
//            }finally {
//                try {
//
//                    preparedStatement.close();
//                }catch (Exception e4){
//
//                }
//            }
//
//            //To go on bill generate window and generate bill
//
//            String no=billNoTextField.getText();
//            Bill obj=new Bill();
//            obj.bills(no);
//            window1.close();
//
//
//        });
//
//        calculateTotal=new Button("Calculate Total");
//        calculateTotal.setOnAction(event -> {
//            int amount1Value=Integer.parseInt(amount1.getText());
//            int amount2Value=Integer.parseInt(amount2.getText());
//            int amount3Value=Integer.parseInt(amount3.getText());
//            int amount4Value=Integer.parseInt(amount4.getText());
//            int amount5Value=Integer.parseInt(amount5.getText());
//            int amount6Value=Integer.parseInt(amount6.getText());
//            int amount7Value=Integer.parseInt(amount7.getText());
//            int amount8Value=Integer.parseInt(amount8.getText());
//            int amount9Value=Integer.parseInt(amount9.getText());
//            int amount10Value=Integer.parseInt(amount10.getText());
//            int totalAmount=object.total(amount1Value,amount2Value,amount3Value,amount4Value,
//                    amount5Value,amount6Value,amount7Value,amount8Value,amount9Value,amount10Value);
//            total.setText("Rs."+String.valueOf(totalAmount));
//
//        });

        total=new TextField();
        total.setEditable(false);
        total.setMaxWidth(90);

        BorderPane borderPane=new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setCenter(gridPane);
        borderPane.setStyle("-fx-background-image:url(khubaib.jpg)");

        scene1=new Scene(borderPane,1300,680);
        window1.setScene(scene1);
        window1.show();

    }
}
