package api_tests;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataUtilities {
	
	
	public String addPet() {
		
		String reqBody = "{\n" + 
				"  \"category\": {\n" + 
				"    \"id\": 3,\n" + 
				"    \"name\": \"pet\"\n" + 
				"  },\n" + 
				"  \"name\": \"Tom Cat\",\n" + 
				"  \"photoUrls\": [\n" + 
				"    \"fakephoto.jpg\"\n" + 
				"  ],\n" + 
				"  \"tags\": [\n" + 
				"    {\n" + 
				"      \"id\": 5,\n" + 
				"      \"name\": \"home,furry\"\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"status\": \"available\"\n" + 
				"}";
       
        Response response = null;
        response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(reqBody)
            .post(Constants.BASE_URL + "/pet");
                
        Long petId = response.path("id");
     
        return petId.toString();

        }
        

	public void deletePet(String petId) {
        given()
            .contentType(ContentType.JSON)
            .delete(Constants.BASE_URL + "/pet/" + petId);

	}

}
