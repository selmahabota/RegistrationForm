package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static WebDriver driver;

    public static WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public static void typeIn(By locator, String Text) {
        getElement(locator).sendKeys(Text);
    }

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/register");
        driver.manage().window().maximize();

        By firstNameFieldLocator = By.id("FirstName");
        By lastNameFieldLocator = By.id("LastName");
        By emailFieldLocator = By.id("Email");
        By passwordFieldLocator = By.id("Password");
        By confirmPasswordFieldLocator = By.id("ConfirmPassword");
        By registerButtonLocator = By.id("register-button");
        By continueRegisterButtonLocator = By.cssSelector(".button-1.register-continue-button");
        By resultLocator = By.cssSelector(".result");

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);

        typeIn(firstNameFieldLocator, "Selma");
        typeIn(lastNameFieldLocator, "Habota");
        typeIn(emailFieldLocator, "selma" + randomInt + "@gmail.com");
        typeIn(passwordFieldLocator, "25102023");
        typeIn(confirmPasswordFieldLocator, "25102023");
        getElement(registerButtonLocator).click();

        String expectedText = "Your registration completed";
        String actualText = getElement(resultLocator).getText();
        Assert.assertEquals(actualText, expectedText, "Text not matched!");

        String actualUrl = "https://demowebshop.tricentis.com/registerresult/1";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Url not matched");

        Assert.assertTrue(getElement(continueRegisterButtonLocator).isEnabled());

        getElement(continueRegisterButtonLocator).click();

        driver.quit();
    }
}