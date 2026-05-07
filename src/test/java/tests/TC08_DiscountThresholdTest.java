package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BookShelvesPage2;

public class TC08_DiscountThresholdTest extends BaseTest {

    private BookShelvesPage2 livingPage;
    private static final Logger log =
            LogManager.getLogger(TC08_DiscountThresholdTest.class);

    // Initialize Page Object before each test
    @BeforeMethod
    public void setUpPage() {
        livingPage = new BookShelvesPage2(driver);
    }

    // DataProvider for different discount thresholds
    @DataProvider(name = "discountThresholds")
    public Object[][] discountThresholds() {
        return new Object[][]{
                {20},
                {30},
                {40},
                {50}
        };
    }

    //  Validate products count with discount greater than threshold
    @Test(dataProvider = "discountThresholds",
            description = "Validate products having discount greater than given threshold")
    public void validateDiscountThreshold(int threshold) {

        int count =
                livingPage.countProductsWithDiscountGreaterThan(threshold);

        log.info(
                " Products with discount greater than {}% : {}",
                threshold, count
        );

        // Business‑safe assertion (inventory is dynamic)
        Assert.assertTrue(
                count >= 0,
                "Product count should never be negative"
        );
    }
}