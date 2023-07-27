import com.github.javafaker.Faker;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BaseClass<T>{
    protected abstract T createPageInstance();
    protected T page;
    protected static Properties properties;
    protected Faker faker;
    protected static final Logger LOGGER = Logger.getLogger(MainPageTest.class.getName());
    protected String url;

    public BaseClass(String url) {
        this.url = url;
    }

    @BeforeClass
    public void setUp() {
        LOGGER.info("Test class is started");
        faker = Faker.instance();
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpEach() {
        System.setProperty("selenide.browser", "Chrome");
        open(properties.getProperty(url));
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        getWebDriver().manage().window().maximize();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        page = createPageInstance();
    }

    @AfterClass
    public void tearDown() {
        closeWebDriver();
        LOGGER.info("Test class is finished");
    }
}
