/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.system;

public class WindowsManager {

    public void Init(){
        // MenuBar 
        WindowsManager.ViewAdmin = false;
        WindowsManager.ViewBusiness = false;
        WindowsManager.EditAdmin = false;
        WindowsManager.EditBusiness = false;
        // Buttons
        WindowsManager.ContactManager = false;
        WindowsManager.CustomerManager = false;
        WindowsManager.Storage = false;
        WindowsManager.Employee = false;
        WindowsManager.SupplierManager = false;
    }
    
    // MenuBar toogles
    public void ViewAdmin_toogle(boolean value){
        WindowsManager.ViewAdmin = value;
    }
    public void ViewBusiness_toogle(boolean value){
        WindowsManager.ViewBusiness = value;
    }
    public void EditAdmin_toogle(boolean value){
        WindowsManager.EditAdmin = value;
    }
    public void EditBusiness_toogle(boolean value){
        WindowsManager.EditBusiness = value;
    }
    // Buttons toogle
    public void ContactManager_toogle(boolean value){
        WindowsManager.ContactManager = value;
    }
    public void CustomerManager_toogle(boolean value){
        WindowsManager.CustomerManager = value;
    }
    public void SupplierManager_toogle(boolean value){
        WindowsManager.SupplierManager = value;
    }
    public void Storage_toogle(boolean value){
        WindowsManager.Storage = value;
    }
    public void Employee_toogle(boolean value){
        WindowsManager.Employee = value;
    }
    
    // Menubar Is Open
    public boolean ViewAdmin_isOpen(){
        return WindowsManager.ViewAdmin;   
    }
    public boolean ViewBusiness_isOpen(){
        return WindowsManager.ViewBusiness;
    }
    public boolean EditAdmin_isOpen(){
        return WindowsManager.EditAdmin;
    }
    public boolean EditBusiness_isOpen(){
        return WindowsManager.EditBusiness;
    }
    // if is true the window is open if it false is closed
    public boolean ContactManager_isOpen(){
        return WindowsManager.ContactManager;   
    }
    public boolean CustomerManager_isOpen(){
        return WindowsManager.CustomerManager;
    }
    public boolean SupplierManager_isOpen(){
        return WindowsManager.SupplierManager;
    }
    public boolean Storage_isOpen(){
        return WindowsManager.Storage;
    }
    public boolean Employee_isOpen(){
        return WindowsManager.Employee;
    }
    
    // Menubar Windows 
    private static boolean ViewAdmin;
    private static boolean ViewBusiness;
    private static boolean EditAdmin;
    private static boolean EditBusiness;
    // Buttons Windows
    private static boolean ContactManager;
    private static boolean CustomerManager;
    private static boolean SupplierManager;
    private static boolean Storage;
    private static boolean Employee;
}
