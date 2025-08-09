package eist;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class InventoryClient {
    private RestTemplate rest;
    private static final String BASE_URL = "http://localhost:8080/inventory/";

    private String messages = new String();

    public InventoryClient() {
        this.rest = new RestTemplate();
    }
    public void addProduct(int product) {
        messages = rest.postForEntity(BASE_URL + "addProduct?product=" + product, null, String.class).getBody();
    }

    public void checkProduct(){
        try {
            ResponseEntity<String> response = rest.getForEntity(BASE_URL + "checkProduct", String.class);
            messages = response.getBody();
        } catch (HttpClientErrorException ex) {
            messages = ex.getMessage();
        }
    }

    public void removeProduct(int product) {
        messages = rest.exchange(BASE_URL + "removeProduct?product=" + product, HttpMethod.DELETE, null, String.class).getBody();
    }
    public void printMessages() {
        System.out.println(messages);
    }

    public String getMessages() {
        return messages;
    }
}
