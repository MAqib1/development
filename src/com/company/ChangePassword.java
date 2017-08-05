package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class ChangePassword {

    Stage window17;
    Label titleLabel,usernameLabel,oldPasswordLabel,newPasswordLabel;
    TextField usernameField;
    PasswordField oldPasswordField,newPasswordField;
    Button chagePasswordButton,back;
    Scene scene17;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection;

    public void changePassword(){

        window17=new Stage();
        window17.setTitle("Change Password");

        connection=DataBaseConnection.ConnectDB();

        titleLabel=new Label("Change Password");
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
        usernameField.setMaxWidth(120);

        HBox hBox=new HBox(110);
        hBox.getChildren().addAll(usernameLabel,usernameField);

        oldPasswordLabel=new Label("Old Password");
        oldPasswordLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        oldPasswordField=new PasswordField();
        oldPasswordField.setMaxWidth(120);

        HBox hBox1=new HBox(85);
        hBox1.getChildren().addAll(oldPasswordLabel,oldPasswordField);

        newPasswordLabel=new Label("New Password");
        newPasswordLabel.setStyle("-fx-font-size:120%;-fx-font-weight:bold");


        newPasswordField=new PasswordField();
        newPasswordField.setMaxWidth(120);

        HBox hBox2=new HBox(80);
        hBox2.getChildren().addAll(newPasswordLabel,newPasswordField);



        chagePasswordButton=new Button("Change Password");
        chagePasswordButton.setOnAction(e->{
            String uname=usernameField.getText();
            String passwor=oldPasswordField.getText();
            String newP=newPasswordField.getText();

                try {
                        String sql = "update CreateUser set Password='" + newP + "' where Username='" + uname + "' and Password='" + passwor + "'";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "Password Change Successfully");

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Please Enter Correct Username and Old Password ");
                }finally {
                    try {
                        resultSet.close();
                        preparedStatement.close();
                    }catch (Exception e2){

                    }
                }
        });

        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(chagePasswordButton);

        VBox vBox=new VBox(30);
        vBox.getChildren().addAll(hBox,hBox1,hBox2,stackPane);
        vBox.setPadding(new Insets(30,50,10,50));
        layout.setCenter(vBox);

        back=new Button("Back");
        back.setMinWidth(90);
        back.setOnAction(e->{
            HomePage object2=new HomePage();
            object2.homePage();
            window17.close();

        });

        HBox hBox4=new HBox(20);
        hBox4.getChildren().addAll(back);
        hBox4.setPadding(new Insets(10,10,20,20));

        layout.setBottom(hBox4);

        scene17=new Scene(layout,500,400);
        window17.setScene(scene17);
        window17.show();


    }
}
