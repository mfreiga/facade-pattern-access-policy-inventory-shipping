package eist;


public class FactoryFacade {

    private AccessControlList ACL;
    private InventoryClient inventoryClient;
    private ShippingClient shippingClient;

    //TODO: initialize variables in the constructor. AccessControlList instance should be filled according to Role/Permission table.
    public FactoryFacade(){
        this.ACL = new AccessControlList();
        this.ACL.grantAccess("SalesManager", "ADD");
        this.ACL.grantAccess("SalesManager", "SELL");
        this.ACL.grantAccess("SalesManager", "CHECK");

        this.ACL.grantAccess("SalesIntern", "ADD");
        this.ACL.grantAccess("SalesIntern", "CHECK");

        this.ACL.grantAccess("MarketingManager", "CHECK");

    }

    //TODO: implement addProduct method with calling necessary methods from InventoryClient and/or ShippingClient. Do permission checks and follow return message if permission fails.
    public String addProduct(String role, int product){
        if(ACL.hasAccess(role, "ADD" )){
            inventoryClient.addProduct(product);
            return inventoryClient.getMessages();
        }else{
            return "This role has no access";
        }

    }

    //TODO: implement sellProduct method with calling necessary methods from InventoryClient and/or ShippingClient. Do permission checks and follow return message if permission fails.
    public String sellProduct(String role, String shippingAddress, int product){
        if(ACL.hasAccess(role, "SELL" )){
            //add the shipping address to the shipping list to keep records.
            // Moreover, it should continue to perform task and remove the product amount from the inventory
            // and return a message with using getMessages() method of InventoryClient.
            shippingClient.makeShipping(shippingAddress);
            inventoryClient.removeProduct(product);
            return inventoryClient.getMessages();
        }else{
            return "This role has no access";
        }
    }

    //TODO: implement checkProduct method with calling necessary methods from InventoryClient and/or ShippingClient. Do permission checks and follow return message if permission fails.
    public String checkProduct(String role){
        if(ACL.hasAccess(role, "CHECK" )){
            inventoryClient.checkProduct();
            return inventoryClient.getMessages();
        }else{
            return "This role has no access";
        }
    }

    //TODO: implement shippingRecord method with calling necessary methods from InventoryClient and/or ShippingClient. Do permission checks and follow return message if permission fails.
    //TODO: be aware that only people who have "Sell" permission can access to the shippingRecords.
    public String shippingRecord(String role){
        if(ACL.hasAccess(role, "SELL" )){
            shippingClient.shippingRecord();
            return shippingClient.getMessages();
        }else{
            return "This role has no access";
        }
    }

    public AccessControlList getACL() {
        return ACL;
    }

    public void setACL(AccessControlList ACL) {
        this.ACL = ACL;
    }

    public InventoryClient getInventoryClient() {
        return inventoryClient;
    }

    public void setInventoryClient(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public ShippingClient getShippingClient() {
        return shippingClient;
    }

    public void setShippingClient(ShippingClient shippingClient) {
        this.shippingClient = shippingClient;
    }
}
