package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
 * Created by Khubaib CH on 7/26/2017.
 */
public class CreateNewUser {
    Stage window18;
    Label titleLabel,usernameLabel,passwordLabel;
    TextField usernameField;
    PasswordField passwordField;
    Button create,back;
    Scene scene18;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection;

    public void createNewUser(){
        window18=new Stage();
        window18.setTitle("Create New User");

        connection=DataBaseConnection.ConnectDB();

        titleLabel=new Label("Create New User");
        titleLabel.setStyle("-fx-font-size:250%;-fx-font-weight:bold");
        StackPane stackPane1=new StackPane();
        stackPane1.getChildren().addAll(titleLabel);
        stackPane1.setPadding(new Insets(30,10,10,10));

        BorderPane layout=new BorderPane();
        layout.setTop(stackPane1);
        layout.setStyle("-fx-background-color:gray");

        usernameLabel=new Label("Username");
        usernameLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        usernameField=new TextField();
        usernameField.setMaxWidth(140);

        HBox hBox=new HBox(30);
        hBox.getChildren().addAll(usernameLabel,usernameField);

        passwordLabel=new Label("Password");
        passwordLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        passwordField=new PasswordField();
        passwordField.setMaxWidth(140);

        HBox hBox1=new HBox(30);
        hBox1.getChildren().addAll(passwordLabel,passwordField);

        create=new Button("Create");
        create.setMinWidth(90);
        create.setOnAction(e->{
            try {
                String sql="insert into CreateUser (Username,Password)values(?,?)";
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,String.valueOf(usernameField.getText()));
                preparedStatement.setString(2,String.valueOf(passwordField.getText()));
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null,"User Create Successfully");
            }catch (Exception e1){
                JOptionPane.showMessageDialog(null,"Please Enter Username and Password");
            }finally {
                try {
                    preparedStatement.close();
                }catch (Exception e2){

                }
            }
        });

        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(create);

        VBox vBox=new VBox(40);
        vBox.getChildren().addAll(hBox,hBox1,stackPane);
        vBox.setPadding(new Insets(60,50,10,100));
        layout.setCenter(vBox);

        back=new Button("Back");
        back.setMinWidth(90);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window18.close();

        });

        HBox hBox4=new HBox(20);
        hBox4.getChildren().addAll(back);
        hBox4.setPadding(new Insets(10,10,20,20));

        layout.setBottom(hBox4);

        scene18=new Scene(layout,500,400);
        window18.setScene(scene18);
        window18.show();

    }
}
