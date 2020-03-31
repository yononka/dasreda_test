package api_tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPetPOST {
	
//	Add a new pet to the store
	
	DataUtilities getdata = new DataUtilities();

	@Test
	public void postCorrectBody() {
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
        
        Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertNotNull(response.path("id"));
		Assert.assertEquals(response.path("category.id").toString(), "3");
		Assert.assertEquals(response.path("category.name").toString(), "pet");
		Assert.assertEquals(response.path("name").toString(), "Tom Cat");
		Assert.assertEquals(response.path("photoUrls").toString(), "[fakephoto.jpg]");
		Assert.assertEquals(response.path("tags.id").toString(), "[5]");
		Assert.assertEquals(response.path("tags.name").toString(), "[home,furry]");
		Assert.assertEquals(response.path("status").toString(), "available");
        
	}
	
	@Test
	public void postEmptyBody() {
	  
		String reqBody = "";
       
        Response response = null;
        response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(reqBody)
            .post(Constants.BASE_URL + "/pet");
                
        Assert.assertEquals(response.getStatusCode(), 405);
          
	}
	

	@Test(dataProvider = "provideNames")
	public void postPetName(String name, int expectedCode) {
		
		String reqBody = "{\n" + 
				"  \"name\": " + name + ",\n" + 
				"  \"photoUrls\": [\n" + 
				"    \"fakephoto.jpg\"\n" + 
				"  ]\n" + 
				"}";
		
			       
        Response response = null;
        response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(reqBody)
            .post(Constants.BASE_URL + "/pet");
                           
        Assert.assertEquals(response.getStatusCode(), expectedCode);

	}
	
	@DataProvider(name = "provideNames")
	public Object[][] provideDataNames() {

		return new Object[][] { 
			{ "\"\"", 400 }, 
			{ "\" \"", 400 },
			{"", 400},
			{ "\"Jerry (&*)#$?>.\"", 200 },
			{ "\"Jerry Mouse\"", 200 }
			
		};
	}
	
	
	  @Test(dataProvider = "provideStatuses")
	  public void postPetStatus(String status, int expectedCode) {
			String reqBody = "{\n" + 
					"  \"name\": \"Tom Cat\",\n" + 
					"  \"photoUrls\": [\n" + 
					"    \"fakephoto.jpg\"\n" + 
					"  ],\n" + 
					"  \"status\":" + status + "\n" + 
					"}";
				       
	        Response response = null;
	        response = RestAssured.given()
	            .contentType(ContentType.JSON)
	            .body(reqBody)
	            .post(Constants.BASE_URL + "/pet");
	                           
	        Assert.assertEquals(response.getStatusCode(), expectedCode); 
       
	  }
	  
		@DataProvider(name = "provideStatuses")
		public Object[][] provideDataStatuses() {

			return new Object[][] { 
				{ "\"available\"", 200 }, 
				{ "\"pending\"", 200 },
				{"\"sold\"", 200},
				{ "\"broken\"", 405 }
				
			};
		}
		
		  @Test(dataProvider = "provideCategoryIds")
		  public void postPetCategoryIds(String categoryId, int expectedCode) {
				String reqBody = "{\n" +
						"  \"category\": {\n" + 
						"    \"id\": " + categoryId + ",\n" + 
						"    \"name\": \"pet\"\n" + 
						"  },\n" + 
						"  \"name\": \"Tom Cat\",\n" + 
						"  \"photoUrls\": [\n" + 
						"    \"fakephoto.jpg\"\n" + 
						"  ]\n" +  
						"}";
					       
		        Response response = null;
		        response = RestAssured.given()
		            .contentType(ContentType.JSON)
		            .body(reqBody)
		            .post(Constants.BASE_URL + "/pet");
		                                   
		        Assert.assertEquals(response.getStatusCode(), expectedCode); 
	       
		  }
		  
			@DataProvider(name = "provideCategoryIds")
			public Object[][] provideDataCategoryIds() {

				return new Object[][] { 
					{ "\"1\"", 200 },
					{ "\"-1\"", 400 },
					{ "", 500 },
					{ "92233720368547758071", 400 },
					{"\"char\"", 400}					
				};
			}
			
			
			
			  @Test(dataProvider = "provideEmptyCategoryIds")
			  public void postPetEmptyCategory(String categoryId, String expectedCatId) {
					String reqBody = "{\n" +
							"  \"category\": {\n" + 
							"    \"id\": " + categoryId + ",\n" + 
							"    \"name\": \"pet\"\n" + 
							"  },\n" + 
							"  \"name\": \"Tom Cat\",\n" + 
							"  \"photoUrls\": [\n" + 
							"    \"fakephoto.jpg\"\n" + 
							"  ]\n" +  
							"}";
						       
			        Response response = null;
			        response = RestAssured.given()
		            .contentType(ContentType.JSON)
		            .body(reqBody)
		            .post(Constants.BASE_URL + "/pet")
			        .then()
			        .extract().response();
			                                   
			        Assert.assertEquals(response.path("category.id").toString(), expectedCatId); 
		       
			  }
			  
				@DataProvider(name = "provideEmptyCategoryIds")
				public Object[][] provideDataEmptyCategoryIds() {

					return new Object[][] { 
						{ "\"\"", "0" }, 
						{ "\" \"", "0" },
						{"\"0\"", "0"}					
					};
				}
	  

}
