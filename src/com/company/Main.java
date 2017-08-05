package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Login object1=new Login();
//        object1.login();

//        HomePage object2=new HomePage();
//        object2.homePage();

//
//        ChangePassword obj=new ChangePassword();
//        obj.changePassword();

        Home home=new Home();
    }
}
