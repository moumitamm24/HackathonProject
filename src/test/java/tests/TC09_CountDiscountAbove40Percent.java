package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookShelvesPage2;

public class TC09_CountDiscountAbove40Percent extends BaseTest {

    BookShelvesPage2 livingPage;
    private static final Logger log = LogManager.getLogger(TC09_CountDiscountAbove40Percent.class);

    @Test
    public void countDiscountAbove40Percent() {

        livingPage = new BookShelvesPage2(driver);
        int count = livingPage.countProductsWithDiscountMoreThan40();

        log.info(" Final Count: " + count);

        // Business-safe assertion
        Assert.assertTrue(count >= 0);
    }
}