package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookshelvesPage01;

public class TC01_FilterPriceBelow15000 extends BaseTest
{
    @Test
    public void navigateToPriceFilterUsingBookshelvesTile()
    {
        BookshelvesPage01 page = new BookshelvesPage01(driver, prop);
        // calling the navigate method from the bookshelves page:
        page.navigateAndApplyPriceFilter();
        // Using the Assert for checking that it is empty or not:
        Assert.assertFalse(
                driver.getPageSource().isEmpty(),
                "Assertion Failed: Page source is empty after applying price filter"
        );
    }
}