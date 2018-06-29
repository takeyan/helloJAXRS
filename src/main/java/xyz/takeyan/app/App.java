package xyz.takeyan.app;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.*;

 
public class App {
    
    public static void main(String[] args) {
        
        String user="SL293286"; 
        String apikey="1613ada34195a192362599ab4742666f05b5cb2ff52368faa91bdeb1ea623dc7"; 
        String user_api = user + ":" + apikey; 
        String auth_header = "Basic " + java.util.Base64.getEncoder().encodeToString( user_api.getBytes() ); 

        Client client = ClientBuilder.newClient();
  
        Response response = client
            .target("https://api.softlayer.com/")                             
            .path("rest/v3.1/SoftLayer_Account/getUsers.json")
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization",auth_header)
            .get();

        try {
            if (response.getStatus() == 200) { 
                String st = response.readEntity(String.class);
                JSONArray ja = new JSONArray(st);

                for (int i=0 ; i<ja.length() ; i++) {
                    System.out.println("### USERNAME = " + ja.getJSONObject(i).getString("username"));
                }
//                System.out.println(ja.toString());
                } 
          } catch (Exception e) {    
                e.printStackTrace();    
          } finally {
                response.close();
          }
    }
}
