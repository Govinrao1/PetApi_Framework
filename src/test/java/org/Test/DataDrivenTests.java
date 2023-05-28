package org.Test;

import org.EndPoints.UserEndPoints;
import org.Payload.User_Payload;
import org.Utilities.DataProviderUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DataDrivenTests {
    @Test(priority=1, dataProvider="Data",dataProviderClass=DataProviderUtil.class)  
	public void testPostUser(String userId, String userName, String fName,String lName,String useremail, String pwd, String ph) 
	{
		User_Payload payload=new User_Payload();
		payload.setId(Integer.parseInt(userId));
		payload.setUsername(userName);
		payload.setFirstName(fName);
		payload.setLastName(lName);
		payload.setEmail(useremail);
		payload.setPassword(pwd);
		payload.setPhone(ph);
		
		 Response response =UserEndPoints.createUser(payload);
    	 Assert.assertEquals(response.getStatusCode(), 200);
    	 
	}
    
    @Test(priority=2, dataProvider="UserNames",dataProviderClass=DataProviderUtil.class)
    public void deleteUserByUserName(String userName) 
    {
    	Response response =UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
      
    }
}
