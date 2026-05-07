package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.JSUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudyChairPage
{
    // WebDriver instance for this page
    private WebDriver driver;

    // Study menu on top navigation (hover action required)
    @FindBy(xpath = "//span[normalize-space()='Study']")
    private WebElement studyMenu;

    // Study Chairs link under Study menu
    @FindBy(xpath = "//a[normalize-space()='Study Chairs']")
    private WebElement studyChairsLink;

    // Sort By dropdown header on Study Chairs page
    @FindBy(xpath = "//h2[@class='TKyNr undefined']")
    private WebElement sortByOption;

    // Sort option: Popularity
    @FindBy(xpath = "//div[@aria-label='Sort by Popularity']")
    private WebElement popularityOption;

    // Sort option: Price High to Low
    @FindBy(xpath = "//div[contains(text(),'Price High to Low')]")
    private WebElement priceHighToLowOption;

    // Sort option: Price Low to High
    @FindBy(xpath = "//div[contains(text(),'Price Low to High')]")
    private WebElement priceLowToHighOption;

    // Sort option: Discount High to Low
    @FindBy(xpath = "//div[contains(text(),'Discount High to Low')]")
    private WebElement discountHighToLowOption;

    // List of all product card containers displayed on the page
    @FindBy(css = "div.o0mbO")
    private List<WebElement> productCards;

    // Prices inside each product card
    @FindBy(css = "div.o0mbO div.UYQNp")
    private List<WebElement> productPrices;

    // Product names inside each product card
    @FindBy(css = "div.o0mbO h3.XxwSy")
    private List<WebElement> productNames;

    // Page constructor to initialize WebElements using PageFactory
    public StudyChairPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Navigate to Study Chairs page using hover action
    public void navigateToStudyChairs() {
        Actions actions = new Actions(driver);
        actions.moveToElement(studyMenu).perform();
        studyChairsLink.click();
    }
    // Apply sorting by popularity
    public void sortByPopularity() {
        sortByOption.click();
        JSUtils.clickUsingJS(driver, popularityOption);
    }
    // Apply sorting by price from high to low
    public void sortByPriceHighToLow() {
        sortByOption.click();
        JSUtils.clickUsingJS(driver, priceHighToLowOption);
    }
    // Apply sorting by price from low to high
    public void sortByPriceLowToHigh() {
        sortByOption.click();
        JSUtils.clickUsingJS(driver, priceLowToHighOption);
    }
    // Apply sorting by discount from high to low
    public void sortByDiscountHighToLow() {
        sortByOption.click();
        JSUtils.clickUsingJS(driver, discountHighToLowOption);
    }
    // Fetch top three chairs grouped by same price
    public Map<String, List<String>> getTopThreeChairsWithSamePrice() {

        // Map to store price as key and list of product names as value
        Map<String, List<String>> priceMap = new LinkedHashMap<>();
        int attempts = 0;

        // Handle lazy loading by scrolling until at least 3 products are visible
        while (productCards.size() < 3 && attempts < 5) {
            JSUtils.scrollBy(driver, 900);
            attempts++;
        }

        int count = 0;

        // Iterate through product cards and collect names grouped by price
        for (int i = 0; i < productCards.size(); i++) {

            String price = productPrices.get(i).getText();
            String name = productNames.get(i).getText();

            // Stop once top three unique prices are collected
            if (count >= 3 && !priceMap.containsKey(price)) {
                break;
            }

            priceMap
                    .computeIfAbsent(price, k -> new ArrayList<>())
                    .add(name);

            count++;
        }
        return priceMap;
    }
}