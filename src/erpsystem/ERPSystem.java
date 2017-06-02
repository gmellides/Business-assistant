/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem;

import erpsystem.util.system.FileManager;
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
            
            stage.setTitle(f.getResources().getString("AppName"));
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
        launch(args);   
    }

}
