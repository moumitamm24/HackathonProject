package tests;

import base.BaseTest;
import pages.StudyChairPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TC12_StudyChair_SortByHighestRecommendationTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC12_StudyChair_SortByHighestRecommendationTest.class);

    @Test
    public void verifySortByHighestRecommendation()
    {
        // Initialize Page Object
        StudyChairPage studyChairPage = new StudyChairPage(driver);

        // Navigation & sorting handled by Page methods
        studyChairPage.navigateToStudyChairs();
        log.info("Navigated to Study Chairs page");

        studyChairPage.sortByPopularity();
        log.info("Sorted study chairs by popularity");

        //  Fetch top 3+ chairs with same price via Page method
        Map<String, List<String>> chairMap =
                studyChairPage.getTopThreeChairsWithSamePrice();

        log.info("Fetched top chairs with same price");

        // VALIDATION: at least 3 chairs captured
        int totalChairs = chairMap.values()
                .stream()
                .mapToInt(List::size)
                .sum();

        Assert.assertTrue(
                totalChairs >= 3,
                "Study chairs are not displayed or sorting by popularity failed"
        );

        log.info("Validation passed: Total chairs captured = {}", totalChairs);

        //  Log chair details (for reporting / demo)
        for (Map.Entry<String, List<String>> entry : chairMap.entrySet()) {
            log.info("Price: {}", entry.getKey());
            for (String chairName : entry.getValue()) {
                log.info("Chair Name: {}", chairName);
            }
        }
    }
}