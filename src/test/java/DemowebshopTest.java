import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

public class DemowebshopTest {
    @Test
    void addToWishListTest(){
        String cookieTest= "NOPCOMMERCE.AUTH=9EA705E677DCDA6403154932C9E3251B9" +
                "72AE34618BAF558209453EF3E673E99757F0B2B33E23B6059BD9D277BC9" +
                "F065344C8DEA0B9F1D475D770738F625124FCABF59478193D89B5D06" +
                "7D3C6126BABAA14EFD8F767DD66F66E63E008DF343EC5F4AFC22" +
                "B22086B2501FDFF0B2B3A5F3D90AE832F4F962D91AE44B4657E05AE5FA627EC8DDA39D760E90CA257FD8198F; " +
                "Nop.customer=25cf0a7e-a772-4c38-be85-ad25d29cba07; " +
                "__atuvc=33%7C36; __atuvs=6134c1cb6553346c000; " +
                "__utmb=78382081.10.9.1630847436325";
        Response response =
            given()
                .contentType("application/x-www-form-urlencoded")
                .cookie("cookieTest")
                .body("addtocart_43.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/43/2")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .extract().response();

        String wishListAmount = response.path("updatetopwishlistsectionhtml");
        String cookieTestBack = response.cookie("NOP.CUSTOMER");



        open("http://demowebshop.tricentis.com/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("NOP.CUSTOMER", cookieTestBack));
        open("http://demowebshop.tricentis.com");
        assertThat($(".wishlist-qty").getText()).isEqualTo(wishListAmount);





    }
}
