package org.Test;

import org.EndPoints.UserEndPoints;
import org.Payload.User_Payload;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserTests {
     public Faker faker;
     public User_Payload userPayload;
     
     @BeforeClass
     public void setUpData() 
     {
    	faker =new Faker();
    	userPayload =new User_Payload();
    	userPayload.setId(faker.idNumber().hashCode());
    	userPayload.setUsername(faker.name().username());
    	userPayload.setFirstName(faker.name().firstName());
    	userPayload.setLastName(faker.name().lastName());
    	userPayload.setEmail(faker.internet().emailAddress());
    	userPayload.setPassword(faker.internet().password(5, 10));
    	userPayload.setPhone(faker.phoneNumber().cellPhone());
    	System.out.println("User details: "+userPayload.toString());
     }
     
     // create new user
     @Test(priority=1)
     public void testPostUser() 
     {
    	 Response response =UserEndPoints.createUser(userPayload);
    	 response.then().log().body();
    	 Assert.assertEquals(response.getStatusCode(), 200);
    	 
    	 
     }
     // get user response
     @Test(priority=2)
     public void testGetUser()
     {
    	 Response response =UserEndPoints.getUser(this.userPayload.getUsername());
    	 response.then().log().body();
    	 Assert.assertEquals(response.getStatusCode(), 200);
     }
     
     @Test(priority=3)
     public void updateUser() 
     {
    	 // update user
    	 userPayload.setFirstName(faker.name().firstName());
     	 userPayload.setLastName(faker.name().lastName());
     	 userPayload.setEmail(faker.internet().emailAddress());
     	
    	 Response response =UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
    	 response.then().log().body();
    	 Assert.assertEquals(response.getStatusCode(), 200);
    	 
       // check the response of the update user
    	 Response responseOfterUpdate =UserEndPoints.getUser(this.userPayload.getUsername());
    	 Assert.assertEquals(responseOfterUpdate.getStatusCode(), 200);
     
     }
     // delete the existing user 
      @Test(priority=4)
      public void deleteUser()
       {
         Response response =UserEndPoints.deleteUser(this.userPayload.getUsername());
         response.then().log().body();
         Assert.assertEquals(response.getStatusCode(), 200);
       }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
