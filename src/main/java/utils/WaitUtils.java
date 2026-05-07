package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils
{
    public static WebDriverWait wait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public static WebElement waitForVisibility(WebDriver driver, By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void waitForVisibility(WebDriver driver, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitForPageLoad(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d ->
                        ((JavascriptExecutor) d)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
    public static void waitForPresence(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}