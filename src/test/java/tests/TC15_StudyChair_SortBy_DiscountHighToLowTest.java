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

public class TC15_StudyChair_SortBy_DiscountHighToLowTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC15_StudyChair_SortBy_DiscountHighToLowTest.class);

    @Test
    public void verifySortByDiscountHighToLow() throws InterruptedException
    {
        // Page object
        StudyChairPage studyChairPage = new StudyChairPage(driver);

        // Navigation & sorting handled by Page methods
        studyChairPage.navigateToStudyChairs();
        log.info("Navigated to Study Chairs page");

        studyChairPage.sortByDiscountHighToLow();
        log.info("Applied sort: Discount High to Low");

        // VALIDATION: fetch first 3 discounts (handle lazy loading)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> discounts;
        int attempts = 0;

        do {
            discounts = driver.findElements(
                    By.xpath("//span[contains(@class,'iR4SB')]")
            );
            js.executeScript("window.scrollBy(0, 800);");
            Thread.sleep(1500);
            attempts++;

            log.info(
                    "Lazy loading attempt {} | Discounts found: {}",
                    attempts, discounts.size()
            );

        } while (discounts.size() < 3 && attempts < 5);

        Assert.assertTrue(
                discounts.size() >= 3,
                "Less than 3 discount values displayed even after scrolling"
        );

        int discount1 = extractDiscount(discounts.get(0).getText());
        int discount2 = extractDiscount(discounts.get(1).getText());
        int discount3 = extractDiscount(discounts.get(2).getText());

        log.info(
                "Fetched discounts → {}%, {}%, {}%",
                discount1, discount2, discount3
        );

        // Assert Discount High → Low
        Assert.assertTrue(
                discount1 >= discount2,
                "Discount is not sorted High to Low between first and second item"
        );

        Assert.assertTrue(
                discount2 >= discount3,
                "Discount is not sorted High to Low between second and third item"
        );
    }
    private int extractDiscount(String discountText) {
        return Integer.parseInt(
                discountText.replace("%", "")
                        .replace("OFF", "")
                        .trim()
        );
    }
}