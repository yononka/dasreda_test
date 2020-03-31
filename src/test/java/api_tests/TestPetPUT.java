package api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPetPUT {
	
//	Update an existing pet
	
	DataUtilities getdata = new DataUtilities();
	
	@Test
	public void updatePet() {
		Response response = null;
		String petId = getdata.addPet();
		
		String reqBody = "{\n" +
				"  \"id\": "+ petId +",\n" +
				"  \"category\": {\n" + 
				"    \"id\": 7,\n" + 
				"    \"name\": \"wild\"\n" + 
				"  },\n" + 
				"  \"name\": \"Jerry Mouse\",\n" + 
				"  \"photoUrls\": [\n" + 
				"    \"jerryfakephoto.jpg\"\n" + 
				"  ],\n" + 
				"  \"tags\": [\n" + 
				"    {\n" + 
				"      \"id\": 9,\n" + 
				"      \"name\": \"grey\"\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"status\": \"sold\"\n" + 
				"}";
		

        response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(reqBody)
            .put(Constants.BASE_URL + "/pet");
	  
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.path("id").toString(), petId);
		Assert.assertEquals(response.path("category.id").toString(), "7");
		Assert.assertEquals(response.path("category.name").toString(), "wild");
		Assert.assertEquals(response.path("name").toString(), "Jerry Mouse");
		Assert.assertEquals(response.path("photoUrls").toString(), "[jerryfakephoto.jpg]");
		Assert.assertEquals(response.path("tags.id").toString(), "[9]");
		Assert.assertEquals(response.path("tags.name").toString(), "[grey]");
		Assert.assertEquals(response.path("status").toString(), "sold");
				
		getdata.deletePet(petId);
		
  }
	
	  @Test
	  public void updateInexistentPet() {
		  String petId = getdata.addPet(); 
		  String inexistentPetId = petId + "1";
		  String reqBody = "{\n" + 
					"  \"id\": "+ inexistentPetId +",\n" +
					"  \"name\": \"Tom Cat\",\n" + 
					"  \"photoUrls\": [\n" + 
					"    \"fakephoto.jpg\"\n" + 
					"  ]\n" + 
					"}";

			Response response = null;
			
	        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .put(Constants.BASE_URL + "/pet");
		       
	        Assert.assertEquals(response.getStatusCode(), 404);
		  
	  }
	  
	  @Test
	  public void updateDeletedPet() {

			String petId = getdata.addPet();
			
			String reqBody = "{\n" +
					"  \"id\": "+ petId +",\n" +
					"  \"name\": \"Tom Cat\",\n" + 
					"  \"photoUrls\": [\n" + 
					"    \"fakephoto.jpg\"\n" + 
					"  ]\n" + 
					"}";
					
			Response response = null;
			
			getdata.deletePet(petId);

	        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .put(Constants.BASE_URL + "/pet");
		
			Assert.assertEquals(response.getStatusCode(), 404);
		  
	  }
	
	
}
