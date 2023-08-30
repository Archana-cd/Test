import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestCase_GETandPost {
	
	@Test
	public void Test01_Get() {
		
		baseURI="https://api.restful-api.dev";
				
		given().get("/objects")
		.then()
		.statusCode(200)
		.body("data.capacity[0]",equalTo("128 GB") )
		.and()
		.body("data.color[0]",equalTo("Cloudy White") );
		
	}
	
	@Test
	
	public void Test02_GetFailed() {
		baseURI="https://api.restful-api.dev";
		
		given().get("/objects")
		.then()
		.statusCode(200)
		.body("data.capacity[0]",equalTo("Test 128 GB") );
		
	}

	
	@Test
    public void Test03_Post() {
    	
		
		String Payload1="{\r\n"
				+ "   \"name\": \"Apple MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2022,\r\n"
				+ "      \"price\": 1849.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}";
		
		//baseURI="https://api.restful-api.dev";
				
//		given().
//		baseUri("https://api.restful-api.dev").contentType("application/json").accept("application/json").
//		body(Payload1).when().post("/objects").then().statusCode(200).log().all().body("data.year[0]",equalTo("2019"));
		
		given().
		baseUri("https://api.restful-api.dev").contentType("application/json").accept("application/json").
		body(Payload1).
		when().post("/objects").
		then().assertThat().body("data.year", equalTo(2022)).
		body("data[\"CPU model\"]", equalTo("Intel Core i9")).
		statusCode(200).log().all();
		
		
	
    }
	@Test
    public void Test04_PostFailed() {
    	
		
		String Payload1="{\r\n"
				+ "   \"name\": \"Apple MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2022,\r\n"
				+ "      \"price\": 1849.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}";
	
		
		given().
		baseUri("https://api.restful-api.dev").contentType("application/json").accept("application/json").
		body(Payload1).when().post("/objects").then().assertThat().body("data.year", equalTo(2022)).
		body("data[\"CPU model\"]", equalTo("TestIntel Core i9")).
		statusCode(200).log().all();
		
	
    }
	
	
}
