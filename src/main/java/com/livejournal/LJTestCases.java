package com.livejournal;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

/*Tast-cases examples with Selenium.WebDriver for https://livejournal.com/
Tast-cases: https://docs.google.com/spreadsheets/d/1elsTaHw3nO0mBjGof8QS9u0otKWrsztL6VsFVLLrOWE/edit?usp=sharing*/
public class LJTestCases {
    public static void main( String[] args ) {

        //1.Страница профиля пользователя после входа
        //userAuth();

        //2.Публикация поста авторизованным пользователем
        postText();

        //3.Изменение поста
        updatePost();

        //4.Удаление поста
        deletePost();

    }
    public static void userAuth(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String usN = "user03102023";
        String usPass = "xyZ123#";
        driver.get("https://livejournal.com/");
        driver.findElement(By.xpath("//a[.='Войти']")).click();
        WebElement user = driver.findElement(By.id("user"));
        user.sendKeys(usN);
        WebElement pass = driver.findElement(By.id("lj_loginwidget_password"));
        pass.sendKeys(usPass);
        driver.findElement(By.xpath("//form[@action='https://www.livejournal.com/login.bml?ret=1']//button")).click();
        WebElement postButtn = driver.findElement(By.xpath("//header//span[@class='s-header-item-post--short']"));
        driver.close();
    }
    public static void postText() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String usN = "user03102023";
        String usPass = "xyZ123#";
        driver.get("https://livejournal.com/");
        driver.findElement(By.xpath("//a[.='Войти']")).click();
        WebElement user = driver.findElement(By.id("user"));
        user.sendKeys(usN);
        WebElement pass = driver.findElement(By.id("lj_loginwidget_password"));
        pass.sendKeys(usPass);
        driver.findElement(By.xpath("//form[@action='https://www.livejournal.com/login.bml?ret=1']//button")).click();
        WebElement postButtn = driver.findElement(By.xpath("//header//span[@class='s-header-item-post--short']"));
        driver.findElement(By.xpath("//span[contains(text(), 'Написать')][2]")).click();
//        if(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed()){
//            driver.findElement(By.xpath("//span[.='Нет, спасибо']/ancestor::button")).click();
//        }

        String title = "test"+ new Random().nextInt(100);
        String text= "some text" + new Random().nextInt(100);
        driver.findElement(By.xpath("//textarea[@placeholder = 'Придумайте заголовок']")).sendKeys(title);
        driver.findElement(By.xpath("//span[@data-offset-key]")).sendKeys(text);
        driver.findElement(By.xpath("//span[.='Виден всем']/ancestor::button")).click();
        driver.findElement(By.xpath("//h4[.='Кто увидит пост?']/parent::div/ul/li[4]")).click();
        driver.findElement(By.xpath("//span[.='Настроить и опубликовать']/parent::button")).click();
        driver.findElement(By.xpath("//span[.='Настроить и опубликовать']/parent::button/following-sibling::div/footer//button")).click();


        driver.close();
    }
    public static void updatePost() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String usN = "user03102023";
        String usPass = "xyZ123#";
        driver.get("https://livejournal.com/");
        driver.findElement(By.xpath("//a[.='Войти']")).click();
        WebElement user = driver.findElement(By.id("user"));
        user.sendKeys(usN);
        WebElement pass = driver.findElement(By.id("lj_loginwidget_password"));
        pass.sendKeys(usPass);
        driver.findElement(By.xpath("//form[@action='https://www.livejournal.com/login.bml?ret=1']//button")).click();
        WebElement postButtn = driver.findElement(By.xpath("//header//span[@class='s-header-item-post--short']"));
        Actions actions = new Actions(driver);
        WebElement userIcon = driver.findElement(By.xpath("//nav[@role='navigation']/following-sibling::nav/ul/li/a[contains(@title,'user')]"));
        WebElement menuPoint = driver.findElement(By.xpath("//a[contains(@href, 'post/list')]"));
        actions.moveToElement(userIcon);
        actions.perform();
        menuPoint.click();
        driver.findElement(By.xpath("//button[contains(text(), 'Опубликованные записи')]")).click();
        List<WebElement> articles = driver.findElements(By.xpath("//div[@id='posted']//article//a"));
        articles.stream().findFirst().get().click();
        driver.findElement(By.xpath("//div[@class='aentry-head']//button")).click();
        driver.findElement(By.xpath("//div[@class='aentry-head']//button/following-sibling::div//span[.='Редактировать запись']/parent::a")).click();
        actions.sendKeys("updated").perform();
        driver.findElement(By.xpath("//span[.='Настроить и обновить']/parent::button")).click();
        driver.findElement(By.xpath("//span[.='Настроить и обновить']/parent::button/following-sibling::div/footer//button")).click();
        driver.close();
    }
    public static void deletePost() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String usN = "user03102023";
        String usPass = "xyZ123#";
        driver.get("https://livejournal.com/");
        driver.findElement(By.xpath("//a[.='Войти']")).click();
        WebElement user = driver.findElement(By.id("user"));
        user.sendKeys(usN);
        WebElement pass = driver.findElement(By.id("lj_loginwidget_password"));
        pass.sendKeys(usPass);
        driver.findElement(By.xpath("//form[@action='https://www.livejournal.com/login.bml?ret=1']//button")).click();
        WebElement postButtn = driver.findElement(By.xpath("//header//span[@class='s-header-item-post--short']"));
        Actions actions = new Actions(driver);
        WebElement userIcon = driver.findElement(By.xpath("//nav[@role='navigation']/following-sibling::nav/ul/li/a[contains(@title,'user')]"));
        WebElement menuPoint = driver.findElement(By.xpath("//a[contains(@href, 'post/list')]"));
        actions.moveToElement(userIcon);
        actions.perform();
        menuPoint.click();
        driver.findElement(By.xpath("//button[contains(text(), 'Опубликованные записи')]")).click();
        List<WebElement> articles = driver.findElements(By.xpath("//div[@id='posted']//article//a"));
        articles.stream().findFirst().get().click();
        driver.findElement(By.xpath("//div[@class='aentry-head']//button")).click();
        driver.findElement(By.xpath("//div[@class='aentry-head']//button/following-sibling::div//span[.='Редактировать запись']/parent::a")).click();
        driver.findElement(By.xpath("//a[.='Удалить пост']")).click();
        driver.findElement(By.xpath("//span[.='Удалить']/parent::button")).click();

        driver.quit();

    }
}
