package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.omg.CORBA.BAD_CONTEXT;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Khubaib ch on 7/21/2017.
 */
public class ViewStock {

    Stage window9;
    Label title, fewAlphabets, or;
    TextField name;
    Button searchName, viewAll, back, clear;
    TableView<ViewStockTable> tableView;
    ObservableList<ViewStockTable> data;
    Scene scene9;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void viewStock() {

        window9 = new Stage();
        window9.setTitle("View Stock");

        connection = DataBaseConnection.ConnectDB();

        title = new Label("View Stock");
        title.setStyle("-fx-font-size:250%;-fx-font-weight:bold");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(title);
        stackPane.setAlignment(Pos.BASELINE_CENTER);

        fewAlphabets = new Label("Type The First Few Alphabets ");
        fewAlphabets.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        name = new TextField();
        name.setMaxWidth(100);

        searchName = new Button("Search Name");
        searchName.setMinWidth(100);
        searchName.setOnAction(e -> {
            try {
                String medicineName = name.getText();
                String sql = "select * from AddDrugs where Particulars like '" + medicineName + "%' order by Particulars";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    data.add(new ViewStockTable(
                            resultSet.getString("Particulars"),
                            resultSet.getString("Qty"),
                            resultSet.getString("BatchNo"),
                            resultSet.getString("MRP"),
                            resultSet.getString("Rate"),
                            resultSet.getString("ExpDate")


                    ));
                    tableView.setItems(data);
                }

            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, e1);
            } finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (Exception e2) {

                }
            }
        });


        or = new Label("OR");
        or.setStyle("-fx-font-size:120%;-fx-font-weight:bold");

        viewAll = new Button("View All");
        viewAll.setMinWidth(100);
        viewAll.setOnAction(e -> {
            try {
                String sql = "select * from AddDrugs order by Particulars";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    data.add(new ViewStockTable(
                            resultSet.getString("Particulars"),
                            resultSet.getString("Qty"),
                            resultSet.getString("BatchNo"),
                            resultSet.getString("MRP"),
                            resultSet.getString("Rate"),
                            resultSet.getString("ExpDate")


                    ));
                    tableView.setItems(data);
                }

            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, e1);
            } finally {
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (Exception e2) {

                }
            }
        });

        HBox hBox = new HBox(40);
        hBox.getChildren().addAll(fewAlphabets, name, searchName, or, viewAll);

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(10, 10, 30, 30));
        vBox.getChildren().addAll(stackPane, hBox);

        BorderPane layout = new BorderPane();
        layout.setTop(vBox);
        layout.setStyle("-fx-background-color:gray");

        TableColumn Particulars = new TableColumn("Particulars");
        Particulars.setMinWidth(230);
        Particulars.setCellValueFactory(new PropertyValueFactory<>("Particulars"));

        TableColumn qtyAvailable = new TableColumn("Qty Available");
        qtyAvailable.setMinWidth(120);
        qtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));
        qtyAvailable.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn BatchNo = new TableColumn("Batch No");
        BatchNo.setMinWidth(140);
        BatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNo"));

        TableColumn Mrp = new TableColumn("MRP");
        Mrp.setMinWidth(120);
        Mrp.setCellValueFactory(new PropertyValueFactory<>("mrp"));

        TableColumn Rate = new TableColumn<>("Rate");
        Rate.setMinWidth(120);
        Rate.setCellValueFactory(new PropertyValueFactory<>("rate"));

        TableColumn expDate = new TableColumn("Expiry Date");
        expDate.setMinWidth(160);
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setPadding(new Insets(0, 10, 10, 10));
        data = FXCollections.observableArrayList();
        tableView.getColumns().addAll(Particulars, qtyAvailable, BatchNo, Mrp, Rate, expDate);
        qtyAvailable.setEditable(true);
        qtyAvailable.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ViewStockTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ViewStockTable, String> t) {
                        ((ViewStockTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQty(t.getNewValue());
                    }
                }
        );
        layout.setCenter(tableView);

        back = new Button("Back");
        back.setMinWidth(100);
        back.setOnAction(e -> {
            HomePage object2 = new HomePage();
            object2.homePage();
            window9.close();

        });

        clear = new Button("Clear");
        clear.setMinWidth(100);
        clear.setOnAction(e -> {
            data.clear();
        });

        HBox hBox1 = new HBox(300);
        hBox1.setPadding(new
                Insets(20, 10, 30, 20));
        hBox1.getChildren().addAll(back, clear);

        layout.setBottom(hBox1);

        scene9 = new Scene(layout, 900, 620);
        window9.setScene(scene9);
        window9.show();


    }
}
