package pages;

import java.time.Duration;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JSUtils;
import utils.WaitUtils;


public class BookShelvesPage2 {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(BookShelvesPage2.class);

    public BookShelvesPage2(WebDriver driver)  {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //using findBy here
    @FindBy(xpath = "//div[@aria-label='Living menu']")
    private WebElement livingMenu;

    @FindBy(xpath = "//a[normalize-space()='Shoe Racks']")
    private WebElement shoeRacks;

     @FindBy(xpath = "//div[@aria-label='Sort By filter']")
     private WebElement sortByOption;

     //for High to Low
     @FindBy(xpath = "//div[@role='radio' and @aria-label='Sort by Price High to Low']")
     private WebElement priceHighToLowOption;

     @FindBy(xpath = "//a[normalize-space()='Side Tables']")
     private WebElement sideTablesLink;

     @FindBy(xpath = "//div[@class='UYQNp']")
     private List<WebElement> productPrices;

     @FindBy(xpath = "//span[contains(@class,'iR4SB')]")
     private List<WebElement> discountBadges;

     @FindBy(xpath = "//div[contains(@class,'o0mbO')]")
     private List<WebElement> productCards;

     @FindBy(xpath = "//div[@class='X0lDv']")
     private List<WebElement> newArrivalProductCards;

     private void hoverLivingMenu() {
         WaitUtils.waitForVisibility(driver, livingMenu);
         new Actions(driver).moveToElement(livingMenu).perform();
     }

     private void navigateToSideTables() {
         WaitUtils.waitForPageLoad(driver);
         hoverLivingMenu();
         WaitUtils.waitForVisibility(driver, sideTablesLink);
         JSUtils.click(driver, sideTablesLink);
     }

     private void waitForNewElements(List<WebElement> elements, int oldCount) {
         try {
             new WebDriverWait(driver, Duration.ofSeconds(5))
                     .until(d -> elements.size() > oldCount);
         } catch (TimeoutException ignored) {
             // No new items loaded – continue safely
         }
    }

     public void navigateAndSortByPriceHighToLow() {

         WaitUtils.waitForPageLoad(driver);
         hoverLivingMenu();

         WaitUtils.waitForVisibility(driver, shoeRacks);
         shoeRacks.click();

         WaitUtils.waitForVisibility(driver, sortByOption);
         sortByOption.click();

         WaitUtils.waitForVisibility(driver, priceHighToLowOption);
         JSUtils.scrollIntoView(driver, priceHighToLowOption);
         JSUtils.click(driver, priceHighToLowOption);
     }

     public int getProductCountGreaterThanPrice(int limitPrice) {

         WaitUtils.waitForVisibility(driver, productPrices.get(0));

         int count = 0;
         for (WebElement price : productPrices) {
             String text = price.getText().replace("₹", "").replace(",", "").trim();
             if (!text.isEmpty() && Integer.parseInt(text) > limitPrice) {
                 count++;
             }
         }
         return count;
    }

     public int countProductsWithDiscountMoreThan40() {

         navigateToSideTables();

         Set<String> uniqueItems = new HashSet<>();
         int previous = 0;

         for (int i = 0; i < 4; i++) {

             for (WebElement badge : discountBadges) {
                 int discount = Integer.parseInt(
                         badge.getText().replace("%", "").replace("OFF", "").trim()
                 );
                 if (discount > 40) {
                     uniqueItems.add(badge.getText() + badge.getLocation().getY());
                 }
             }

             if (uniqueItems.size() == previous) break;
             previous = uniqueItems.size();

             int currentCount = discountBadges.size();
             JSUtils.scrollBy(driver, 800);
             waitForNewElements(discountBadges, currentCount);
         }
         return uniqueItems.size();
    }

     public List<String> listProductNamesWithDiscountAbove40() {

         navigateToSideTables();

         List<String> names = new ArrayList<>();
         int previous = 0;

         for (int i = 0; i < 4; i++) {

             for (WebElement card : productCards) {

                 List<WebElement> badges =
                         card.findElements(By.xpath(".//span[contains(@class,'iR4SB')]"));

                 if (badges.isEmpty()) continue;

                 int discount = Integer.parseInt(
                         badges.get(0).getText().replace("%", "").replace("OFF", "").trim()
                 );

                 if (discount > 40) {
                      String name = card
                             .findElement(By.xpath(".//h3[@class='XxwSy']"))
                             .getText();
                     if (!names.contains(name)) names.add(name);
                 }
             }

             if (productCards.size() == previous) break;
             previous = productCards.size();

             int currentCount = productCards.size();
             JSUtils.scrollBy(driver, 800);
             waitForNewElements(productCards, currentCount);
         }
         return names;
    }

     public void printNewArrivalsProductNamesOnly() {

         navigateToSideTables();

         Set<String> names = new LinkedHashSet<>();
         int previous = 0;

         for (int i = 0; i < 6; i++) {

             for (WebElement card : newArrivalProductCards) {

                 List<WebElement> badge =
                         card.findElements(By.xpath(".//div[contains(text(),'New')]"));

                 if (!badge.isEmpty()) {
                     names.add(
                             card.findElement(By.xpath(".//img[@alt]"))
                                     .getAttribute("alt")
                                     .trim()
                     );
                 }
             }

             if (newArrivalProductCards.size() == previous) break;
             previous = newArrivalProductCards.size();

             int currentCount = newArrivalProductCards.size();
             JSUtils.scrollBy(driver, 900);
             waitForNewElements(newArrivalProductCards, currentCount);
         }

         int i = 1;
         for (String name : names) {
             log.info(i++ + ". " + name);
         }
         log.info("Total New Arrival Products: {}", names.size());
    }

     public int countProductsWithDiscountGreaterThan(int threshold) {

         navigateToSideTables();

         Set<String> countedItems = new HashSet<>();
         int previous = 0;

         for (int i = 0; i < 4; i++) {

             for (WebElement badge : discountBadges) {

                 String text = badge.getText();
                 if (!text.contains("%")) continue;

                 int discount = Integer.parseInt(
                         text.replace("%", "").replace("OFF", "").trim()
                 );

                 if (discount > threshold) {
                     countedItems.add(text + badge.getLocation().getY());
                 }
             }

             if (countedItems.size() == previous) break;
             previous = countedItems.size();

             int currentCount = discountBadges.size();
             JSUtils.scrollBy(driver, 800);
             waitForNewElements(discountBadges, currentCount);
         }

         log.info("Products with discount > {}% : {}", threshold, countedItems.size());
         return countedItems.size();
     }
}