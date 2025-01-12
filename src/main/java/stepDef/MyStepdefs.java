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
import java.util.Random;

public class MyStepdefs extends env_target {
    @Given("^User is on parabank homepage$")
    public void userIsOnParabankHomepage() {
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(parabankLink);
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='leftPanel']/h2"))
        );
    }

    @When("^User click register link button$")
    public void userClickRegisterLinkButton() {
        driver.findElement(By.xpath("//a[contains(@href, 'register')]")).click();
    }

    @Then("^User is in register page$")
    public void userIsInRegisterPage() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Signing up is easy!')]"))
        );
    }

    @When("^User input name$")
    public void userInputName() {
        //User input firstname
        driver.findElement(By.id("customer.firstName")).sendKeys("Myskill");
        //User input lastname
        driver.findElement(By.name("customer.lastName")).sendKeys("Indo");
    }

    @And("^User input address detail$")
    public void userInputAddressDetail() {
        //User input Address
        driver.findElement(By.id("customer.address.street")).sendKeys("Sweet Home");
        //User input City
        driver.findElement(By.xpath("//*[@class='input'][@id='customer.address.city']")).sendKeys("London");
        //User input State
        driver.findElement(By.cssSelector("#customer\\.address\\.state")).sendKeys("Negara");
        //User input ZipCode
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/form/table/tbody/tr[6]/td[2]/input")).sendKeys("112233");
        //User input Phone#
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("12345678");
        //User input SSN
        driver.findElement(By.id("customer.ssn")).sendKeys("1234567");
    }

    @And("^User fill valid username and password$")
    public void userFillValidUsernameAndPassword() {
        Random rand = new Random();
        int userRand = rand.nextInt(10000);
        //Input username
        driver.findElement(By.id("customer.username")).sendKeys("user"+userRand);
        //Input password
        driver.findElement(By.id("customer.password")).sendKeys("12345");
    }

    @And("^User input password confirmation$")
    public void userInputPasswordConfirmation() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("12345");
    }

    @When("^User click Register button$")
    public void userClickRegisterButton() {
        driver.findElement(By.xpath("//*[@class='button'][@value='Register']")).click();
    }

    @Then("^User regist successfully$")
    public void userRegistSuccessfully() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Your account was created successfully. You are now logged in.')]"))
        );
        driver.quit();
    }

    @And("^user input invalid password confirmation$")
    public void userInputInvalidPasswordConfirmation() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("11111");
    }

    @Then("^User get error password did not match$")
    public void userGetErrorPasswordDidNotMatch() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Passwords did not match.')]"))
        );
        driver.quit();
    }
}
