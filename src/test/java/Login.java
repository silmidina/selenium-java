import config.env_target;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends env_target{
    @Test
    public void main(){
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
        //Isi username dan password
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Klik tombol login
        driver.findElement(By.xpath("//input[@type='submit'][@data-test='login-button']")).click();
        //Tunggu hingga halaman Products terlihat
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(), 'Products')]"))
        );
        //Tutup browser
        driver.quit();
    }

    @Test
    public void invalidUsername(){
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
        //Isi username dan password
        driver.findElement(By.name("user-name")).sendKeys("standard_sauce");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Klik tombol login
        driver.findElement(By.xpath("//input[@type='submit'][@data-test='login-button']")).click();
        //Tunggu hingga halaman Products terlihat
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3/button"))
        );
        //Tutup browser
        driver.quit();
    }


}
