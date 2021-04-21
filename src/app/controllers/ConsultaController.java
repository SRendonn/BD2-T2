package app.controllers;

import app.Main;
import app.models.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import multichain.command.CommandElt;
import multichain.command.MultichainException;
import multichain.object.StreamKey;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultaController implements Initializable {

    @FXML
    private void goToHome() throws IOException {
        Main.push("views/index.fxml");
    }

    @FXML private TableView<Person> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                    ganancia = ganancias.get(i).toString();
                    String[] parts = ganancia.split(",");
                    String key = parts[2].substring(8,parts[2].length()-1);
                    if (key.equals(vendedor.getKey())){
                        aux=parts[3].substring(20,(parts[3].length()-2));
                        total+=Double.parseDouble(aux);
                    }
                }
                data.add(new Person(vendedor.getKey(),Double.toString(total)));
            }
            tableView.setItems(data);
        } catch (MultichainException e) {
            e.printStackTrace();
        }
    }
}