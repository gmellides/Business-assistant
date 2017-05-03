
package erpsystem.util.system;

import java.io.File;

public class FileManager {
    // MUST BE ON MAIN 
    public boolean Create_Data_Folder(){
        boolean flag = false;
            File data_dir = new File(System.getProperty("user.dir")+"/user_data");
            if (!data_dir.exists()){
                data_dir.mkdir();
                flag = true;
            }else{
                flag = true;
            }
        return flag;
    }
    
    public boolean Create_output_Folder(){
        boolean flag = false;     
            File output_dir = new File(MyDocuments_path);
            if(!output_dir.exists()){
                output_dir.mkdir();
                flag = true;
            }else{
                // do nothing 
                flag = true;
            }
        return flag;
    }

    public String getPath(){
        return MyDocuments_path;
    }

    private String MyDocuments_path = System.getProperty("user.home")+"/Documents/Erp System Files";
}
