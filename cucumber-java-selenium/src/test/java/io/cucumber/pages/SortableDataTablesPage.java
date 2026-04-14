package io.cucumber.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SortableDataTablesPage extends Page{

    public SortableDataTablesPage(ChromeDriver driver) {
        super(driver);
        System.out.println("Homepage title is : " + getTitle().getText());
    }

    @FindBy(css = "h3")
    private WebElement title;

    @FindBy(xpath = "//table[@id='table1']")
    private WebElement table1;

    @FindBy(xpath = "//table[@id='table1']//tbody//tr")
    private List<WebElement> tableRows;

    public WebElement getTitle() {
        return title;
    }

    public void verifyTableIsVisible() {
        Assertions.assertTrue(table1.isDisplayed());
    }

    public void verifyRowCountMatches(int expected, int actual) {
        Assertions.assertEquals(expected, actual);
    }

    public List<List<String>> getAllTableData() {
        List<List<String>> tableData = new ArrayList<>();
        List<WebElement> rows = tableRows;

        for(WebElement row : rows) {
            List<String> rowText = new ArrayList<>();
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                rowText.add(cell.getText().trim());
            }
            rowText.removeLast();
            tableData.add(rowText);
        }
        return tableData;
    }
}
