package jenkins;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class baselib {
	
	public static WebDriver driver;

	public static ExtentReports extent;
	public ExtentTest test;
	public ExtentHtmlReporter reporter;

	
	public static final Logger logger = LogManager.getLogger(baselib.class);


	@Parameters({ "environment" })

	@BeforeTest(alwaysRun = true)


	public void before_test_setup(String environment) throws Exception {
//		PropertyConfigurator.configure(baseLib.class.getClassLoader().getResource("log4j2.properties"));

		String environemnt = System.getProperty("environment");
		ConfigFactory.setProperty("env", environemnt);
		Environment configENV = ConfigFactory.create(Environment.class);

//		System.setProperty("webdriver.chrome.driver", configENV.chrome_driver_path()); no need to use in selenium 4

		ChromeOptions options = new ChromeOptions();

		String chrom = SeleniumManager.getInstance().getDriverPath(options, false).getBrowserPath();
		String chromedrivr = SeleniumManager.getInstance().getDriverPath(options, false).getDriverPath();
		logger.info(chrom + "aaaaaaaaaaaaaaaaaaa" + chromedrivr);
		options.addArguments("--disable-dev-shm-usage"); // overcome limited
		options.addArguments("start-maximized"); // open Browser in maximized
													// mode
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--unsafely-treat-insecure-origin-as-secure=" + configENV.url() + "/");
		HashMap<String, Object> prefs = new HashMap<>();
		prefs.put("plugins.always_open_pdf_externally", true);

		options.setExperimentalOption("prefs", prefs); // To resolve PDF Download cases added plugin to direct download

//		waitStatement.iSleep(Duration.ofSeconds(30));
//		WebDriverManager.chromedriver().clearDriverCache().setup(); not use bcz we r using selenium manager
		driver=new ChromeDriver(options);
		logger.info("Chrome launched");
		getDriver().manage().window().maximize();


		try {
			getDriver().get(configENV.url());

		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginPageElements loginPage = new LoginPageElements();
		try {
			loginPage.login_broker(configENV.username(), configENV.password());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("--------------- Script Running-----------");

	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterTest(groups = "CommonGroup")
	public void after_test_setup() {
		driver.quit();

		logger.info("--------------- Script End-----------");
	}

	@AfterMethod(groups = "CommonGroup")
	public void aftertestsetup(ITestResult result) throws Exception {
		String scriptName = result.getMethod().getMethodName();
		String environemnt = System.getProperty("environment");
		if (environemnt.equalsIgnoreCase("automationAWS") || environemnt.equalsIgnoreCase("automationOne")
				|| environemnt.equalsIgnoreCase("automationTwo") || environemnt.equalsIgnoreCase("automationThree")) {
			if (ITestResult.FAILURE == result.getStatus()) {
				String Failresult = UploadRead.VerifyNoDataAvailable();
				String screenshotPath = screenshotLib.takescreenshot(scriptName);
				String getS3url = UploadRead.upload(screenshotPath);
				ReportIng_preview reportImgPreview = new ReportIng_preview(getS3url);
				String windowOpenHtml = reportImgPreview.getWindowOpenHtml();
				test.fail("This Test Case FAILED: " + Failresult.toUpperCase() + "<br>" + windowOpenHtml);
				test.fail(MarkupHelper.createLabel("Test Case " + scriptName + " Status is FAILED:", ExtentColor.RED));
				logger.info(scriptName + " Script is failed and Screenshot has been taken:");
				test.fail(result.getThrowable());
				String popupScript = reportImgPreview.getPopupScript();
				test.fail(popupScript);

			}

			else if (result.getStatus() == ITestResult.SUCCESS) {

				test.pass(MarkupHelper.createLabel("Test Case " + scriptName + " Status is PASSED", ExtentColor.GREEN));
				String screenshotPath = screenshotLib.takescreenshot(scriptName);
				String getS3url = UploadRead.upload(screenshotPath);
				ReportIng_preview reportImgPreview = new ReportIng_preview(getS3url);
				String windowOpenHtml = reportImgPreview.getWindowOpenHtml();
				test.pass("This Test Case PASSED:" + "<br>" + windowOpenHtml);
				logger.info(scriptName + " Script is passed and Report generated:");
				String popupScript = reportImgPreview.getPopupScript();
				test.pass(popupScript);

			} else if (result.getStatus() == ITestResult.SKIP) {

				test.skip(
						MarkupHelper.createLabel("Test Case " + scriptName + " Status is SKIPPED", ExtentColor.ORANGE));
				String screenshotPath = screenshotLib.takescreenshot(scriptName);
				String getS3url = UploadRead.upload(screenshotPath);
				ReportIng_preview reportImgPreview = new ReportIng_preview(getS3url);
				String windowOpenHtml = reportImgPreview.getWindowOpenHtml();
				test.skip("This Test Case SKIPPED:" + "<br>" + windowOpenHtml);
				logger.info(scriptName + " Script is skipped :");
				test.skip(result.getThrowable());
				String popupScript = reportImgPreview.getPopupScript();
				test.skip(popupScript);

			}
		}

		else {
			if (ITestResult.FAILURE == result.getStatus()) {
				String Failresult = UploadRead.VerifyNoDataAvailable();
				test.fail("This Test Case FAILED: " + Failresult.toUpperCase(), MediaEntityBuilder
						.createScreenCaptureFromPath(screenshotLib.takescreenshot(scriptName)).build());
				test.fail(MarkupHelper.createLabel("Test Case " + scriptName + " Status is FAILED:", ExtentColor.RED));
				logger.info(scriptName + " Script is failed and Screenshot has been taken:");
				test.fail(result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.pass(
						MarkupHelper.createLabel("Test Case " + scriptName + " Status is PASSED:", ExtentColor.GREEN));
				test.pass("This Test Case PASSED:: ", MediaEntityBuilder
						.createScreenCaptureFromPath(screenshotLib.takescreenshot(scriptName)).build());
				logger.info(scriptName + " Script is passed and Report generated:");
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.skip(MarkupHelper.createLabel("Test Case " + scriptName + " Status is SKIPPED:",
						ExtentColor.ORANGE));
				test.skip("This Test Case SKIPED:: ", MediaEntityBuilder
						.createScreenCaptureFromPath(screenshotLib.takescreenshot(scriptName)).build());
				logger.info(scriptName + " Script is skiped :");
				test.skip(result.getThrowable());
			}
		}
       
	}

	@BeforeMethod(groups = "CommonGroup")
	public void beforetestsetup(Method method) {

		if (extent == null) {
			throw new NullPointerException("ExtentReports instance is not initialized");
		}

		String methodname = method.getName();

		test = extent.createTest(methodname);
		logger.info(methodname);

	}

	@BeforeSuite(groups = "CommonGroup")
	public void beforesuitsetup() {
		// String environemnt = System.getProperty("environment");
//		if(environemnt.equalsIgnoreCase("automationProd")){
//		reporter = new ExtentHtmlReporter(
//				System.getProperty("user.dir") + "/Reports/Investwell_" + screenshotLib.getCurrentDateTime() + ".html");
//		}else{
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Investwell.html");
//		}
		reporter.config().setChartVisibilityOnOpen(true);
		reporter.config().setDocumentTitle("Extent Report Demo");
		reporter.config().setReportName("Swati");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Environment", System.getProperty("environment"));
		extent.setSystemInfo("Browser", "Chrome");
	}

	@AfterSuite(groups = "MainGroup")
	public void aftersuitsetup(ITestContext context) {
		extent.flush();
		String environemnt = System.getProperty("environment");
		if (environemnt.equalsIgnoreCase("automationAWS") || environemnt.equalsIgnoreCase("automationOne")
				|| environemnt.equalsIgnoreCase("automationTwo") || environemnt.equalsIgnoreCase("automationThree")) {
			String suiteFileName = context.getCurrentXmlTest().getSuite().getFileName();
			logger.info(suiteFileName);
			String str[] = suiteFileName.split("/");
			String strr = str[6];
			String strnew = strr.replace(".xml", "");
//			emailTemplate.Sending_Report(strnew);
		} else {

		}
	}

}
