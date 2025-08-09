# Facade with Access Policy 

## Problem Statement 

As a fresh graduate, you are hired by an old beer factory to perform some digitalization on their inventory and shipping system. The boss of the factory informed you that accessing the inventory and shipping should happen with necessary permissions. Since you should have built the system as soon as possible, initially you are given the below table for these permissions.

![ACLTable.png](/api/files/markdown/Markdown_2023-05-22T12-47-51-265_952ab22d.png)

From the table, you should implement each role and permissions with their exact match. 

Also, as a design, you are given the below UML Diagram which shows the connections between classes and the systems. There are three different servers. The first one is the RootProject where your operations are located. Second one is the InventoryMicroservice and the third one is ShippingMicroservice. InventoryClient and ShippingClient in the RootProject are given to you as already built in. You should complete below tasks to finish missing parts of the project. 

### UML Diagram of the Application
@startuml

    
namespace RootProject {
class  RootProject.FactoryFacade {
        +FactoryFacade()
        +addProduct(String, int)
        +sellProduct(String, String, int)
        +checkProduct(String)
        +shippingRecord(String)
        }
    }
  
namespace RootProject {
      class RootProject.AccessControlList {
      +grantAccess(String, String)
      +hasAccess(String, String)

      }
    }
  
  namespace RootProject {
      class RootProject.InventoryClient {
        +addProduct(int)
        +checkProduct()
        +removeProduct()
        +printMessages()
        +getMessages()
        +createHttpEntity(String)
      }
    }
    
    namespace RootProject {
      class RootProject.ShippingClient {
        +makeShipping(String)
        +shippingRecord()
        +printMessages()
        +getMessages()
        +createHttpEntity(String)
      }
    }
    
    namespace InventoryMicroservice {
      class InventoryMicroservice.InventoryController {
            +addProduct(String)
            +removeProduct(String)
            +checkProduct()
      }
    }
    
    namespace ShippingMicroservice {
      class ShippingMicroservice.ShippingController {
            +makeShipping(String)
            +shippingRecord()
        
      }
    }
    
    

  RootProject.FactoryFacade -right-> RootProject.AccessControlList
  RootProject.FactoryFacade -down-> RootProject.InventoryClient
  RootProject.FactoryFacade -down-> RootProject.ShippingClient
  RootProject.InventoryClient .down.> InventoryMicroservice.InventoryController
  RootProject.ShippingClient .down.> ShippingMicroservice.ShippingController

hide empty fields
hide empty methods
hide circle
@enduml


### Repository structure

- `InventoryMicroservice/`: Can be started via the gradle task `:InventoryMicroservice:bootRun`.
- `ShippingMicroservice/`: Can be started via the gradle task `:ShippingMicroservice:bootRun`.
- `src/eist/Client.java:main` place code to test your solution here. 


### Part 1: AccessControlList

First, you need to implement AccessControlList.

**You have the following tasks:**

1. **Implement Giving Access:**
Implement the method `grantAccess(String role, String permission)` in the class `AccessControlList`. Make sure to not duplicate an existing role. 

2. **Implement Control for Access:**
Implement the method `hasAccess(String role, String permission)` in the class `AccessControlList`. It should check if a given role is existing with the given permission. If it is exist, return true, if it is not in the map, return false.

### Part 2: FactoryFacade

Secondly, you need to implement your FactoryFacade class to provide an easy interface.

**You have the following tasks:**
1. **Implement Constructor:**
Implement the method `FactoryFacade()` in the class `FactoryFacade`. Since you don't want to overload your Microservices with unpermitted requests, permission-checks should happen in Facade class. You should setup your AccessControlList instance in the constructor as it is defined in the above table. You can check the table and fill the ACL instance one by one with the permissions. Everytime a FactoryFacade instance is created, an AccessControlList object should be initialized with the same described permissions in the default constructor. 

