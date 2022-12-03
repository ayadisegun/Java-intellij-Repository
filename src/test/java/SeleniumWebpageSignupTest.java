import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.enterprise.inject.Stereotype;


/***
 You want to test a signup flow for selenium blog
 1. open your chrome browser
 2. input your selenium demo page url ("https://selenium-blog.herokuapp.com")
 3. maximize your browser
 4. click on the sign up button to open the sign uppage
 5. input your username on the username field
 6. input your email in the email field
 7. input your password in the password field
 8. click on the sign up button
 9. click on the user1 iten on the listpage
 10. search for an item (using Python with selenium) and confirm it is present
 11. click on log out
 12. Quit the browser

 from this flow above, the test we can write are  up to 12, but we will limit it to 5.
 write test on the following scnarios
 1. verify that the user inputs the right url and he's on the right webpage
 2. verify that when user clicks on the signup button, the user is directed to the signup page
 3. verify that the user cannot signup with usrname less than 3 characters
 4. verify that the user cannot signup with invalid email address
 5. verify that the user cannot login with password less than or equal to one character
 6. verify that the user cannot sign up with either/all of the fields blank
 7. verify that the user is successfully sign in when valid details are inputed.
 8. verify that user1 item is present on the item list page
 9. verify that the item searched for on the item list page is present
 10 verify that when the user logout, he/she is directed back to the login page
 */


public class SeleniumWebpageSignupTest {
    //import the selenium WebDriver
    private WebDriver driver;

    //create a new method for our setup
    @BeforeTest
    public void setUp() throws InterruptedException {
        //locate where the chrome driver is
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //test 1. open chrome browser
        driver = new ChromeDriver();
        //test2. input your selenium page demo url or any url you want to test
        driver.get("https://selenium-blog.herokuapp.com");
        //(now input a command that will ensure the page waits for the loading of the url inputted)
        Thread.sleep(2000);
        //test 3. maximize window
        driver.manage().window().maximize();
        //test 4. click on the sign up button to open the signup page.
        // to copy element you can get by ID,NAME, or xpath,
        // just inspect the webpage and click the element that speaks to what you wanna use
        // then right click to copy by xpath.

        Thread.sleep(1000);
    }

    @Test (priority = 0)
    public void webpageTest() throws InterruptedException {
        //test1. verify that the user inputs the right url and he's on the right webpage
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com"))
        //pass https://selenium-blog.herokuapp.com/signup
        System.out.println("Correct Webpage");
        else
        System.out.println("Wrong Webpage");
        driver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 1)
    public void signUpPageTest() throws InterruptedException {
        //test1. verify that when user clicks on the signup button, the user is directed to the signup page
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/signup"))
            //pass
            System.out.println("Sign Up Page");
        else
            System.out.println("Wrong Webpage");
        Thread.sleep(2000);
    }

    @Test (priority = 2)
    public void positiveTest() throws InterruptedException {
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys("rantas15");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ranmoita15@mailinator.com");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys("admin");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 3)
    public void clickArticlesTest() throws InterruptedException {
        //test 9 click on the LearnXpath item on the listpage
        driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[1]/a")).click();
        Thread.sleep(1000);
        //Click logout from create response page
        driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[3]/a")).click ();
        Thread.sleep(1000);
    }

    @Test (priority = 4)
    public void confirmBackToLoginPageTest() throws InterruptedException {
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/"))
            //pass
            System.out.println("Back to Login Page");
        else
            //fail
            System.out.println("User not redirected to login page");
        //click sign up button again to get to sign up page for negative tests
        driver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
        Thread.sleep(2000);
    }

    @Test (priority = 5)
    public void invalidEmailTest() throws InterruptedException {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";
        if (driver.getCurrentUrl().equals(signUpPage))
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        else
            driver.navigate().to(signUpPage);
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys("ronkk7");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ronkay@mailinator.com1");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys("admin");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        Thread.sleep(500);
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/users"))
            //pass
            System.out.println("Users cant signup with invalid Email");
        else
            System.out.println("Bug,invalid email has just passed");
        Thread.sleep(500);
    }

    @Test (priority = 6)
    public void passwordLessEqual1Test() throws InterruptedException {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";
        if (driver.getCurrentUrl().equals(signUpPage))
        driver.navigate().refresh();
        else
        driver.navigate().to(signUpPage);
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys("ranta");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ranta@mailinator.com");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys("A");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/users"))
            //pass
            System.out.println("Users cant signup with password less than 1 character");
        else
            System.out.println("Bug,one password character has just passed");
        Thread.sleep(500);
    }

    @Test (priority = 7)
    public void usernameLessThan3Test() throws InterruptedException {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";
        if (driver.getCurrentUrl().equals(signUpPage))
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        else
            driver.navigate().to(signUpPage);
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys("ro");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ranoild@mailinator.com");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys("admin");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/users"))
            //pass
            System.out.println("Users cant signup with username less than 3 character");
        else
            System.out.println("Bug,username less than 3 character has just passed");
        Thread.sleep(500);
    }

    @Test (priority = 8)
    public void usernameBlankTest() throws InterruptedException {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";
        if (driver.getCurrentUrl().equals(signUpPage))
            //Refresh the page to reset all input fields
            driver.navigate().refresh();
        else
            driver.navigate().to(signUpPage);
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys(" ");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ronkayo@mailinator.com");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys("admin");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/users"))
            //pass
            System.out.println("Users cant signup with blank username");
        else
            System.out.println("Bug,blank username signup has just passed");
        Thread.sleep(500);
    }

    @Test (priority = 9)
    public void passwordBlankTest() throws InterruptedException {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";
        if (driver.getCurrentUrl().equals(signUpPage))
            driver.navigate().refresh();
        else
            driver.navigate().to(signUpPage);
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys("ronkola");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ronkay@mailinator.com");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys(" ");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/users"))
            //pass
            System.out.println("Users cant signup with blank Password");
        else
            System.out.println("Bug,blank password signup has just passed");
        Thread.sleep(500);
    }

    @Test (priority = 10)
    public void userPwordBlankTest() throws InterruptedException {
        String signUpPage = "https://selenium-blog.herokuapp.com/signup";
        if (driver.getCurrentUrl().equals(signUpPage))
            driver.navigate().refresh();
        else
            driver.navigate().to(signUpPage);
        //test5. input your username on the username field
        driver.findElement(By.id("user_username")).sendKeys(" ");
        //test6. input your email on the email field
        driver.findElement(By.id("user_email")).sendKeys("Ronkayol@mailinator.com");
        //test7. input your password on the password field
        driver.findElement(By.id("user_password")).sendKeys(" ");
        //test8. click on the sign up button
        driver.findElement(By.id("submit")).click();
        driver.getCurrentUrl();
        if(driver.getCurrentUrl().contains("https://selenium-blog.herokuapp.com/users"))
            //pass
            System.out.println("Users cant signup with blank details");
        else
            System.out.println("Bug,blank details signup has just passed");
        Thread.sleep(500);
    }

    @AfterTest
    public void closeBrowser() {
        //quit the browser
        driver.quit();
    }
}


/**@Test (priority = 4)
public void createNewArticleTest() throws InterruptedException {
//test 9 click on the create new article item on the listpage
driver.findElement(By.xpath("/html/body/div[2]/p/a")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id=\"article_title\"]")).sendKeys("ranta14");
//test6. input your email on the email field
driver.findElement(By.xpath("//*[@id=\"article_description\"]")).sendKeys("update it");
driver.findElement(By.xpath("//*[@id=\"new_article\"]/div[3]/div/input")).click();
Thread.sleep(5000);*/

