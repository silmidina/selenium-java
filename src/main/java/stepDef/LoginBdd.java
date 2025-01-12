package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.env_target;
import java.time.Duration;

public class LoginBdd extends env_target{
    @Given("User is on login page")
    public void user_is_on_login_page() {
        //Set driverlocation path
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\drivers\\chromedriver.exe");
        //Maximize driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Set url
        driver.get(baseUrl);
        //Durasi maksimum untuk WebDriverWait
        Duration duration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        //Tunggu hingga tombol login terlihat
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@data-test='login-button']"))
        );
    }

    @When("User fill username and password")
    public void user_fill_username_and_password() {
        //Isi username dan password
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("User click login button")
    public void user_click_login_button() {
        //Klik tombol login
        driver.findElement(By.xpath("//input[@type='submit'][@data-test='login-button']")).click();
    }

    @Then("User verify login result")
    public void user_verify_login_result() {
        //Tunggu hingga halaman Products terlihat
        Duration duration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(), 'Products')]"))
        );
        //Tutup browser
        driver.quit();
    }

    @When("User fill invalid username and password")
    public void user_fill_invalid_username_and_password() {
        //Isi username dan password
        driver.findElement(By.name("user-name")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");
    }

    @Then("User get error message")
    public void user_get_error_message() {
        //Tunggu hingga halaman Products terlihat
        Duration duration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
        );
        //Tutup browser
        driver.quit();
    }

    @When("^User input (.*) and (.*)$")
    public void user_fill_invalid_username_and_password(String username, String password) {
        //Isi username dan password
        driver.findElement(By.name("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("^User get verify login (.*)$")
    public void user_verify_login_result(String result) {
        //Tunggu hingga halaman Products terlihat
        Duration duration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result == "Passed"){
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(), 'Products')]"))
            ));
        } else if (result == "Failed") {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
            ));
        }

        //Tutup browser
        driver.quit();
    }
}
