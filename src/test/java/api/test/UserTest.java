package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	User user;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		user=new User();
		
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5, 10));
		user.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	@Test(priority =1)
	public  void testPostUser()
	{
		logger.info("===========Creating User=============");
		Response response = UserEndpoints.createUsers(user);
		System.out.println(response);
		Assert.assertEquals(response.statusCode(),200);
		logger.info("============Created User==============");
	}
	@Test(priority = 3)
	public void FetchUser() 
	{
		logger.info("==============Fetching Data===========");
		
	    Response response = UserEndpoints.FetchUser(user.getUsername());
	    response.then().log().all();
	    Assert.assertEquals(response.statusCode(), 200);
	    logger.info("==============Data Feteched=================");
	}
	@Test(priority=2)
	public void testUpdateUserByName()
	{
		logger.info("==============Updating Data===========");
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.Updateuser(this.user.getUsername(),user);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data After Update
		Response responseAfterUpdate = UserEndpoints.FetchUser(this.user.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("==============Updated Data===========");
	}
	@Test(priority = 4)
	public void testDelUserByName()
	{
		logger.info("==============Deleting Data===========");
		Response response=UserEndpoints.DeleteUser(user.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("==============Data Deleted===========");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}