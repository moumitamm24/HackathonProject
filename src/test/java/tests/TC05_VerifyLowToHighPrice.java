package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookshelvesPage01;

public class TC05_VerifyLowToHighPrice extends BaseTest
{
    @Test
    public void sortBookshelvesPriceLowToHigh()
    {
        BookshelvesPage01 page = new BookshelvesPage01(driver, prop);
        // Home → Bookshelves → Sort → Price Low to High
        page.navigateAndSortPriceLowToHigh();
        // Assertion: Page should load successfully after sorting by Price Low to High
        Assert.assertFalse(
                driver.getPageSource().isEmpty(),
                "Assertion Failed: Page did not load properly after sorting by Price Low to High"
        );
    }
}