2. **Implement Adding New Products:**
Implement the method `addProduct(String role, int product)` in the class `FactoryFacade`. A permisson check should be performed here. If the given role is permitted to perform the operation, it should continue to perform the task and add the product amount to the inventory. If the operation successful, you should return `getMessages()` method from related client. Otherwise <span class="red">"This role has no access" </span> should be returned. To perform necessary operations, the provided methods of the InventoryClient or ShippingClient can be invoked. 

3. **Implement Selling Products:**
Implement the method `sellProduct(String role, String shippingAddress, int product)` in the class `FactoryFacade`. A permisson check should be performed here. If the given role is permitted to perform operation, it should add the shipping address to the shipping list to keep records. Moreover, it should continue to perform task and remove the product amount from the inventory and return a message with using `getMessages()` method of `InventoryClient`.  If the given role has no rights to perform operation, <span class="red">"This role has no access" </span> should be returned. To perform necessary operations the provided methods of the InventoryClient or ShippingClient can be invoked.
4. **Implement Check Available Products:**
Implement the method `checkProduct(String role)` in the class `FactoryFacade`. A permisson check should be performed here. If the given role is permitted to perform the operation, it should continue to perform the task and return the amount of the product in the inventory. To return the message, `getMessages()` method of the related client can be used after correct method is called to perform operation. If the given role has no rights to perform operation, <span class="red">"This role has no access" </span> should be returned. To perform necessary operations the provided methods of the InventoryClient or ShippingClient can be invoked. 

5. **Implement Review Shipping Records:**
Implement the method `shippingRecord(String role)` in the class `FactoryFacade`. A permisson check should be performed here. Similar to selling products, only people who have "Sell" permission can see the shipping records of the sold products. If the given role is permitted to perform the operation, it should continue to perform the task and return the list of records from the shipping list. To return the message, `getMessages()` method of the related client can be used after correct method is called to perform operation. If the given role has no rights to perform the operation, <span class="red">"This role has no access" </span> should be returned. To perform necessary operations the provided methods of the InventoryClient or ShippingClient can be invoked. 

**<span class="red">!Important Notice:</span>** The permission checks should be implemented in a modular way. If the factory introduces new roles with the same set of permissions, checks should be able to work without any changes on the code.

### Part 3: Microservices

At the end, you need to implement REST APIs inside the Microservices.

**You have the following tasks:**

**<span class="red">!Important Notice/Hint!</span>** To be sure about type of your endpoints mapping and their URLs, check and understand InventoryClient and ShippingClient to see which mapping procedures and endpoints are used to reach these endpoints. 

1. **Implement Add Product:**
Implement the method `addProduct(String product)` in the class `InventoryController`. The given product parameter should be converted to the integer and added to the <span class="lila">productNum</span>. As a response, return a message as <code class="block">"Products are added! New amount: <productNum>"</code>, where `<productNum>` is the integer instance of the InventoryController. An example message should look like this: ```Products are added! New amount: 50```

2. **Implement Sell Product:**
Implement the method `removeProduct(String product)` in the class `InventoryController`. The given product parameter should be converted to the integer and susbtracted from the <span class="lila">productNum</span>. If the given amount of the product is higher than the available one, the new product number in the inventory should be 0. At the end of the operations, return a message as  <code class="block">"Products are removed! New amount: <productNum>"</code>, where `<productNum>` is the integer instance of the InventoryController. 

3. **Implement Check Product:**
Implement the method `checkProduct()` in the class `InventoryController`. Return a response as <code class="block">"Product amount: <productNum>"</code>, where `<productNum>` is the integer instance of the InventoryController.

4. **Implement Make Shipping:**
Implement the method `makeShipping(String shipping)` in the class `ShippingControler`. The given shipping paramater should be added to the shippingList. Also, as a response message, <code class="block">"Shipping is added to records"</code> should be returned.

5. **Implement Shipping Record:**
Implement the method `shippingRecord()` in the class `ShippingControler`. Return the <span class="lila">shippingList</span> as the records of the shippings. 





