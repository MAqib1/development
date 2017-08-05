package com.company;

import javafx.scene.control.*;

import java.awt.*;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javafx.scene.control.*;
/**
 * Created by Khubaib ch on 7/16/2017.
 */
public class Total {

    public int total(int amount1Value,int amount2Value,int amount3Value,int amount4Value,
                     int amount5Value,int amount6Value,int amount7Value,int amount8Value,int amount9Value,int amount10Value){

        int sum=amount1Value+amount2Value+amount3Value+amount4Value+amount5Value+amount6Value+amount7Value+amount8Value+amount9Value+amount10Value;
     return sum;
    }

    public int amounts(int q,int r){
        return q*r;

    }
}
