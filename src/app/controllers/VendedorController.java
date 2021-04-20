package app.controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import multichain.command.CommandElt;
import multichain.command.MultichainException;
import org.json.simple.JSONObject;

import java.io.IOException;

public class VendedorController {

    @FXML
    TextField cedulaInput;

    @FXML
    TextField nombreInput;

    @FXML
    TextField telefonoInput;

    @FXML
    private void goToHome() throws IOException {
        Main.push("views/index.fxml");
    }

    @FXML
    private void createVendedor() {

        String cedula = cedulaInput.getText().trim();
        String nombre = nombreInput.getText().trim();
        String telefono = telefonoInput.getText().trim();

        JSONObject json = new JSONObject();
        json.put("nombre", nombre);
        json.put("telefono", telefono);
        JSONObject data = new JSONObject();
        data.put("json", json);

        if (!cedula.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()) {
            try {
                Main.commandManager.invoke(CommandElt.PUBLISH, "vendedores", cedula, data);
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vendedor registrado.");
                alert.show();
            } catch (MultichainException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo registrar el vendedor.");
                alert.show();
            } finally {
                cedulaInput.clear();
                nombreInput.clear();
                telefonoInput.clear();
            }
        }
    }
}
