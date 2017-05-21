package erpsystem.util.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
/*
 - ERP FILES
  -- Κάρτα επιχείρησης
  -- Στοιχεία Επιχείρησης
    
*/
public class FileManager {
    /**
     * Creates Data Folder on the path that jar is placed
     * inside this folder there are some xml files that saves 
     * @return 
     */
    public boolean create_all_folders(){
        boolean method_flag = false;
        
            boolean app_data_flag = false;
                File app_data_factor = new File(getApp_data_business());
                if (!app_data_factor.exists()){
                    app_data_factor.mkdirs();
                    app_data_factor = new File(getApp_data_admin());
                    if(!app_data_factor.exists())
                        app_data_factor.mkdir();
                   app_data_flag=true; 
                }
                
            boolean documents_data_flag = false;
                File output_folder_factor = new File(getDocuments_business_data());
                if (!output_folder_factor.exists()){
                    output_folder_factor.mkdirs();
                    
                    output_folder_factor = new File(getDocuments_business_reports());
                    if (!output_folder_factor.exists())
                        output_folder_factor.mkdir();
                
                    output_folder_factor = new File(getDocuments_Customer());
                    if (!output_folder_factor.exists())
                        output_folder_factor.mkdir();
                    
                    output_folder_factor = new File(getDocuments_Customer_data());
                    if (!output_folder_factor.exists())
                        output_folder_factor.mkdir();
             
                    output_folder_factor = new File(getDocuments_Customer_repots());
                    if (!output_folder_factor.exists())
                        output_folder_factor.mkdir();
                    
                    documents_data_flag = true;
                }
  
        if (app_data_flag && documents_data_flag){
            method_flag = true;
        }
        
        return method_flag;
    }
    
    public boolean check_folders(){
        File folder_check;
        boolean method_flag = false;
        
            boolean app_data_flag = false;
                folder_check = new File(getApp_data_root());
                if (folder_check.exists())
                    app_data_flag = true;

            boolean output_data_flag = false;
                folder_check = new File(getDocuments_root());
                if (folder_check.exists())
                    output_data_flag = true;
        
        if (app_data_flag && output_data_flag)
            method_flag = true;
        
        return method_flag;
    }
    
    public boolean save_logo_img(File source,String Extension){
        boolean flag = false;
            try{
                File destination = new File(getApp_data_root()+"/logo."+Extension);
                FileChannel source_Channel = new FileInputStream(source).getChannel();
                FileChannel distination_Channel = new FileOutputStream(destination).getChannel();
                distination_Channel.transferFrom(source_Channel, 0, source_Channel.size());
            }catch(IOException e){
                e.printStackTrace();
            }
        return flag;
    }

    public String getApp_data_root() {
        return app_data_root;
    }
    public String getApp_data_business() {
        return app_data_business;
    }
    public String getApp_data_admin() {
        return app_data_admin;
    }
    public String getDocuments_root() {
        return Documents_root;
    }
    public String getDocuments_business() {
        return Documents_business;
    }
    public String getDocuments_business_data() {
        return Documents_business_data;
    }
    public String getDocuments_business_reports() {
        return Documents_business_reports;
    }
    public String getDocuments_Customer() {
        return Documents_Customer;
    }
    public String getDocuments_Customer_data() {
        return Documents_Customer_data;
    }
    public String getDocuments_Customer_repots() {
        return Documents_Customer_repots;
    }

    private final String app_data_root = System.getProperty("user.dir")+"/user_data";
    private final String app_data_business = app_data_root+"/business";
    private final String app_data_admin = app_data_root+"/admin";
    private final String Documents_root = System.getProperty("user.home")+"/Documents/Erp System Files";
    private final String Documents_business = Documents_root+"/Επιχείρηση";
    private final String Documents_business_data = Documents_business+"/Στοιχεία Επιχείρησης";
    private final String Documents_business_reports = Documents_business+"/Αγορές Επιχείρησης";
    private final String Documents_Customer = Documents_root+"/Πελάτες";
    private final String Documents_Customer_data = Documents_Customer+"/Στοιχεία Πελατών";
    private final String Documents_Customer_repots = Documents_Customer+"/Πολήσεις";
}
