/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem;

import erpsystem.util.system.FileManager;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ERPSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        if (manager.Create_output_Folder()){
            FXMLLoader f = new FXMLLoader();

            f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));

            Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/Launcher.fxml").openStream());

            Scene scene = new Scene(root);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent we) {
                 System.exit(0);
              }
            });
            stage.setTitle("ERP SYSTEM");
            stage.setScene(scene);
            stage.setResizable(false);
            //Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
            //stage.getIcons().add(icon);
            stage.show();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);   
    }
    FileManager manager = new FileManager();
}
