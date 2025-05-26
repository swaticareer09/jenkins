package jenkins;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:${env}.properties" })
	public interface Environment extends Config {

		String downloadPath();

		String screenshotpath();

		String reportPath();

		String chrome_driver_path();

		String report_name();

		String familyHeadReport();

		String createTxnClient();

		String familyMemberone();

		String familyMemberTwo();

		String url();

		String username();

		String password();

		String RMUserName();

		String RMpassword();

//	    String clientName();
		String clientportal();

		String bid();

		String bidSecond();

		String secondBrokerClient();

		String BrokerLevelClient();

		String brokerlevelusername();

		String brokerLevelClientPass();

		String ReportingLevelClient();

		String BSESetting_URL();

		String images();

		String SBUserName();

		String SBpassword();

		String GoalClientName();

		String goalClientUserName();

		String goalClientPassword();

		String BackOfficeName();

		String BackOfficeUserName();

		String ServiceRMName();

		String ServiceRMUsername();

		String commonPassword();

		String SubBroker();

		String SubBrokerCode();

		String RManager();

		String FamilyHead();

		String IIN_UAT();

		String UCC_UAT();

		String CAN_UAT();

		String NSE_Investor();

		String BSE_Investor();

		String MFU_Investor();

		String familyUsername();

		String familyPassword();

		String folioNo();

		@Key("db.hosturl")
		String getDBHosturl();

		@Key("db.username")
		String getDBUsername();

		@Key("db.password")
		String getDBPassword();

		// Exchanges:
		// --NSE :
		String nseAppId();

		String nsePwd();

		String nseBrokerCode();

		// --BSE :
		String bseLoginId();

		String bsePwd();

		String bsePassKey();

		// --MFU :
		String mfuLoginId();

		String mfuPwd();

		//suspend user or error validation test user::
		
		String deactivatedUser();
		
		String deactivatedUserName();
		
		String deactivatedUserPwd();
		
	

}
