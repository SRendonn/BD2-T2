package app.controllers;

import app.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class IndexController {

    @FXML
    private void goToConsultar() throws IOException {
        Main.push("views/Consultar.fxml");
    }

    @FXML
    private void goToVendedor() throws IOException {
        Main.push("views/RegistrarVendedor.fxml");
    }

    @FXML
    private void goToGanancia() throws IOException {
        Main.push("views/RegistrarGanancia.fxml");
    }
}
