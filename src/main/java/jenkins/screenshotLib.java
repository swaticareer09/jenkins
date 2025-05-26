package jenkins;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class screenshotLib extends baselib {

	public static String takescreenshot(String ScriptName) {

//		String timestamp = new SimpleDateFormat("_yyyy_MM_dd__hh_mm_ss").format(new Date());

		String screenshotPath = System.getProperty("user.dir") + "/Screenshot/" + ScriptName + getCurrentDateTime()
				+ ".png";
		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotPath);
		delete_file(1);
		try {
			FileUtils.copyFile(srcFile, destFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotPath;
	}

	public static void delete_file(int daysBack) {
		Environment configENV = ConfigFactory.create(Environment.class);
		File dir = new File(configENV.screenshotpath());
		File[] files = dir.listFiles();
//		Calendar cal = Calendar.getInstance();  
//		 cal.add(Calendar.DAY_OF_MONTH, daysBack * -1);  
//		 long purgeTime = cal.getTimeInMillis(); 
		long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);

		for (File file : files) {
			if (file.exists()) {
				if (file.lastModified() < purgeTime) {
					file.delete();
					if (!file.delete()) {
					}
				}

			}
		}
	}

	public static String getCurrentDateTime() {
		String timestamp = new SimpleDateFormat("_dd_MM_yyyy_hh_mm_ss").format(new Date());
		return timestamp;

	}
}