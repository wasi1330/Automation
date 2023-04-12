package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyStepdefs {

    WebDriver driver;
    WebDriverWait wait;
    @Given("I have entered a correct email {string}")
    public void iHaveEnteredACorrectEmail(String email) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");

        driver = new ChromeDriver(chromeOptions);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Waseem\\OneDrive\\Desktop\\driver\\chromedriver.exe");
        driver.manage().window().maximize();
        driver.get("https://login.mailchimp.com/signup/");
        WebElement cookies = driver.findElement(By.id("onetrust-reject-all-handler"));
        cookies.click();
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @And("I have entered a valid username {string}")
    public void iHaveEnteredAValidUsername(String username) throws InterruptedException {

        WebElement usernameField = driver.findElement(By.id("new_username"));
        usernameField.sendKeys("");
        usernameField.clear();
        usernameField.sendKeys(username);

    }


    @And("I have entered a valid password {string}")
    public void iHaveEnteredAValidPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("new_password"));
        passwordField.sendKeys(password);

    }

    @When("I pressed the sign up button")
    public void iPressedTheSignUpButton() throws InterruptedException {
        WebElement signupButton = driver.findElement(By.id("create-account-enabled"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("create-account-enabled")));
        signupButton.click();


    }

    @Then("an account is created {string}")
    public void anAccountIsCreated(String expected) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("!margin-bottom--lv3")));
        WebElement checkMail = driver.findElement(By.className("!margin-bottom--lv3"));
        String actual = checkMail.getText();
        assertEquals(expected, actual);
    }


    @Then("an account is not created for long username is given {string}")
    public void anAccountIsNotCreatedForLongUsernameIsGiven(String expected) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("invalid-error")));
        WebElement checkUserName = driver.findElement(By.className("invalid-error"));
        String actual = checkUserName.getText();
        assertEquals(expected, actual);
    }
    @Then("an account is not created for user already exists {string}")
    public void anAccountIsNotCreatedForUserAlreadyExists(String expected) throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("invalid-error")));
        WebElement checkUserExists = driver.findElement(By.className("invalid-error"));

        String actual = checkUserExists.getText();
        if (actual.contains("Great minds think alike")) {
            actual = "Great minds think alike";
        }
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Then("an account is not created as email is required {string}")
    public void anAccountIsNotCreatedAsEmailIsRequired(String expected) {
        WebElement checkEmail = driver.findElement(By.className("invalid-error"));
        String actual = checkEmail.getText();
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }



}