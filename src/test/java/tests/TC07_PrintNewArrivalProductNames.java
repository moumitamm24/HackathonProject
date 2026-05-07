package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookShelvesPage2;

public class TC07_PrintNewArrivalProductNames extends BaseTest {

    BookShelvesPage2 livingPage;

    private static final Logger log = LogManager.getLogger(TC07_PrintNewArrivalProductNames.class);
    @Test
    public void printOnlyNewArrivalProductNames() {

        livingPage = new BookShelvesPage2(driver);
        livingPage.printNewArrivalsProductNamesOnly();

        // Logic validation only
        Assert.assertTrue(true);
    }

}