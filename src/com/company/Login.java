package com.company;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/15/2017.
 */
public class Login {

    Stage window;
    Label name,otherdetail,Username,Password;
    TextField username;
    PasswordField password;
    Button login;
    Scene scene;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection;

    public void login(){

        window=new Stage();
        window.setTitle("Doctor's Pharmacy");

        connection=DataBaseConnection.ConnectDB();

        name=new Label("Doctor's Pharmacy");
        name.setStyle("-fx-font-weight:bold; -fx-font-size:200%");

        otherdetail=new Label("Near Doctor's Hospital Modal town C chock Bahawalpur\n\t\t   D.L No 72/DCA/21/DHS-2011\n\t\t\t   Wholesale and  Retail");
        otherdetail.setStyle("-fx-font-size:120%");

        VBox vBox=new VBox(20);
        vBox.getChildren().addAll(name,otherdetail);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,10,30,10));

        GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(20,30,10,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setMaxWidth(400);
        gridPane.setMaxHeight(300);

        Username=new Label("Username");
        GridPane.setConstraints(Username,1,2);
        username =new TextField();
        GridPane.setConstraints(username,2,2);


        Password=new Label("Password");
        GridPane.setConstraints(Password,1,4);
        password=new PasswordField();
        GridPane.setConstraints(password,2,4);

        login=new Button("Login");
        login.setPadding(new Insets(10,0,10,10));
        login.setMinWidth(100);
        login.setOnAction(e->{
            String uname=username.getText();
            String passw=password.getText();

            try {
                String sql="select * from CreateUser where Username='"+uname+"' and Password='"+passw+"'";
                preparedStatement=connection.prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    resultSet.close();
                    preparedStatement.close();
                    HomePage object2=new HomePage();
                    object2.homePage();
                    window.close();
                    JOptionPane.showMessageDialog(null,"Welcome");

                }else {
                    JOptionPane.showMessageDialog(null,"You enter wrong Username and Password");
                }

            }catch (Exception e1){

            }finally {
                try {
                    resultSet.close();
                    preparedStatement.close();


                }catch (Exception e2){

                }
            }
        });

        GridPane.setConstraints(login,2,6);


        gridPane.getChildren().addAll(Username,username,Password,password,login);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setStyle("-fx-background-color:#F3E5F5");

        BorderPane layout=new BorderPane();
        layout.setTop(vBox);
        layout.setCenter(gridPane);

        scene=new Scene(layout,550,500);
        window.setScene(scene);
        window.show();

    }

}
