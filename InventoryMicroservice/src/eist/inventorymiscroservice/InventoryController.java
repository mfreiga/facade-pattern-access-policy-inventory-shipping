package eist.inventorymiscroservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/inventory/")
public class InventoryController {

    int productNum = 50;

    //TODO Hint: be aware that the url value is "/inventory/". Therefore while completing the endpoint do not add extra "/".
    //TODO: check correct mapping type and end point from InventoryClient to fill mapping information. Then, convert the product parameter to the integer and add to productNum and return necessary messages.
    // An example message is: "Products are added! New amount: 50"
    @PostMapping("addProduct")
    public String addProduct(@RequestParam String product){
        //The given product parameter should be converted to the integer and added to the productNum.
        // As a response, return a message as "Products are added!
        // New amount: ", where <productNum> is the integer instance of the InventoryController.
        // An example message should look like this: Products are added! New amount: 50
        int temp = Integer.parseInt(product);
        productNum+= temp;
        return "Products are added! New amount: "+ productNum;
    }

    //TODO: check correct mapping type and end point from InventoryClient to fill mapping information. Then, convert the product parameter to the integer and remove from productNum.
    // If the input parameter is higher than productNum, make productNum 0 and return necessary messages. An example message is: "Products are removed! New amount: 50"
    @DeleteMapping("removeProduct")
    public String removeProduct(@RequestParam String product){
        int temp = Integer.parseInt(product);
        productNum-= temp;

        if(productNum < 0){
            productNum = 0;
        }
        return "Products are removed! New amount: " + productNum;
    }

    //TODO: check correct mapping type and end point from InventoryClient to fill mapping information. Then return necessary messages. An example message is: "Product amount: 50"
    @GetMapping("checkProduct")
    public String checkProduct(){
        return "Product amount: " + productNum;
    }
}
