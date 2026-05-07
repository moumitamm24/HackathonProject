package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.CollectionsPage;
import utils.ExcelUtil;
import java.util.List;

public class TC18_Collections_CheckBedroomInOasisCollection extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC18_Collections_CheckBedroomInOasisCollection.class);

    @Test
    public void verifyCollectionsMenuVisibilityOnHover() {

        // Starting TC20: Verify Bedroom page in Oasis Collection
        CollectionsPage cp = new CollectionsPage(driver);

        // Hovering on New Arrivals menu
        cp.hoverOnNewArrivalsMenu();

        // Clicking on Bedroom under Oasis Collection
        cp.ClickOnBedroomRoomOasisCollection();

        // Applying Filter for NON-STORAGE beds
        cp.clickOnBedsStorage();
        cp.clickNonStorage();

        // Print Result :
        cp.printNonStorageBedResults();

        // Get data from page
        List<String[]> bedData = cp.getNonStorageBedData();

        ExcelUtil.writeToExcel(
                System.getProperty("user.dir") + "/test-output/results/NonStorageBeds.xlsx",
                "Oasis Non Storage Beds",
                bedData
        );
        Assert.assertTrue(
                bedData.size() > 1,
                "No beds are displayed after applying Non Storage filter"
        );
    }
}