package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import multichain.command.CommandElt;
import multichain.command.CommandManager;
import multichain.command.MultichainException;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;
    // el login y password se encuentra en %APPDATA%/Multichain/empresa/multichain.conf
    public static CommandManager commandManager = new CommandManager("localhost", "4410", "multichainrpc", "BHbz943UPzMkB5RFLVgcSjymEHaTmXjuUKN635UWgM3V");

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
        stage.setTitle("DB2-T2 Multichain");
        scene = new Scene(root, 854, 480);
        stage.setScene(scene);
        stage.show();
        try {
            commandManager.invoke(CommandElt.SUBSCRIBE, "vendedores");
            commandManager.invoke(CommandElt.SUBSCRIBE, "ganancias");
        } catch (MultichainException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ocurrió un error tratando de suscribirse a los streams.");
            alert.show();
        }
    }

    /**
     * Cambia la vista actual de la aplicación
     *
     * @param fxmlFile la ruta del archivo que se quiere mostrar
     * @throws IOException en caso de que el recurso no se pueda cargar
     */
    public static void push(String fxmlFile) throws IOException {
        scene.setRoot(FXMLLoader.load(Main.class.getResource(fxmlFile)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
