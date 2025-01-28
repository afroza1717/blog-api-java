package org.example.util;


import java.io.File;
import java.util.HashMap;

public class Constants {
	private static HashMap<String, String> roleList = new HashMap();
	public static final String ROLE_REQUEST_SUPERADMIN = "0XSA_ROLE";
	public static final String ROLE_REQUEST_ADMIN = "0XSAD_ROLE";
	public static final String ROLE_REQUEST_CMS_ADMIN = "0XCMS_ROLE";
	public static final String ROLE_REQUEST_VENDOR = "0XVND_ROLE";
	public static final String ROLE_REQUEST_CUSTOMER = "0XCST_ROLE";
	public static final String ROLE_REQUEST_DASHBOARD_ADMIN = "0XDSB_ROLE";
	public static final String ROLE_REQUEST_USER_MANAGER = "0XUM_ROLE";
	static {
		roleList.put(ROLE_REQUEST_SUPERADMIN, "Super Admin");
		roleList.put(ROLE_REQUEST_ADMIN, "Admin");
		roleList.put(ROLE_REQUEST_CMS_ADMIN, "CMS Admin");
		roleList.put(ROLE_REQUEST_VENDOR, "Vendor");
		roleList.put(ROLE_REQUEST_CUSTOMER, "Customer");
		roleList.put(ROLE_REQUEST_DASHBOARD_ADMIN, "Dashboard Admin");
		roleList.put(ROLE_REQUEST_USER_MANAGER, "User Manager");
	}

	public static boolean roleFound(String roleCode) {
		if (roleList.containsKey(roleCode)) {
			return true;
		} else {
			return false;
		}
	}

	public static final String LOOKUP_TYPE_DOCUMENT_ENTITY = "DOCUMENT_ENTITY";
	public static final String LOOKUP_TYPE_DOCUMENT_CATEGORY = "DOCUMENT_CATEGORY";
	public static final String ANONYMOUS_USER = "anonymousUser";
	public static final String HOME_URL = "/home";
	public static final String IS_ACTIVE_YES = "Yes";
	public static final String IS_ACTIVE_NO = "No";
	public static final int IS_ACTIVE_ZERO = 0;
	public static final int IS_ACTIVE_ONE = 1;
	public static final String IS_PUBLIC_YES = "Yes";
	public static final String IS_PUBLIC_NO = "No";
	public static final String IS_SYSTEM_YES = "Yes";
	public static final String IS_SYSTEM_NO = "No";
	public static final String EMPTY = "empty";
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String DOC_SHARE_TO_USER = "User";
	public static final String DOC_SHARE_TO_GROUP = "Group";
	public static final String DOC_SHARE_TO_PUBLIC = "Public";
	public static final String DOC_SHARE_To_EMAIL = "Email";
	public static final String DOC_SHARE_TYPE_DOC = "Document";
	public static final String DOC_SHARE_TYPE_FILE = "File";
	public static final String USER_ICON_URL = "../images/user_icon.png";
//	public static final String COMPANY_LOGO_PATH = FileFolderUtil.getUserHomeDir() + File.separator
//			+ "companyLogo.jpeg";
	public static final String COMPANY_WEB_URL = "www.unicap-securities.com";
	public static final String COMPANY_NOTICE = "www.unicap-securities.com";
	public static final String MSG_RUNTIME_REPLACE_TEXT = "$replaceTxt$";
	public static final String SEND_EMBADED_LOGO_WITH_MAIL = "true";
	public static final String SHARED_LINK_URL = "/authenticate/sharedLink";
	public static final int IS_DELETED_NO = 0;
	public static final String APPLICATION_DATE_FORMAT = "dd-MMM-yyyy";
	public static final String CALENDER_DATE_FORMAT = "dd-MM-yyyy";
	public static final String SERVER_DATE_FORMAT = "yyyy-MM-dd";
	public static final String TYPE_CITY = "City";
	public static final String TYPE_COUNTRY = "Country";
	public static final String TYPE_POLICE_STATION = "Police Station";
	public static final String TYPE_STATE = "State";
	public static final String TYPE_DOCUMENT = "Document";
	public static final String TYPE_DOCUMENT_FILE = "File";
	public static final String TYPE_FOLDER = "Folder";
	public static final String TYPE_STORE = "Store";
	public static final String REPORT_PARAM_TYPE_MENU = "Menu";
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";
	public static final String DRAFT_STATUS = "DRAFT";
	public static final String APPROVED_STATUS = "APPROVED";
	public static final String OBSOLETE_STATUS = "OBSOLETE";
	public static final String USER_IS_DISABLED = "User is disabled";
	public static final String BAD_CREDENTIALS = "Bad credentials";
	public static final String ADMIN = "ADMIN";
	public static final String VENDOR = "VENDOR";
	public static final String CUSTOMER = "CUSTOMER";

   public static final int IS_DELETED_YES = 1;

   public static final String CARD_TYPE_NAME_PERSPECTIVE = "Perspective";

   public static final String CARD_TYPE_NAME_OBJECTIVE = "Objective";

   public static final String CARD_TYPE_NAME_MEASURE = "Measure";
   public static final String SCORE_CARD = "SCORE_CARD";
   public static final String INITIATIVE = "INITIATIVE";
   public static final String STRATEGYMAP = "STRATEGY_MAP";
   public static final String DASHBOARD = "DASHBOARD";
   public static final String REPORT = "REPORT";
   public static final String CHART = "CHART";
   public static final String BRIEFINGS = "BRIEFINGS";
   public static final String FILE = "FILE";
   public static final String WEB_ADDRESS = "WEB_ADDRESS";

}
