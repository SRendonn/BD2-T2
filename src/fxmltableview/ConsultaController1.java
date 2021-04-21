package fxmltableview;

import app.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import multichain.command.CommandElt;
import multichain.command.MultichainException;
import multichain.object.StreamKey;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaController1 implements Initializable {

    @FXML
    private void goToHome() throws IOException {
        Main.push("views/index.fxml");
    }

    @FXML private TableView<Person> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Person> data = null;
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

        tableView.setItems(data);
    }
}