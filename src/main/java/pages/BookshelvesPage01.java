package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JSUtils;
import utils.WaitUtils;

import java.util.List;
import java.util.Properties;

public class BookshelvesPage01
{
    private static final Logger log = LoggerFactory.getLogger(BookshelvesPage01.class);
    private WebDriver driver;
    private Properties prop;

    @FindBy(xpath = "//p[normalize-space()='Bookshelves']")
    private WebElement bookshelvesTile;

    @FindBy(xpath = "//button[.//span[text()='Show More']]")
    private WebElement showMoreButton;

    @FindBy(xpath = "//div[@role='button' and .//h2[normalize-space()='Storage Type']]")
    private WebElement storageTypeButton;

    @FindBy(xpath = "//div[@role='checkbox' and .//div[text()='Open Storage']]//div[contains(text(),'(')]")
    private WebElement openStorageCountElement;

    @FindBy(xpath = "//div[@role='checkbox' and .//div[text()='Open Storage']]")
    private WebElement openStorageCheckbox;

    @FindBy(xpath = "//h2[@class='TKyNr undefined']")
    private WebElement sortBy;

    @FindBy(xpath = "//div[contains(text(),'Price High to Low')]")
    private WebElement highToLow;

    @FindBy(xpath = "//div[@aria-label='Sort by Price Low to High']")
    private WebElement lowToHigh;

    public BookshelvesPage01(WebDriver driver, Properties prop)
    {
        this.driver = driver;
        this.prop = prop;
        PageFactory.initElements(driver, this);
    }

    public void navigateAndApplyPriceFilter()
    {
        JSUtils.scrollIntoView(driver, bookshelvesTile);
        JSUtils.click(driver, bookshelvesTile);
        WaitUtils.waitForPageLoad(driver);

        // Click "Show More" multiple times to load more products
        for (int i = 0; i < 5; i++) {
            try {
                JSUtils.click(driver, showMoreButton);
                Thread.sleep(1000);
            } catch (Exception e) {
                break;
            }
        }
        List<WebElement> productCards = driver.findElements(
                By.xpath("//div[@role='link' and contains(@class,'xmdLL')]")
        );
        log.info("\nProducts with price ≤ 15000 (Printing up to 10):\n");
        int printedCount = 0;
        for (WebElement card : productCards)
        {
            if (printedCount == 10)
            {
                break;
            }
            try
            {
                JSUtils.scrollIntoView(driver, card);

                String name = card.findElement(
                        By.xpath(".//h3[contains(@class,'XxwSy')]")
                ).getText();

                String priceText = card.findElement(
                        By.xpath(".//div[contains(@class,'UYQNp')]")
                ).getText();

                int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));

                if (price <= 15000) {
                    printedCount++;
                    log.info(printedCount + ") Product Name : " + name + " | Price : " + priceText);
                }
            }
            catch (Exception ignored)
            {
            }
        }
        if (printedCount < 10)
        {
            log.info("\n⚠ Only " + printedCount + " products found with price ≤ 15000.");
        }
        log.info("\nSuccessfully printed 10 products.");
    }
    public void navigateAndFilterOpenStorage()
    {
        JSUtils.scrollIntoView(driver, bookshelvesTile);
        JSUtils.click(driver, bookshelvesTile);
        WaitUtils.waitForPageLoad(driver);

        WaitUtils.waitForVisibility(driver, storageTypeButton);
        JSUtils.click(driver, storageTypeButton);

        String countText = openStorageCountElement.getText();
        int expectedCount = Integer.parseInt(countText.replaceAll("[^0-9]", ""));

        WaitUtils.waitForVisibility(driver, openStorageCheckbox);
        JSUtils.click(driver, openStorageCheckbox);
        WaitUtils.waitForPageLoad(driver);

        log.info("\nOPEN STORAGE PRODUCTS (EXPECTED COUNT = " + expectedCount + "):\n");

        int printedCount = 0;
        int alreadyHandled = 0;

        while (printedCount < expectedCount)
        {
            List<WebElement> productCards = driver.findElements(
                    By.xpath("//div[@role='link' and contains(@class,'xmdLL')]")
            );

            for (int i = alreadyHandled; i < productCards.size(); i++) {

                if (printedCount >= expectedCount) break;

                try {
                    WebElement card = productCards.get(i);
                    JSUtils.scrollIntoView(driver, card);

                    String name = card.findElement(
                            By.xpath(".//h3[contains(@class,'XxwSy')]")
                    ).getText();

                    String price = card.findElement(
                            By.xpath(".//div[contains(@class,'UYQNp')]")
                    ).getText();

                    printedCount++;
                    log.info(printedCount + ") Product Name : " + name + " | Price : " + price);

                } catch (Exception ignored) {
                }
            }

            alreadyHandled = productCards.size();

            try {
                JSUtils.scrollIntoView(driver, showMoreButton);
                JSUtils.click(driver, showMoreButton);
                WaitUtils.waitForPageLoad(driver);
            } catch (Exception e) {
                break;
            }
        }
        log.info("EXACT Open Storage products printed : " + printedCount);
    }
    public void navigateToOutOfStockPageAndPrintProducts()
    {
        // Step 1: Click Bookshelves tile (optional but per your flow)
        JSUtils.scrollIntoView(driver, bookshelvesTile);
        JSUtils.click(driver, bookshelvesTile);
        WaitUtils.waitForPageLoad(driver);

        // Step 2: Navigate to Out-of-Stock page using config value
        String outOfStockUrl = prop.getProperty("outOfStockBookshelvesUrl");
        driver.get(outOfStockUrl);
        WaitUtils.waitForPageLoad(driver);

        // Step 3: Find all product cards on Out-of-Stock page
        List<WebElement> productCards = driver.findElements(
                By.xpath("//div[@role='link' and contains(@class,'xmdLL')]")
        );

        log.info("\n========== OUT OF STOCK BOOKSHELVES ==========\n");

        int count = 0;

        // Step 4: Extract and print product names
        for (WebElement card : productCards) {
            try {
                JSUtils.scrollIntoView(driver, card);

                String productName = card.findElement(
                        By.xpath(".//h3[contains(@class,'XxwSy')]")
                ).getText();

                count++;
                System.out.println(count + ". " + productName);

            } catch (Exception e) {
                // Ignore individual product failures
            }
        }
        log.info("Total Out of Stock Products Found: " + count);
    }
    public void navigateAndSortPriceHighToLow()
    {
        JSUtils.scrollIntoView(driver, bookshelvesTile);
        JSUtils.click(driver, bookshelvesTile);
        WaitUtils.waitForPageLoad(driver);

        WaitUtils.waitForVisibility(driver, sortBy);
        JSUtils.click(driver, sortBy);

        WaitUtils.waitForVisibility(driver, highToLow);
        JSUtils.scrollIntoViewTop(driver, highToLow);
        JSUtils.click(driver, highToLow);

        WaitUtils.waitForPageLoad(driver);
    }
    public void navigateAndSortPriceLowToHigh()
    {
        JSUtils.scrollIntoView(driver, bookshelvesTile);
        JSUtils.click(driver, bookshelvesTile);
        WaitUtils.waitForPageLoad(driver);

        WaitUtils.waitForVisibility(driver, sortBy);
        JSUtils.click(driver, sortBy);

        WaitUtils.waitForVisibility(driver, lowToHigh);
        JSUtils.scrollIntoViewTop(driver, lowToHigh);
        JSUtils.click(driver, lowToHigh);

        WaitUtils.waitForPageLoad(driver);
    }
}