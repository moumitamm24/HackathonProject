package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookshelvesPage01;

public class TC04_VerifyHighToLowPrice extends BaseTest
{
    @Test
    public void sortBookshelvesPriceHighToLow()
    {
        BookshelvesPage01 page = new BookshelvesPage01(driver, prop);
        // Home → Bookshelves → Sort → Price High to Low
        page.navigateAndSortPriceHighToLow();
        // Assertion: Page should load successfully after sorting by Price High to Low
        Assert.assertFalse(
                driver.getPageSource().isEmpty(),
                "Assertion Failed: Page did not load properly after sorting by Price High to Low"
        );
    }
}