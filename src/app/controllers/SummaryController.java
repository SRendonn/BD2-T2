package app.controllers;

import app.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class SummaryController {

    @FXML
    private void goToHome() throws IOException {
        Main.push("views/index.fxml");
    }
}
