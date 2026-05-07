package tests;

import base.BaseTest;
import pages.StudyChairPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC14_StudyChair_SortBy_PriceLowToHighTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC14_StudyChair_SortBy_PriceLowToHighTest.class);

    @Test
    public void verifySortByPriceLowToHigh() throws InterruptedException
    {
        // Initialize Page Object
        StudyChairPage studyChairPage = new StudyChairPage(driver);

        // Navigate & apply sorting using Page methods
        studyChairPage.navigateToStudyChairs();
        log.info("Navigated to Study Chairs page");

        studyChairPage.sortByPriceLowToHigh();
        log.info("Applied sorting: Price Low to High");

        // VALIDATION: Fetch first 3 prices (handle lazy loading)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> prices;
        int attempts = 0;

        do {
            prices = driver.findElements(
                    By.xpath("//div[contains(@class,'UYQNp')]")
            );
            js.executeScript("window.scrollBy(0, 800);");
            Thread.sleep(1500);
            attempts++;

            log.info(
                    "Lazy loading attempt {} | Prices found: {}",
                    attempts, prices.size()
            );

        } while (prices.size() < 3 && attempts < 5);

        Assert.assertTrue(
                prices.size() >= 3,
                "Less than 3 study chair prices displayed even after scrolling"
        );

        int price1 = convertToInt(prices.get(0).getText());
        int price2 = convertToInt(prices.get(1).getText());
        int price3 = convertToInt(prices.get(2).getText());

        log.info(
                "Fetched prices → {}, {}, {}",
                price1, price2, price3
        );

        // Assert Price Low → High
        Assert.assertTrue(
                price1 <= price2,
                "Price is not sorted Low to High between first and second item"
        );

        Assert.assertTrue(
                price2 <= price3,
                "Price is not sorted Low to High between second and third item"
        );
    }

    private int convertToInt(String priceText) {
        return Integer.parseInt(
                priceText.replace("₹", "")
                        .replace(",", "")
                        .trim()
        );
    }
}