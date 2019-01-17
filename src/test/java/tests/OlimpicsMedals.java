package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Reports;
import utilities.TestBase;

import java.util.*;

public class OlimpicsMedals extends Reports {
    @Test
    public void rankOrder(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        List<WebElement> rows= driver.findElements(By.xpath(
                "//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[1]"));
        rows.remove(11);rows.remove(10);
        boolean correctOrder= true;
        for (int i = 0; i < rows.size()-1; i++) {
            if(Integer.parseInt(rows.get(i).getText())>=Integer.parseInt(rows.get(i+1).getText())){
                correctOrder=false;
            }
        }
       Assert.assertTrue(correctOrder);
       driver.findElement(By.xpath("//th[.='NOC']")).click();
        List<WebElement> countries= driver.findElements(By.xpath(
                "//caption[.='2016 Summer Olympics medal table']/../tbody//tr//th//img"));
        String[] country= new String[countries.size()];
        for (int i = 0; i <countries.size() ; i++) {
            country[i]= countries.get(i).getAttribute("title");
        }
        String [] countriesByOrder= country;
        Arrays.sort(countriesByOrder);
        Assert.assertEquals(country,countriesByOrder);

        List<WebElement> rowsAfterClickOnNOS= driver.findElements(By.xpath(
                "//caption[.='2016 Summer Olympics medal table']/../tbody//tr//td[1]"));
        rowsAfterClickOnNOS.remove(10);rowsAfterClickOnNOS.remove(7);
        for (int i = 0; i < rowsAfterClickOnNOS.size()-1; i++) {
            if(Integer.parseInt(rowsAfterClickOnNOS.get(i).getText())>=Integer.parseInt(rowsAfterClickOnNOS.get(i+1).getText())){
                correctOrder=false;
            }
        }
        Assert.assertFalse(correctOrder);
    }
    @Test
    public void reports(){
        System.out.println("Greatest number of gold medals: "+ numberOfGoldMedals());
        System.out.println("Greatest number of silver medals: "+ numberOfSilverMedals());
        System.out.println("Greatest number of bronze medals: "+ numberOfBronzeMedals());
        System.out.println("Greatest number of total medals: "+ numberOfTotalMedals());
    }
    public String numberOfGoldMedals(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        generatingReport();
        int max=0;
        int index=0;
        for (int i = 0; i <report.length ; i++) {
            if(Integer.parseInt(report[i][2].getText())>max){
                max=Integer.parseInt(report[i][2].getText());
                index=i;
            }
        }
        return report[index][1].getText();
    }
    public String numberOfSilverMedals(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        generatingReport();
        int max=0;
        int index=0;
        for (int i = 0; i <report.length ; i++) {
            if(Integer.parseInt(report[i][3].getText())>max){
                max=Integer.parseInt(report[i][3].getText());
                index=i;
            }
        }
        return report[index][1].getText();
    }
    public String numberOfBronzeMedals(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        generatingReport();
        int max=0;
        int index=0;
        for (int i = 0; i <report.length ; i++) {
            if(Integer.parseInt(report[i][4].getText())>max){
                max=Integer.parseInt(report[i][4].getText());
                index=i;
            }
        }
        return report[index][1].getText();
    }
    public String numberOfTotalMedals(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        generatingReport();
        int max=0;
        int index=0;
        for (int i = 0; i <report.length ; i++) {
            if(Integer.parseInt(report[i][5].getText())>max){
                max=Integer.parseInt(report[i][5].getText());
                index=i;
            }
        }
        return report[index][1].getText();
    }
    public List<String> countryListBySilverMedal(){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        driver.findElement(By.xpath("//th[.='Silver']")).click();
        driver.findElement(By.xpath("//th[.='Silver']")).click();
        List<String> ret= new ArrayList<String>();
        for (int i = 0; i <10 ; i++) {
            ret.add(driver.findElement(By.xpath("(//caption[.='2016 Summer Olympics medal table']/../tbody//tr//th//img)["+(i+1)+"]")).getAttribute("title"));
        }
        return ret;
    }
    @Test
    public void countriesByMedalType(){
        System.out.println("Country list by silver medal: "+ countryListBySilverMedal());
    }
    @Test
    public void testCordinatesByCountry(){
        String country1=  "South Korea";
        String country2=  "Germany";
        Assert.assertEquals(getCordinatesByCountry(country1),"row: "+ 8+ "col: "+ 2);
        Assert.assertEquals(getCordinatesByCountry(country2),"row: "+ 5+ "col: "+ 2);

    }

    public String getCordinatesByCountry(String country){
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        generatingReport();
        for (int i = 0; i < report.length; i++) {
            if(country.equals(report[i][1].getText())){
                return "row: "+ (i+1)+ "col: "+ 2;
            }
        }
        return "Country is not in list";
    }
    @Test
    public void getListBySumOfBronzeMedals(){
        System.out.println(getListByBronzeMedalCount());
    }
    public Set<String> getListByBronzeMedalCount(){
        Set<String> ret= new HashSet<String>();
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
        generatingReport();
        for (int i = 0; i < report.length; i++) {
            for (int j = 0; j <report.length ; j++) {
                if(i==j){
                    continue;
                }
                if(Integer.parseInt(report[i][4].getText())+Integer.parseInt(report[j][4].getText())==18){
                        ret.add(report[i][1].getText());
                        ret.add(report[j][1].getText());
                }
            }
        }
        return ret;
    }
}
