package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookshelvesPage01;

public class TC03_ExcludeOutOfStockItems extends BaseTest
{
    @Test
    public void excludeOutOfStockBookshelves()
    {
        BookshelvesPage01 page = new BookshelvesPage01(driver, prop);
        // Home → Bookshelves → All Filter → Exclude Out Of Stock
        page.navigateToOutOfStockPageAndPrintProducts();
        // Assertion: Page should load successfully after excluding out-of-stock items
        Assert.assertFalse(
                driver.getPageSource().isEmpty(),
                "Assertion Failed: Page did not load properly after excluding out-of-stock items"
        );
    }
}