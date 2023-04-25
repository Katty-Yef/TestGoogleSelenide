import org.googlenotauthorized.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseClass<T>{

    protected T page;
    protected static final Logger LOGGER = Logger.getLogger(MainPageTest.class.getName());
    protected String urlToOpen;

    BaseClass(String url){
        urlToOpen = url;
    }

    @BeforeClass
    public void setUp(){
        LOGGER.info("Test class is started");
    }
    @BeforeMethod
    public void setUpEach(){
        System.setProperty("selenide.browser", "Chrome");
        open(urlToOpen);
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        getWebDriver().manage().window().maximize();

        page = new T();
    }

    @AfterClass
    public void tearDown(){
        LOGGER.info("Test class is finished");
    }
}
