package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.pages.SortableDataTablesPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SortableDataTablesSteps extends Context {

    SortableDataTablesPage sortableDataTablesPage;
    WebDriver driver;

    public SortableDataTablesSteps(Manager manager) {
        super(manager);
        this.driver = manager.getDriver();
        this.sortableDataTablesPage = new SortableDataTablesPage(manager.getDriver());
    }

    @And("example 1 Table is displayed")
    public void tableVisible() {
        sortableDataTablesPage.verifyTableIsVisible();
    }

    @Then("the table should display:")
    public void validateTableData(DataTable expectedTable) {
        List<List<String>> expectedData = expectedTable.asLists(String.class);
        List<List<String>> actualData = sortableDataTablesPage.getAllTableData();

        List<String> validationErrors = new ArrayList<>();

        if (expectedData.size() != actualData.size()) {
            sortableDataTablesPage.verifyRowCountMatches(expectedData.size(), actualData.size());
        }

        int rowsToCompare = Math.min(expectedData.size(), actualData.size());

        for(int rowIndex = 0; rowIndex < rowsToCompare; rowIndex++) {
            List<String> expectedRow = expectedData.get(rowIndex);
            List<String> actualRow = actualData.get(rowIndex);

            if (!expectedRow.equals(actualRow)) {
                int colsToCompare = Math.min(expectedRow.size(), actualRow.size());

                for (int colIndex = 0; colIndex < colsToCompare; colIndex++) {
                    String expectedCell = expectedRow.get(colIndex);
                    String actualCell = actualRow.get(colIndex);

                    if (!expectedCell.equals(actualCell)) {
                        validationErrors.add(String.format("Mismatch at Row %d, Column %d -> Expected: '%s', Actual: '%s'",
                                (rowIndex + 1), (colIndex + 1), expectedCell, actualCell));
                    }
                }
            }
        }

        if (!validationErrors.isEmpty()) {
            // Join all the errors with a new line for a clean console output
            String finalErrorMessage = "\n--- TABLE VALIDATION FAILED ---\n" +
                    String.join("\n", validationErrors) +
                    "\n-------------------------------\n";

            // Fail the test and print the beautifully formatted report
            Assertions.fail(finalErrorMessage);
        }
    }
}
