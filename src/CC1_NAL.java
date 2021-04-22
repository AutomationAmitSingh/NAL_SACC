import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CC1_NAL {

    static WebDriver driver;

    public static void main (String [] args) throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.worldometers.info/world-population/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String xpath_current_pop = "//div[@class='maincounter-number']/span[@class='rts-counter']";
        String xpath_today_pop = "//div[text()='Today']//parent::div//span[@class='rts-counter']";
        String xpath_this_year_pop = "//div[text()='This year']//parent::div//span[@class='rts-counter']";
        String xpath_today_this_Year_pop = "//div[text()='This year' or text()='Today']//parent::div//span[@class='rts-counter']";

        int count = 1;
        while(count<=20) {
            System.out.println(count + " sec");
            if(count == 21) break;

            System.out.println("---------current population count-----------");
            printPopulationData(xpath_current_pop);

            System.out.println("---------Today and This Year population count-----------");
            printPopulationData(xpath_today_this_Year_pop);
            System.out.println("==================================================");
            Thread.sleep(1000);
            count++;

        }
        driver.quit();
    }

    public static void printPopulationData(String locator) throws InterruptedException {
//			List<WebElement> popList = driver
//					.findElements(By.xpath(locator));
//			for (WebElement e : popList) {
//				System.out.println(e.getText());
//			}

        driver
                .findElements(By.xpath(locator))
                .stream()
                .forEach(e -> System.out.println(e.getText()));

    }

}
