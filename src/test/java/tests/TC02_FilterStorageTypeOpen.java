package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookshelvesPage01;

public class TC02_FilterStorageTypeOpen extends BaseTest
{
    @Test
    public void filterOpenStorageBookshelves()
    {
        BookshelvesPage01 page = new BookshelvesPage01(driver, prop);
        // Home → Bookshelves → Storage Type → Open
        page.navigateAndFilterOpenStorage();
        // Assertion: Page should be loaded successfully after applying Open Storage filter
        Assert.assertFalse(
                driver.getPageSource().isEmpty(),
                "Assertion Failed: Page did not load properly after applying Open Storage filter"
        );
    }
}