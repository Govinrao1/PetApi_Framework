package org.EndPoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import org.Payload.User_Payload;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints {
	// created for getting urls from properties file
	static ResourceBundle getUrl()
	{
		ResourceBundle routes =ResourceBundle.getBundle("Properties"); // load properties file
		return routes;
	}
	
      public static Response createUser(User_Payload payload) {
    	  String post_url =getUrl().getString("post_Url");
    	  
    	  Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON)
    	  .body(payload)
    	  .when().post(post_url);
    	  return response;
      }
      
      public static Response getUser(String userName) {
    	  String get_url =getUrl().getString("get_Url");
    	  
    	  Response response =given()
    	  .pathParam("username", userName)
    	  .when().get(get_url);
    	  
    	  return response;
      }
      
      public static Response updateUser(String userName, User_Payload payload) {
    	  String update_url =getUrl().getString("update_Url");
    			  
    	  Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON)
    	  .pathParam("username", userName)
    	  .body(payload)
    	  .when().put(update_url);
    	  return response;
      }
      
      public static Response deleteUser(String userName) {
    	  String delete_url =getUrl().getString("delete_Url");
    	  
    	  Response response =given()
    	  .pathParam("username", userName)
    	  .when().delete(delete_url);
    	 
		return response;
    	  
      }
      
      
      
      
      
      
      
      
      
      
      
}
