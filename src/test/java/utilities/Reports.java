package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class Reports extends TestBase{

    protected WebElement[][] report= new WebElement[10][6];

    public void generatingReport(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        List<WebElement> rowsRanks= driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[1]"));
        rowsRanks.remove(10);rowsRanks.remove(10);
        List<WebElement> rowsCountries= driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']/../tbody//tr//a"));
        rowsCountries.remove(10);
        List<WebElement> rowsGolds= driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[2]"));
        rowsGolds.remove(10);rowsGolds.remove(10);
        List<WebElement> rowsSilvers= driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[3]"));
        rowsSilvers.remove(10);rowsSilvers.remove(10);
        List<WebElement> rowsBronzes= driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[4]"));
        rowsBronzes.remove(10);rowsBronzes.remove(10);
        List<WebElement> rowsTotal= driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[5]"));
        rowsTotal.remove(10);

        for (int i = 0; i <report.length ;i++) {

            report[i][0]= rowsRanks.get(i);
            report[i][1]= rowsCountries.get(i);
            report[i][2]= rowsGolds.get(i);
            report[i][3]= rowsSilvers.get(i);
            report[i][4]= rowsBronzes.get(i);
            report[i][5]= rowsTotal.get(i);
        }


    }

}
