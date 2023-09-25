package helpDesk;

import core.SeleniumTest;
import org.junit.Test;

public class DromJobTest extends SeleniumTest {
    private MainPage page;
    private AuthorizationPage auth;

    @Test
    public void dromHarrierTest() throws InterruptedException {
        page = new MainPage();
        String expectedYear = "2007";
        int yearToCheck = Integer.parseInt(expectedYear);

        page.selectBrand("Toyota");
        page.selectModel("Harrier");
        page.selectTypeEngine("Гибрид");
        page.checkUnsoldCars();

        page.extendedSearch();
        page.setYearFrom(expectedYear);
        page.setMilageFrom("1");
        page.searchForResult();
        page.isSold();
        page.isProdYearPresent(yearToCheck);
        page.checkMilageExist();

        page.nextPage();
        page.isSold();
        page.isProdYearPresent(yearToCheck);
        page.checkMilageExist();
    }

    @Test
    public void addToFavorite() throws InterruptedException {
        page = new MainPage();
        page.authUser();
        auth = new AuthorizationPage();
        auth.authWithLoginAndPass("89510292164", "Q2640092q");
        page = new MainPage();
        page.addToFavorite();
    }

    @Test
    public void getListPrinted() throws InterruptedException {
        page = new MainPage();
        page.addToList();
    }


}
