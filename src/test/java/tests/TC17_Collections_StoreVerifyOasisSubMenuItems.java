package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.BaseTest;
import pages.CollectionsPage;
import utils.ExcelUtil;

import java.util.List;

public class TC17_Collections_StoreVerifyOasisSubMenuItems extends BaseTest
{
    private static final Logger log =
            LogManager.getLogger(TC17_Collections_StoreVerifyOasisSubMenuItems.class);

    @Test
    public void retrieveSubMenuItems()
    {
        //Store and verify Oasis submenu items
        CollectionsPage cp = new CollectionsPage(driver);

        //Hovering on New Arrivals menu
        cp.hoverOnNewArrivalsMenu();

        boolean isEmpty = cp.itemsDiv.isEmpty();
        log.info("Is Oasis submenu list empty? : {}", isEmpty);

        //Printing values..
        cp.printSubMenuItems();

        Assert.assertFalse(
                isEmpty,
                "Oasis submenu items list is EMPTY"
        );

        //  Get submenu data from page
        List<String[]> subMenuData = cp.getOasisSubMenuData();

        //  Excel file path
        String filePath = System.getProperty("user.dir")
                + "/test-output/results/Oasis_SubMenu_Items.xlsx";

        // Write to Excel
        ExcelUtil.writeToExcel(
                filePath,
                "Oasis Sub Menu",
                subMenuData
        );
        log.info("Oasis submenu items written to Excel at: \n{}", filePath);
    }
}