package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtest {

	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserId,String username,String firstName,String lastname,String email,String pwd,String phn)
	{
		
		User user = new User();
		
		
		user.setId(Integer.parseInt(UserId));
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setPassword(pwd);
		user.setPhone(phn);
		
		Response response = UserEndpoints.createUsers(user);
		Assert.assertEquals(response.getStatusCode(), 200);	
			
	}
	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String username) {
	    Response response = UserEndpoints.DeleteUser(username);
	    Assert.assertEquals(response.getStatusCode(), 200);
	}

	
	
}
