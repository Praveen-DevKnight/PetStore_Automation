package api.endpoints;

import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.*;
import static io.restassured.RestAssured.given;
import api.payload.User;

public class UserEndpoints {

	public static Response createUser(User payLoad)
	{
		Response response =given()
			.log().body()	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payLoad)
		.when()
			.post(Routes.post_url)
		.then()	
			.log().all()
			.extract().response();
		
		return response;
	}
	
	public static Response Updateuser(String username,User payLoad)
	{
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username",username)
				.body(payLoad)
			.when()
				.put(Routes.put_url);
;
				
		return response;		
		
	}
	
	public static Response FetchUser(String username)
	{
		Response response = given()
				.pathParam("username",username)
				.when()
					.get(Routes.get_url);
		
		return response;
	}
	
	public static Response DeleteUser(String username)
	{
		Response response = given()
				.pathParam("username",username)
				.when()
					.delete(Routes.delete_url);
		return response;
	}
	public static Response createUsers(User payLoad) {
	    System.out.println("=== Sending User Payload ===");
	    System.out.println("ID: " + payLoad.getId());
	    System.out.println("Username: " + payLoad.getUsername());
	    System.out.println("First Name: " + payLoad.getFirstName());
	    System.out.println("Last Name: " + payLoad.getLastName());
	    System.out.println("Email: " + payLoad.getEmail());
	    System.out.println("Password: " + payLoad.getPassword());
	    System.out.println("Phone: " + payLoad.getPhone());
	    System.out.println("============================");

	    Response response = given()
	        .log().body() // log request body before sending
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	        .body(payLoad)
	    .when()
	        .post(Routes.post_url)
	    .then()
	        .log().all() // log the full response
	        .extract().response();

	    return response;
	}
	

	
}
