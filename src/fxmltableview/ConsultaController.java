package fxmltableview;

import app.Main;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import multichain.command.CommandElt;
import multichain.command.MultichainException;
import multichain.object.StreamKey;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ConsultaController extends Application {

    @FXML
    private void goToHome() throws IOException {
        Main.push("views/index.fxml");
    }

    @FXML private TableView<Person> tableView;

    @FXML
    public void addPerson(ActionEvent event) {
        ObservableList<Person> data = tableView.getItems();
        ArrayList<StreamKey> vendedores;
        List ganancias;
        String ganancia;
        Double total;
        String aux;
        try {
            vendedores = (ArrayList<StreamKey>) Main.commandManager.invoke(CommandElt.LISTSTREAMKEYS, "vendedores");
            ganancias = (List) Main.commandManager.invoke(CommandElt.LISTSTREAMITEMS, "ganancias");
            for (StreamKey vendedor: vendedores){
                aux="";
                total=0.0;
                for (int i=0; i<ganancias.size(); i++) {
                    ganancia= ganancias.get(i).toString();
                    String[] parts = ganancia.split(",");
                    String key = parts[2].substring(8,parts[2].length()-1);
                    if (key.equals(vendedor.getKey())){
                        aux=parts[3].substring(20,(parts[3].length()-2));
                        total+=Double.parseDouble(aux);
                    }
                }
                data.add(new Person(vendedor.getKey(),Double.toString(total)));
            }
        } catch (MultichainException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FXML TableView Example");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("Consultar.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}