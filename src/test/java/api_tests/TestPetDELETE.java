package api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPetDELETE {
	
	DataUtilities getdata = new DataUtilities();

//	Deletes a pet
	
	
	  @Test
	  public void deletePet() {
		String petId = getdata.addPet(); 
		
		Response response = null;
		response = (Response) RestAssured.given()
        .contentType(ContentType.JSON)
        .delete(Constants.BASE_URL + "/pet/" + petId);
	       
        Assert.assertEquals(response.getStatusCode(), 200);
        
		response = (Response) RestAssured.given()
				.contentType(ContentType.JSON)
	        .when()
	        	.get(Constants.BASE_URL + "/pet/" + petId);
		
		Assert.assertEquals(response.getStatusCode(), 404);
	  
	  }
	  
	  @Test
	  public void deleteInexistentPet() {
			String petId = getdata.addPet(); 
			String inexistentPetId = petId + "1";
			
			Response response = null;
			response = (Response) RestAssured.given()
	        .contentType(ContentType.JSON)
	        .delete(Constants.BASE_URL + "/pet/" + inexistentPetId);
		       
	        Assert.assertEquals(response.getStatusCode(), 404);
	  }
  
	  
	  @Test
	  public void deleteDeletedPet() {
			String petId = getdata.addPet(); 
			
			Response response = null;
			
			getdata.deletePet(petId);

			response = (Response) RestAssured.given()
	        .contentType(ContentType.JSON)
	        .delete(Constants.BASE_URL + "/pet/" + petId);

			
			Assert.assertEquals(response.getStatusCode(), 404);
	  }
}
