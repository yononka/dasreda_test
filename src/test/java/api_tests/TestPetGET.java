package api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPetGET {
	
	DataUtilities getdata = new DataUtilities();

	@Test
	public void getPet() {
		Response response = null;
		String petId = getdata.addPet();
		
		response = (Response) RestAssured.given()
			.contentType(ContentType.JSON)
        .when()
        	.get(Constants.BASE_URL + "/pet/" + petId)
        .then()
	        .extract().response();  
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.path("id").toString(), petId);
		Assert.assertEquals(response.path("category.id").toString(), "3");
		Assert.assertEquals(response.path("category.name").toString(), "pet");
		Assert.assertEquals(response.path("name").toString(), "Tom Cat");
		Assert.assertEquals(response.path("photoUrls").toString(), "[fakephoto.jpg]");
		Assert.assertEquals(response.path("tags.id").toString(), "[5]");
		Assert.assertEquals(response.path("tags.name").toString(), "[home,furry]");
		Assert.assertEquals(response.path("status").toString(), "available");
				
		getdata.deletePet(petId);
	
  }

	
	
	
	
}
