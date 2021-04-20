package app.controllers;

import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import multichain.command.CommandElt;
import multichain.command.MultichainException;
import multichain.object.StreamKey;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GananciaController implements Initializable {

    @FXML
    ChoiceBox<String> vendedorInput;

    @FXML
    private ObservableList<String> listaVendedores;

    @FXML
    TextField valorInput;

    @FXML
    private void goToHome() throws IOException {
        Main.push("views/index.fxml");
    }

    @FXML
    private void createGanancia() {

        String vendedor = vendedorInput.getValue();
        Double valor = Double.parseDouble(valorInput.getText().trim());

        JSONObject json = new JSONObject();
        json.put("valor", valor);
        JSONObject data = new JSONObject();
        data.put("json", json);

        if (!vendedor.isEmpty() && !(valor.isNaN() || valor.isInfinite())) {
            try {
                Main.commandManager.invoke(CommandElt.PUBLISH, "ganancias", vendedor, data);
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ganancia registrada.");
                alert.show();
            } catch (MultichainException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo registrar la ganancia.");
                alert.show();
            } finally {
                vendedorInput.getSelectionModel().clearSelection();
                valorInput.clear();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<StreamKey> vendedores;
        try {
            vendedores = (ArrayList<StreamKey>) Main.commandManager.invoke(CommandElt.LISTSTREAMKEYS, "vendedores");
            listaVendedores = FXCollections.observableArrayList();
            vendedores.forEach(streamKey -> {
                listaVendedores.add(streamKey.getKey());
            });
            vendedorInput.setItems(listaVendedores);
        } catch (MultichainException e) {
            e.printStackTrace();
        }
    }
}
