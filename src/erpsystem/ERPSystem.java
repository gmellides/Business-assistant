/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem;

import erpsystem.util.system.FileManager;
import erpsystem.util.system.WindowsManager;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ERPSystem extends Application {
    private final FileManager manager = new FileManager();
    
    @Override
    public void start(Stage stage) throws Exception {
        if (manager.check_folders()){
           start_graphics(stage);
        }else{
            if(manager.create_all_folders())
                start_graphics(stage);
        }
    }

    public void start_graphics(Stage stage) throws IOException{
         FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/Launcher.fxml").openStream());
            Scene scene = new Scene(root);
            stage.setWidth(760);
            stage.setHeight(505);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent we) {
                 System.exit(0);
              }
            });
            stage.setTitle(fxml_loader.getResources().getString("AppName"));
            stage.setScene(scene);
            stage.setResizable(false);
            //Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
            //stage.getIcons().add(icon);
            stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new WindowsManager().Init();
        launch(args);   
    }

}
