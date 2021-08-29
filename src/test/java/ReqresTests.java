import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class ReqresTests {

   @Test
    void getUserTest (){
       given().
               when()
               .get("https://reqres.in/api/users/2")
               .then()
               .statusCode(200)
               .body("data.first_name",is("Janet"));
   }

    @Test
    void postUserTest () {
        given().
                contentType(JSON)
                .body("{ \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"}")
                .post("https://reqres.in/api/users")
                .then()
                .body("id", notNullValue());
    }

    @Test
    void loginSuccessfulTest () {
        given().
                contentType(JSON)
                .body("{  \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"}")
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }


    @Test
    void updateUserTest (){
         given().
                 contentType(JSON)
                 .body( "{ \"name\": \"morpheus\",\n" +
                            "    \"job\": \"zion resident\"}")
                 .post("https://reqres.in/api/users/2")
                 .then()
                 .body("job", is("zion resident"));

    }

    @Test
    void deleteUserTest (){
        given().
                when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }


}
