
package erpsystem.util.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
/*
this class creates 
*/
public class FileManager {
    /**
     * Creates Data Folder on the path that jar is placed
     * inside this folder there are some xml files that saves 
     * @return 
     */
    public boolean Create_Data_Folder(){
        boolean flag = false;
            File data_dir = new File(user_data);
            if (!data_dir.exists()){
                data_dir.mkdir();
                data_dir.canWrite();
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
    public boolean create_card_folder(){
        boolean flag = false;
            File output_dir = new File(MyDocuments_path+"/Κάρτες");
            if (!output_dir.exists()){
                output_dir.mkdir();
                flag = true;
            }else{
                flag = true;
            }
        return flag;
    }

    public boolean save_logo_img(File source,String Extension){
        boolean flag = false;
            try{
                File destination = new File(user_data+"/logo."+Extension);
                FileChannel source_Channel = new FileInputStream(source).getChannel();
                FileChannel distination_Channel = new FileOutputStream(destination).getChannel();
                distination_Channel.transferFrom(source_Channel, 0, source_Channel.size());
            }catch(IOException e){
                e.printStackTrace();
            }
        return flag;
    }
    
    public String getMyDocumentsPath(){
        return MyDocuments_path;
    }
    public String getUserDataPath(){
        return user_data;
    }
    
    public boolean businessData_exist(){
        File data = new File(user_data+"/business_data.xml");
        return data.exists();
    }
    public boolean businessAdmin_exist(){
        File data = new File(user_data+"/admin_data.xml");
        return data.exists();
    }
    
    private final String user_data = System.getProperty("user.dir")+"/user_data";
    private final String MyDocuments_path = System.getProperty("user.home")+"/Documents/Erp System Files";
}
