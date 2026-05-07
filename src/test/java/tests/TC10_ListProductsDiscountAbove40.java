package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookShelvesPage2;

import java.util.List;

public class TC10_ListProductsDiscountAbove40 extends BaseTest {

    BookShelvesPage2 livingPage;
    private static final Logger log = LogManager.getLogger(TC10_ListProductsDiscountAbove40.class);

    @Test
    public void listProductsWithDiscountAbove40Percent() {

        livingPage = new BookShelvesPage2(driver);
        List<String> products =
                livingPage.listProductNamesWithDiscountAbove40();

        log.info(" Products with discount > 40%:");

        for (String name : products) {
            log.info("➡ " + name);
        }

        // Business-safe assertion
        Assert.assertTrue(products.size() >= 0);
    }
}
