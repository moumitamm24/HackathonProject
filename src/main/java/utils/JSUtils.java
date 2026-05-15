package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtils
{
    private JSUtils()
    {
    }
    // Scroll element into view (center):
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
    // Scroll element into view (top):
    public static void scrollIntoViewTop(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    // Click using JavaScript:
    public static void click(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    private static JavascriptExecutor js(WebDriver driver)
    {
        return (JavascriptExecutor) driver;
    }
    // Scrolling to the middle:
    public static void scrollToMiddle(WebDriver driver)
    {
        js(driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight / 2);"
        );
    }
    // Clicking on the Element by using JS:
    public static void clickUsingJS(WebDriver driver, WebElement element)
    {
        js(driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
    // Blur the Element:
    public static void blurElement(WebDriver driver, WebElement element)
    {
        js(driver).executeScript(
                "arguments[0].blur();",
                element
        );
    }
    // Setting the value:
    public static void setValue(WebDriver driver, WebElement element, String value)
    {
        js(driver).executeScript(
                "arguments[0].value=arguments[1];",
                element,
                value
        );
    }
    // Click using JavaScript
    public static void clickElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    // Scroll down by pixels
    public static void scrollBy(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
    // Scroll to bottom
    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    public static void scrollBy(WebDriver driver, int yPixels) {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0, arguments[0]);", yPixels);
    }
}


