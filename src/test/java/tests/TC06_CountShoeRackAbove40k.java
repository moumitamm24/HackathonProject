package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.BookShelvesPage2;

public class TC06_CountShoeRackAbove40k extends BaseTest {

    BookShelvesPage2 livingPage;

    private static final Logger log = LogManager.getLogger(TC06_CountShoeRackAbove40k.class);
    @Test
    public void countProductsAbove40000() {


        livingPage = new BookShelvesPage2(driver);
        //  Navigate + Sort (High to Low)
        livingPage.navigateAndSortByPriceHighToLow();

        //  Count products above 40000
        int count = livingPage.getProductCountGreaterThanPrice(40000);

        log.info("Products with price greater than 40000: " + count);

        Assert.assertTrue(count >= 0);
    }
}
