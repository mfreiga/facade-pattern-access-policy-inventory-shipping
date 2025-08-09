package eist.shippingmicroservice;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/shipping/")
public class ShippingController {
    List<String> shippingList = new ArrayList<>();

    //TODO: check correct mapping type and end point from ShippingClient to fill mapping information. Then add the given input parameter to the shippingList instance and return necessary messages.
    @PostMapping("makeShipping")
    public String makeShipping(@RequestParam String shipping){
        shippingList.add(shipping);
        return "Shipping is added to records";
    }

    //TODO: check correct mapping type and end point from ShippingClient to fill mapping information. Then return shippingList instance instead of wrongList.
    @GetMapping("shippingRecord")
    public List<String> shippingRecord(){
        return shippingList;
    }

}
