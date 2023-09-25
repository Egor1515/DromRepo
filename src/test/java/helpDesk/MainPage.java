package helpDesk;

import core.SeleniumPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPage extends SeleniumPage {
    private FavoritePage favorite;
    private int lastElementIndex = 0;

    @FindBy(css = ".css-75hx9m > .css-tfkmus")
    private WebElement brandInput;
    @FindBy(css = "div[class=\"css-1r0zrug e1uu17r80\"]")
    private List<WebElement> brandDropDown;
    @FindBy(css = "div[class=\"css-1r0zrug e1uu17r80\"] div")
    private List<WebElement> brandDropDownText;
    @FindBy(css = "input.css-tfkmus  ")
    private WebElement modelInput;
    @FindBy(css = "div[class=\"css-1r0zrug e1uu17r80\"]")
    private List<WebElement> modelDropDown;
    @FindBy(css = "[data-ftid=\"sales__filter_fuel-type\"]")
    private WebElement engineTypeInput;
    @FindBy(css = "div.css-u25ii9.e154wmfa0 div.css-17vx1of.e1x0dvi10")
    private List<WebElement> engineTypeDropDown;
    @FindBy(css = "[data-ga-stats-name=\"auth_block_login\"]")
    private WebElement authBtn;
    @FindBy(css = "div[class=\"css-pivpd8 e13r0v7w0\"] svg path")
    private WebElement addFavoriteBtn;
    @FindBy(css = "[data-ftid=\"sales__filter_mileage-from\"]")
    private WebElement milageFrom;
    @FindBy(css = "[data-ftid=\"sales__filter_mileage-to\"]")
    private WebElement milageTo;
    @FindBy(css = "[data-ftid=\"sales__filter_submit-button\"]")
    private WebElement searchBtn;
    @FindBy(css = "label[class=\"css-1tikdro eiy4qr62\"][for=\"sales__filter_unsold\"]")
    private WebElement checkUnsold;
    @FindBy(css = "[aria-label=\"Год от\"] > .css-75hx9m > .css-me79aa")
    private WebElement yearFromBtn;
    @FindBy(css = "[data-ftid=\"component_select_dropdown\"][class=\"css-u25ii9 e154wmfa0\"] div")
    private List<WebElement> yearDropDown;
    @FindBy(css = "[data-ftid=\"sales__filter_advanced-button\"]")
    private WebElement extendedSearchBtn;
    @FindBy(css = "span[data-ftid=\"bull_title\"]")
    private List<WebElement> carBullTitle;
    @FindBy(css = "[data-ftid=\"component_pagination-item-next\"]")
    private WebElement nextPageBtn;
    @FindBy(css = "[data-ftid=\"component_inline-bull-description\"] > span:last-child")
    private List<WebElement> carCardInfo;
    @FindBy(css = "[data-ftid=\"bull_label_new-unofficial\"]")
    private WebElement newCarStatus;
    @FindBy(css = "[data-ftid=\"bulls-list_bull\"]:first-child [data-ftid=\"bull_title\"]")
    private WebElement choseFirstCarCardTitle;
    @FindBy(css = "div[id=\"bulletinId\"] .bull-item-content__description [class=\"bulletinLink bull-item__self-link auto-shy\"] ")
    private WebElement isFavoritePresent;
    @FindBy(css = "#actual")
    private WebElement btnAllCarsFavorite;

    public MainPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    public void sortSome() {
        List<WebElement> carElements = new ArrayList<>();
        // Напишите код для нахождения и сохранения каждого элемента carElements (100 элементов)

        List<CarInfo> carInfoList = new ArrayList<>();

        for (WebElement carElement : carElements) {
            String text = carElement.getText();
            String[] parts = text.split(" ");
            String company = parts[0];
            int count = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
            carInfoList.add(new CarInfo(company, count));
        }

        // Сортировка записей в порядке убывания "count"
        Collections.sort(carInfoList, Comparator.comparingInt(CarInfo::getCount).reversed());

        // Выбор первых 20 записей
        List<CarInfo> top20 = carInfoList.subList(0, 20);

        // Вывод результатов
        System.out.println("| Фирма | Количество объявлений |");
        System.out.println("|-------|------------------------|");
        for (CarInfo carInfo : top20) {
            System.out.println("| " + carInfo.getCompany() + " | " + carInfo.getCount() + " |");
        }
    }

    public void selectBrand(String brand) throws InterruptedException {
        brandInput.sendKeys(brand, Keys.SPACE);
        Thread.sleep(1000);
        for (WebElement element : brandDropDown) {
            if (element.getText().contains(brand)) {
                element.click();
            }
        }
    }

    public void authUser() {
        authBtn.click();
    }

    public void addToFavorite() throws InterruptedException {
        String getTitleText = choseFirstCarCardTitle.getText();
        addFavoriteBtn.click();
        favorite = new FavoritePage();
        btnAllCarsFavorite.click();
        String expected = isFavoritePresent.getText();
        Assert.assertEquals(expected, getTitleText);
    }

    public void selectModel(String model) throws InterruptedException {
        Thread.sleep(1000);
        modelInput.sendKeys(model, Keys.SPACE);
        Thread.sleep(1000);
        for (WebElement element : modelDropDown) {
            if (element.getText().contains(model)) {
                element.click();
            }
        }
    }

    public void selectTypeEngine(String engineType) {
        engineTypeInput.click();
        for (WebElement webElement : engineTypeDropDown) {
            if (webElement.getText().contains(engineType)) {
                webElement.click();
            }
        }
    }

    public void setYearFrom(String yearFrom) {
        yearFromBtn.click();
        for (WebElement webElement : yearDropDown) {
            if (webElement.getText().contains(yearFrom)) {
                webElement.click();
            }
        }
    }

    public void checkFavoriteCar() {
        driver.get("https://my.drom.ru/personal/bookmark");

    }

    public void checkUnsoldCars() {
        checkUnsold.click();
    }

    public void setMilageFrom(String milageAmounFrom) {
        milageFrom.sendKeys(milageAmounFrom);
    }

    public void setMilageTo(String milageAmounTo) {
        milageFrom.sendKeys(milageAmounTo);
    }

    public void extendedSearch() {
        extendedSearchBtn.click();
    }

    public void searchForResult() {
        searchBtn.click();
    }

    public void isSold() {
        for (WebElement carTitle : carBullTitle) {
            if (!carTitle.getCssValue("text-decoration").contains("line-through")) {
                continue;
            } else {
                throw new RuntimeException("Найден проданный автомобиль");
            }
        }
    }

    public void nextPage() {
        nextPageBtn.click();
    }

    public void checkMilageExist() {
        for (WebElement element : carCardInfo) {
            String text = element.getText();
            Pattern pattern = Pattern.compile("\\d+ км");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                continue;
            } else {
                throw new RuntimeException("Текст не соответствует ожидаемому формату: " + text);
            }
        }
    }

    public void isProdYearPresent(int minYear) {
        for (WebElement element : carBullTitle) {
            String spanText = element.getText();
            Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
            Matcher matcher = pattern.matcher(spanText);

            boolean yearFound = false;

            while (matcher.find()) {
                String year = matcher.group();
                int yearInt = Integer.parseInt(year);

                if (yearInt >= minYear) {
                    yearFound = true;
                    break;
                }
            }
            if (!yearFound) {
                throw new RuntimeException("Год не отображается или меньше " + minYear + " в тексте: " + spanText);
            }
        }
    }

    public void addToList() throws InterruptedException {
        brandInput.click();
        List<BrandInfo> brandInfoList = new ArrayList<>();
        Pattern pattern = Pattern.compile("^(?!Любая марка).*\\(\\d+\\)$");
        for (WebElement element : brandDropDownText) {
            String getText = element.getText();
            Matcher matcher = pattern.matcher(getText);
            if (matcher.matches()) {
                String[] parts = getText.split(" \\(");
                String firm = parts[0].trim();
                int count = Integer.parseInt(parts[1].replaceAll("\\)", "").trim());

                BrandInfo brandInfo = new BrandInfo(firm, count);
                brandInfoList.add(brandInfo);
            }
        }
        Actions actions = new Actions(driver);
        WebElement el = driver.findElement(By.cssSelector("[class=\"css-1r0zrug e1uu17r80\"]:last-child"));
        actions.moveToElement(el).moveByOffset(0, -3).perform();
        for (WebElement element : brandDropDownText) {
            String getText = element.getText();
            Matcher matcher = pattern.matcher(getText);
            if (matcher.matches()) {
                String[] parts = getText.split(" \\(");
                String firm = parts[0].trim();
                int count = Integer.parseInt(parts[1].replaceAll("\\)", "").trim());

                BrandInfo brandInfo = new BrandInfo(firm, count);
                brandInfoList.add(brandInfo);
            }
        }
        int currentPxPosition = 0;
        int newPxPosition = 1;
        while (currentPxPosition != newPxPosition) {

            for (WebElement element : brandDropDownText) {
                String getText = element.getText();
                Matcher matcher = pattern.matcher(getText);
                if (matcher.matches()) {
                    String[] parts = getText.split(" \\(");
                    String firm = parts[0].trim();
                    int count = Integer.parseInt(parts[1].replaceAll("\\)", "").trim());

                    BrandInfo brandInfo = new BrandInfo(firm, count);
                    brandInfoList.add(brandInfo);
                    currentPxPosition = newPxPosition;
                    newPxPosition = scrollList();
                }
            }
        }
        brandInfoList.sort(Comparator.comparingInt(BrandInfo::getCount).reversed());


        ArrayList<String> brandList = new ArrayList<>();
        for (BrandInfo brandInfo : brandInfoList) {
            String resultItem = "| " + brandInfo.getFirm() + " | " + brandInfo.getCount();
            brandList.add(resultItem);
        }

        System.out.println("| Фирма | Количество объявлений | ");
        for (String s : brandList) {
            System.out.println(s);
        }

    }

    static class BrandInfo {
        private final String firm;
        private final int count;

        public BrandInfo(String firm, int count) {
            this.firm = firm;
            this.count = count;
        }

        public String getFirm() {
            return firm;
        }

        public int getCount() {
            return count;
        }
    }

    public int scrollList() {
        WebElement element = driver.findElement(By.cssSelector("[class=\"css-5iu465 ewaf0l10\"] [class=\"css-1r0zrug e1uu17r80\"]:last-child"));
        String styleAttributeValue = element.getAttribute("style");
        String transformValue = extractTransformValue(styleAttributeValue);
        return extractTranslateYValue(transformValue);

    }

    private String extractTransformValue(String styleAttributeValue) {
        Pattern pattern = Pattern.compile("transform:\\s*translateY\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(styleAttributeValue);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    private int extractTranslateYValue(String transformValue) {
        try {
            return Integer.parseInt(transformValue.replaceAll("[^0-9-]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
