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
        WindowsManager.CustomerManager = false;
            WindowsManager.NewCustomer = false;
            WindowsManager.SearchViewCustomer = false;
            WindowsManager.BackupCustomers = false;
        WindowsManager.SupplierManager = false;
            WindowsManager.NewSupplier = false;
            WindowsManager.SearchViewCustomer = false;
            WindowsManager.BackupCustomers = false;
        WindowsManager.ContactManager = false;
            WindowsManager.NewContact = false;
            WindowsManager.SearchViewContact = false;
            WindowsManager.BackupContacts = false;
        WindowsManager.FinanceWindow = false;
        WindowsManager.Storage = false;
        WindowsManager.Employee = false;      
    }
    
    public void toggle_window(String Input){
        switch(Input){
            case "menubar/business/View_BusinessData.fxml":
                ViewBusiness_toggle(false);
                break;
            case "menubar/admin/View_BusinessAdmin.fxml":
                ViewBusiness_toggle(false);
                break;
            case "menubar/business/Edit_BusinessData.fxml":
                EditBusiness_toggle(false);
                break;
            case "menubar/admin/Edit_BusinessAdmin.fxml":
                EditAdmin_toggle(false);
                break;
                
            case "customers/CustomerManager.fxml":
                CustomerManager_toggle(false);
                break;    
            case "customers/NewCustomer.fxml":
                NewCustomer_toggle(false);
                break;   
            
            case "suppliers/SupplierManager.fxml":
                SupplierManager_toggle(false);
                break;
            case "suppliers/NewSupplier.fxml":
                NewSupplier_toggle(false);
                break;
            case "suppliers/BackUp.fxml":
                BackupSupplier_toggle(false);
                break;
            case "suppliers/SearchView.fxml":
                SearchViewSupplier_toggle(false);
                break;
           
               
            case "contacts/ContactManager.fxml":
                ContactManager_toggle(false);
                break;
            case "contacts/BackUp.fxml":
                BackupContacts_toggle(false);
                break;
            case "contacts/SearchView.fxml":
                ShowSearchContact_toggle(false);
                break;
            case "contacts/NewContact.fxml":
                NewContact_toggle(false);
                break;
            
            case "finance/FinanceWindow.fxml":
                FinanceWindow_toggle(false);
                break;    
                
            case "storage/StorageManager.fxml":
                Storage_toggle(false);
                break;
           
        }
    }
    
    // MenuBar toogles
    public void ViewAdmin_toggle(boolean value){
        WindowsManager.ViewAdmin = value;
    }
    public void ViewBusiness_toggle(boolean value){
        WindowsManager.ViewBusiness = value;
    }
    public void EditAdmin_toggle(boolean value){
        WindowsManager.EditAdmin = value;
    }
    public void EditBusiness_toggle(boolean value){
        WindowsManager.EditBusiness = value;
    }
    // Buttons toogle
    public void ContactManager_toggle(boolean value){
        WindowsManager.ContactManager = value;
    }
        public void NewContact_toggle(boolean value){
            WindowsManager.NewContact = value;
        }
        public void ShowSearchContact_toggle(boolean value){
            WindowsManager.SearchViewContact = value;
        }
        public void BackupContacts_toggle(boolean value){
            WindowsManager.BackupContacts = value;
        }    
        
    public void CustomerManager_toggle(boolean value){
        WindowsManager.CustomerManager = value;
    }
        public void NewCustomer_toggle(boolean value){
            WindowsManager.NewCustomer = value;
        }
        public void SearchViewCustomer_toggle(boolean value){
            WindowsManager.SearchViewCustomer = value;
        }
        public void BackupCustomer_toggle(boolean value){
            WindowsManager.BackupCustomers = value;
        }   
        
    public void SupplierManager_toggle(boolean value){
        WindowsManager.SupplierManager = value;
    }
        public void NewSupplier_toggle(boolean value){
            WindowsManager.NewSupplier = value;
        }
        public void SearchViewSupplier_toggle(boolean value){
            WindowsManager.SearchViewCustomer = value;
        }
        public void BackupSupplier_toggle(boolean value){
            WindowsManager.BackupCustomers = value;
        }  
    public void FinanceWindow_toggle(boolean value){
        WindowsManager.FinanceWindow = value;
    }  
    public void Storage_toggle(boolean value){
        WindowsManager.Storage = value;
    }
    public void Employee_toggle(boolean value){
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
        public boolean NewContact_isOpen(){
            return WindowsManager.NewContact;
        }
        public boolean ShowSearchContact_isOpen(){
            return WindowsManager.SearchViewContact;
        }
        public boolean BackupContacts_isOpen(){
            return WindowsManager.BackupContacts;
        }
    public boolean CustomerManager_isOpen(){
        return WindowsManager.CustomerManager;
    }
        public boolean NewCustomer_isOpen(){
            return WindowsManager.NewCustomer;
        }   
        public boolean SearchViewCustomers_isOpen(){
            return WindowsManager.SearchViewCustomer;
        }
        public boolean BackUpCustomers_isOpen(){
            return WindowsManager.BackupCustomers;
        }
    public boolean SupplierManager_isOpen(){
        return WindowsManager.SupplierManager;
    }
        public boolean NewSupplier_isOpen(){
            return WindowsManager.NewSupplier;
        }
        public boolean SearchViewSupplier_isOpen(){
            return WindowsManager.SearchViewSupplier;    
        }
        public boolean BackUpSuppliers_isOpen(){
            return WindowsManager.BackUpSupplier;
        }
    public boolean FinanceWindow_isOpen(){
        return WindowsManager.FinanceWindow;
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
        private static boolean NewContact;
        private static boolean SearchViewContact;
        private static boolean BackupContacts;
    // ---- Customers Windows     
    private static boolean CustomerManager;
        private static boolean NewCustomer;
        private static boolean SearchViewCustomer;
        private static boolean BackupCustomers;
    // ---- Suppliers Windows
    private static boolean SupplierManager;
        private static boolean NewSupplier;
        private static boolean SearchViewSupplier;
        private static boolean BackUpSupplier;
    private static boolean FinanceWindow;
    private static boolean Storage;
    private static boolean Employee;
}
