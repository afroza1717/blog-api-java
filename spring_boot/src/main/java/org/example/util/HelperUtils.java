package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperUtils {
    public static final String IMAGE_UPLOAD_DIRECTORY = "\\wingbangla\\uploads\\wingbangla-ecom\\images\\";
    public static final String SUCCESS_MESSAGE = "data";
    public static final String ERROR_MESSAGE = "error";
    public static final String DATA_TOKEN = "token";
    public static final String ERROR = "error.message";

    private static final Logger logger = LoggerFactory.getLogger(HelperUtils.class);

    public static Map<String, Object> toMap(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        System.out.println(email + " : " + matcher.matches());
        return matcher.matches();
    }

    public static String getRandomString(int length) {
//		byte[] array = new byte[length]; // length is bounded by 7
//		new Random().nextBytes(array);
        // String generatedString = new String(array, Charset.forName("UTF-8"));
        String generatedString = Integer.valueOf(getRandomNumber(111111, 999999)).toString();
        return generatedString;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Object convert(Object oldObject, String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(oldObject, clazz);
    }

    /**
     * Check whether an object is NOT empty, will see if it is a String, Map,
     * Collection, etc.
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Object value) {
        if (value == null)
            return true;

        if (value instanceof String)
            return ((String) value).length() == 0;
        if (value instanceof Collection)
            return ((Collection<? extends Object>) value).size() == 0;
        if (value instanceof Map)
            return ((Map<? extends Object, ? extends Object>) value).size() == 0;
        if (value instanceof CharSequence)
            return ((CharSequence) value).length() == 0;
        if (value instanceof IsEmpty)
            return ((IsEmpty) value).isEmpty();

        // These types would flood the log
        // Number covers: BigDecimal, BigInteger, Byte, Double, Float, Integer, Long,
        // Short
        if (value instanceof Boolean)
            return false;
        if (value instanceof Number)
            return false;
        if (value instanceof Character)
            return false;
        if (value instanceof java.util.Date)
            return false;

        return false;
    }

//   public static HttpHeaders createHttpHeaders() {
//      HttpHeaders headers = new HttpHeaders();
//      try {
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         // String authorizationToken = getAuthorizationToken(user, password);
//
//         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//            .getRequest();
//         HttpSession session = request.getSession();
//         String authorizationToken = (String) session.getAttribute("authorizationToken");
//         headers.add("Authorization", authorizationToken);
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//
//      return headers;
//   }

//   public static String getAuthorizationToken(String user, String password) {
//      RestTemplate restTemplate = new RestTemplate();
//      String apiUrl = ApplicationProperties.INSTANCE.getValueFromPropertiesKey("api.url");
//      String authenticateUrl = apiUrl + "authenticate";
//      String authorizationToken = new String("");
//      HttpHeaders headers = new HttpHeaders();
//
//      // Tasks tasks = restTemplate.getForObject(tasksUrl, Tasks.class);
//      try {
//         // HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//
//         JSONObject personJsonObject = new JSONObject();
//         personJsonObject.put("username", user);
//         personJsonObject.put("password", password);
//
//         HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
//
//         ResponseEntity<LinkedHashMap> responseEntityStr = restTemplate.postForEntity(authenticateUrl, request,
//            LinkedHashMap.class);
//
//         authorizationToken = "Bearer " + responseEntityStr.getBody().get("id_token");
//
//         // JsonNode root = objectMapper.readTree(responseEntityStr.getBody());
//
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//
//      return authorizationToken;
//   }


    /**
     * Check whether IsEmpty o is empty.
     */
    public static boolean isEmpty(IsEmpty o) {
        return o == null || o.isEmpty();
    }

    /**
     * Check whether IsEmpty o is NOT empty.
     */
    public static boolean isNotEmpty(IsEmpty o) {
        return o != null && !o.isEmpty();
    }

    /**
     * Check whether string s is empty.
     */
    public static boolean isEmpty(String s) {
        return (s == null) || s.length() == 0;
    }

    /**
     * Check whether collection c is empty.
     */
    public static <E> boolean isEmpty(Collection<E> c) {
        return (c == null) || c.isEmpty();
    }

    /**
     * Check whether map m is empty.
     */
    public static <K, E> boolean isEmpty(Map<K, E> m) {
        return (m == null) || m.isEmpty();
    }

    /**
     * Check whether charsequence c is empty.
     */
    public static <E> boolean isEmpty(CharSequence c) {
        return (c == null) || c.length() == 0;
    }

    /**
     * Check whether string s is NOT empty.
     */
    public static boolean isNotEmpty(String s) {
        return (s != null) && s.length() > 0;
    }

    /**
     * Check whether collection c is NOT empty.
     */
    public static <E> boolean isNotEmpty(Collection<E> c) {
        return (c != null) && !c.isEmpty();
    }

    /**
     * Check whether charsequence c is NOT empty.
     */
    public static <E> boolean isNotEmpty(CharSequence c) {
        return ((c != null) && (c.length() > 0));
    }

    public static String getRandomColor() {
        String[] letters = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += letters[(int) Math.round(Math.random() * 15)];
        }
        return color;
    }

    public static Timestamp getDateAfterAdditionOfDays(Timestamp ts, Long days) {
        Timestamp dateTSAfterAddition = new Timestamp(ts.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTSAfterAddition);
        cal.add(Calendar.DAY_OF_WEEK, days.intValue());
        dateTSAfterAddition.setTime(cal.getTime().getTime()); // or
        dateTSAfterAddition = new Timestamp(cal.getTime().getTime());
        return dateTSAfterAddition;
    }

    public static Timestamp getDateAfterSubtractionOfDays(Timestamp ts, Long days) {
        Timestamp dateTSAfterSubtraction = new Timestamp(ts.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTSAfterSubtraction);
        cal.add(Calendar.DAY_OF_WEEK, -days.intValue());
        dateTSAfterSubtraction.setTime(cal.getTime().getTime()); // or
        dateTSAfterSubtraction = new Timestamp(cal.getTime().getTime());
        return dateTSAfterSubtraction;
    }

    public static Date getSqlDateAfterAdditionOfDays(Date date, Long days) {
        Date resultSqlDate = null;
        Timestamp dateTSAfterAddition = new Timestamp(date.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTSAfterAddition);
        cal.add(Calendar.DAY_OF_WEEK, days.intValue());
        dateTSAfterAddition.setTime(cal.getTime().getTime()); // or
        dateTSAfterAddition = new Timestamp(cal.getTime().getTime());
        resultSqlDate = getDateFromTimestamp(dateTSAfterAddition);
        return resultSqlDate;
    }

    public static boolean isDateInDateRange(Date fromDate, Date toDate, Date date) {
        return date.after(fromDate) && date.before(toDate);
    }

    public static Date getSqlDateAfterSubtractionOfDays(Date date, Long days) {
        Date resultSqlDate = null;
        Timestamp dateTSAfterSubtraction = new Timestamp(date.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTSAfterSubtraction);
        cal.add(Calendar.DAY_OF_WEEK, -days.intValue());
        dateTSAfterSubtraction.setTime(cal.getTime().getTime()); // or
        dateTSAfterSubtraction = new Timestamp(cal.getTime().getTime());
        resultSqlDate = getDateFromTimestamp(dateTSAfterSubtraction);
        return resultSqlDate;
    }

    public static Timestamp getTimestampAfterAdditionsOfMonths(Timestamp ts, Long months) {
        Timestamp dateTSAfterAddition = new Timestamp(ts.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTSAfterAddition);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + months.intValue()));
        dateTSAfterAddition.setTime(cal.getTime().getTime()); // or
        dateTSAfterAddition = new Timestamp(cal.getTime().getTime());
        return dateTSAfterAddition;
    }

    public static Timestamp getTimestampAfterSubtractionOfMonths(Timestamp ts, Long months) {
        Timestamp dateTSAfterSubtraction = new Timestamp(ts.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTSAfterSubtraction);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - months.intValue()));
        dateTSAfterSubtraction.setTime(cal.getTime().getTime()); // or
        dateTSAfterSubtraction = new Timestamp(cal.getTime().getTime());
        return dateTSAfterSubtraction;
    }

    /*
     * public static void main(String[] args) { java.sql.Timestamp today =
     * getToday(); logger.log(getTimestampAfterAdditionsOfMonths(today,
     * java.lang.Long.valueOf(5)));
     * logger.log(getTimestampAfterAdditionsOfMonths(today,
     * java.lang.Long.valueOf(5))); }
     */
    public static Timestamp getToday() {
        Timestamp today = nowTimestamp();
        Calendar todayCalDate = Calendar.getInstance();

        todayCalDate.set(Calendar.HOUR, 0);
        todayCalDate.set(Calendar.MINUTE, 0);
        todayCalDate.set(Calendar.SECOND, 0);
        todayCalDate.set(Calendar.AM_PM, 0);
        todayCalDate.set(Calendar.MILLISECOND, 0);

        today.setTime(todayCalDate.getTimeInMillis());
        return today;
    }

    public static Timestamp nowTimestamp() {
        return getTimestamp(System.currentTimeMillis());
    }

    /**
     * Convert a millisecond value to a Timestamp.
     *
     * @param time millsecond value
     * @return Timestamp
     */

    public static Timestamp getTimestamp(long time) {
        return new Timestamp(time);
    }

    public static Date getDateFromTimestamp(Timestamp timeStampValue) {
        Date date = new Date(timeStampValue.getTime());

        return date;
    }


    public static Timestamp getTimestampFromDate(Date dateValue) {
        Timestamp date = new Timestamp(dateValue.getTime());
        return date;
    }

    public static Date getDateFromString(String str_date, DateFormat formatter) {
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = null;

        try {
            utilDate = formatter.parse(str_date);
            sqlDate = new Date(utilDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return sqlDate;
    }

    public static Date getDateFromString(String str_date) {
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = null;
        DateFormat formatter = null;
        if (str_date.contains("/")) {
            formatter = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            utilDate = formatter.parse(str_date);
            sqlDate = new Date(utilDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return sqlDate;
    }

    public static String getStringFromDate(Date date, DateFormat formatter) {
        String str_date = "";
        str_date = formatter.format(date);

        return str_date;
    }

    public static List<String> getFirstAndLastDate(int year, int month) {
        List<String> dateLists = new ArrayList<String>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int date = 1;
        int maxDay = 0;
        calendar.set(year, month, date);
        String startDateStr = formatter.format(calendar.getTime());

        // logger.log("First Day: " +
        // formatter.format(calendar.getTime()));

        // Getting Maximum day for Given Month
        maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, maxDay);
        String endDateStr = formatter.format(calendar.getTime());
        // logger.log("Last Day: " +
        // formatter.format(calendar.getTime()));

        dateLists.add(0, startDateStr);
        dateLists.add(1, endDateStr);
        return dateLists;
    }

    public static List<String> getFirstAndLastDateOfYear(int year) {
        List<String> dateLists = new ArrayList<String>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int date = 1;
        int maxDay = 0;
        calendar.set(year, 0, date);
        String startDateStr = formatter.format(calendar.getTime());

        // logger.log("First Day: " +
        // formatter.format(calendar.getTime()));

        // Getting Maximum day for Given Month
        maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, 11, maxDay);
        String endDateStr = formatter.format(calendar.getTime());
        // logger.log("Last Day: " +
        // formatter.format(calendar.getTime()));

        dateLists.add(0, startDateStr);
        dateLists.add(1, endDateStr);
        return dateLists;
    }

    public static List<Timestamp> getFirstAndLastTimestamp(int year, int month) {

        List<Timestamp> dateLists = new ArrayList<Timestamp>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dformatter = new SimpleDateFormat("yyyy-MM-dd");

        calendar.set(year, month, 1);
        String startDateStr = dformatter.format(calendar.getTime());
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, maxDay);
        String endDateStr = dformatter.format(calendar.getTime());

        Timestamp startDate;
        // startDate = UtilDateTime.toTimestamp(java.sql.Date.valueOf(startDateStr));
        startDate = getTimestampFromString(startDateStr);
        Timestamp endDate;
        endDate = getTimestampFromString(endDateStr);
        // endDate = UtilDateTime.toTimestamp(java.sql.Date.valueOf(endDateStr));

        dateLists.add(0, startDate);
        dateLists.add(1, endDate);
        return dateLists;

    }

    public static List<Timestamp> getFirstAndLastTimestamp(int year) {

        List<Timestamp> dateLists = new ArrayList<Timestamp>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dformatter = new SimpleDateFormat("yyyy-MM-dd");

        calendar.set(year, 0, 1);
        String startDateStr = dformatter.format(calendar.getTime());
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, 11, maxDay);
        String endDateStr = dformatter.format(calendar.getTime());

        Timestamp startDate;
        // startDate = UtilDateTime.toTimestamp(java.sql.Date.valueOf(startDateStr));
        startDate = getTimestampFromString(startDateStr);
        Timestamp endDate;
        endDate = getTimestampFromString(endDateStr);
        // endDate = UtilDateTime.toTimestamp(java.sql.Date.valueOf(endDateStr));

        dateLists.add(0, startDate);
        dateLists.add(1, endDate);
        return dateLists;

    }

    public static String getMonthName(int month, int year) {

        String monthName = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MMMMM");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        monthName = formatter.format(calendar.getTime());

        return monthName;

    }

    public static Long getYearLong(Timestamp date) {
        Long year = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = (long) cal.get(Calendar.YEAR);
        return year;
    }


    public static int getMonthWithoutFormat(Timestamp date) {
        long timestamp = date.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return cal.get(Calendar.MONTH);
    }

    public static Timestamp getTimestampFromString(String str_date, DateFormat formatter) {
        if (isNotEmpty(str_date)) {
            try {
                // 31/03/2017 00:00
                // you can change format of date
                java.util.Date date = formatter.parse(str_date.trim());
                Timestamp timeStampDate = new Timestamp(date.getTime());

                return timeStampDate;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

    public static Timestamp getTimestampFromString(String str_date) {
        // 01-Jun-2008
        if (isNotEmpty(str_date)) {
            try {
                SimpleDateFormat formatter = null;
                if (str_date.contains("/")) {
                    formatter = new SimpleDateFormat("dd/MM/yyyy");
                } else {
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                }

                java.util.Date date = formatter.parse(str_date.trim());
                Timestamp timeStampDate = new Timestamp(date.getTime());

                return timeStampDate;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

    public static String getStringFromTimestamp(Timestamp timeStampDate, DateFormat formatter) {
        // you can change format of date
        String str_date = "";
        try {
            if (isNotEmpty(timeStampDate)) {
                Date date = new Date(timeStampDate.getTime());
                str_date = formatter.format(date);
            }
        } catch (Exception e) {
        }

        return str_date;
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static Date convertDateOneFormatToAnother(String dateinString) {
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";

        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        java.util.Date dateOld = null;
        try {
            dateOld = sdf.parse(dateinString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(dateOld);
        Date shipmentDate = Date.valueOf(newDateString.toString());
        return shipmentDate;
    }

    public static Long getCurrentMonth() {
        Integer month = null;
        GregorianCalendar today = new GregorianCalendar();
        month = today.get(Calendar.MONTH);
        return Long.valueOf(month);
    }

    public static Long getCurrentYear() {
        Integer year = null;
        GregorianCalendar today = new GregorianCalendar();
        year = today.get(Calendar.YEAR);
        return Long.valueOf(year);
    }

    public static Date getTodayDate() {
        java.util.Date today = new java.util.Date();
        Calendar todayCalDate = Calendar.getInstance();

        todayCalDate.set(Calendar.HOUR, 0);
        todayCalDate.set(Calendar.MINUTE, 0);
        todayCalDate.set(Calendar.SECOND, 0);
        todayCalDate.set(Calendar.AM_PM, 0);
        todayCalDate.set(Calendar.MILLISECOND, 0);

        today.setTime(todayCalDate.getTimeInMillis());
        Date sqlDate = new Date(today.getTime());

        return sqlDate;
    }
    /*
     * public static Object[] getArrayFromJsonString(String str){ Object[] output =
     * null; if (isNotEmpty(str)) { JSONArray jsonArray = (JSONArray)
     * JSONSerializer.toJSON( str ); JsonConfig jsonConfig = new JsonConfig();
     * jsonConfig.setArrayMode( JsonConfig.MODE_OBJECT_ARRAY ); output = (Object[])
     * JSONSerializer.toJava( jsonArray, jsonConfig ); } return output; } public
     * static String getMonthPeriodString(Map<String, Object> map){
     *
     * String monthPeriodUI = (String) map.get("monthPeriod"); if
     * (isNotEmpty(monthPeriodUI) && monthPeriodUI.trim().contains(" ")) { String
     * month = org.apache.commons.lang.StringUtils.substringBefore(monthPeriodUI ,
     * " "); String year =
     * org.apache.commons.lang.StringUtils.substringAfter(monthPeriodUI , " ");
     * String monthNo = getMonthNumber(month); monthPeriodUI = year.concat(monthNo);
     * } return monthPeriodUI; }
     *
     * public static String getMonthPeriodString(String monthPeriodUI){
     *
     * if (isNotEmpty(monthPeriodUI)) { String month =
     * org.apache.commons.lang.StringUtils.substringBefore(monthPeriodUI , " ");
     * String year =
     * org.apache.commons.lang.StringUtils.substringAfter(monthPeriodUI , " ");
     * String monthNo = getMonthNumber(month); monthPeriodUI = year.concat(monthNo);
     *
     * } return monthPeriodUI; }
     */

    public static String getMonthNumber(String monthName) {

        String monthNumber = "";
        if (monthName.equals("January"))
            monthNumber = "01";
        if (monthName.equals("February"))
            monthNumber = "02";
        if (monthName.equals("March"))
            monthNumber = "03";
        if (monthName.equals("April"))
            monthNumber = "04";
        if (monthName.equals("May"))
            monthNumber = "05";
        if (monthName.equals("June"))
            monthNumber = "06";
        if (monthName.equals("July"))
            monthNumber = "07";
        if (monthName.equals("August"))
            monthNumber = "08";
        if (monthName.equals("September"))
            monthNumber = "09";
        if (monthName.equals("October"))
            monthNumber = "10";
        if (monthName.equals("November"))
            monthNumber = "11";
        if (monthName.equals("December"))
            monthNumber = "12";
        return monthNumber;
    }

    public static String getMonthName(String monthNumber) {

        String monthName = "";
        if (monthNumber.equals("0"))
            monthName = "January";
        if (monthNumber.equals("1"))
            monthName = "February";
        if (monthNumber.equals("2"))
            monthName = "March";
        if (monthNumber.equals("3"))
            monthName = "April";
        if (monthNumber.equals("4"))
            monthName = "May";
        if (monthNumber.equals("5"))
            monthName = "June";
        if (monthNumber.equals("6"))
            monthName = "July";
        if (monthNumber.equals("7"))
            monthName = "August";
        if (monthNumber.equals("8"))
            monthName = "September";
        if (monthNumber.equals("9"))
            monthName = "October";
        if (monthNumber.equals("10"))
            monthName = "November";
        if (monthNumber.equals("11"))
            monthName = "December";
        return monthName;
    }

    public static Calendar getCalendarForCurrentTimeStamp() {
        long timestampInMillis = System.currentTimeMillis();

        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set the Calendar's time using the timestamp
        calendar.setTimeInMillis(timestampInMillis);

        return calendar;
    }

    public static Calendar getCalendarFromTimeStamp(Timestamp inputTimeStamp) {
        long timestampInMillis = inputTimeStamp.getTime();
        logger.info("Input Timestamp: {}", inputTimeStamp);

        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set the Calendar's time using the timestamp
        calendar.setTimeInMillis(timestampInMillis);

        return calendar;

//      // Now, you can work with the Calendar object
//      int year = calendar.get(Calendar.YEAR);
//      int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so adding 1
//      int day = calendar.get(Calendar.DAY_OF_MONTH);
//      int hour = calendar.get(Calendar.HOUR_OF_DAY);
//      int minute = calendar.get(Calendar.MINUTE);
//      int second = calendar.get(Calendar.SECOND);
//
//      System.out.println("Year: " + year);
//      System.out.println("Month: " + month);
//      System.out.println("Day: " + day);
//      System.out.println("Hour: " + hour);
//      System.out.println("Minute: " + minute);
//      System.out.println("Second: " + second);


    }

    public static List<String> getMathematicalSymbolListFromStr(String expression) {
        Pattern pattern = Pattern.compile("[-+*/%]");
        Matcher matcher = pattern.matcher(expression);

        // Create an array to store the arithmetic symbols
        List<String> arithmeticSymbols = new ArrayList<>();

        while (matcher.find()) {
            String symbol = matcher.group();
            arithmeticSymbols.add(symbol);
        }

        return arithmeticSymbols;
    }

    interface IsEmpty {
        boolean isEmpty();
    }

    public static String convertStringIntoMathematicalExpression(String expression) {
        //String expression = "2+0*8";
        ScriptEngineManager manager1 = new ScriptEngineManager();

        for (ScriptEngineFactory engineFactory : manager1.getEngineFactories()) {
            logger.info("Engine Name: " + engineFactory.getEngineName());
            logger.info("Engine Version: " + engineFactory.getEngineVersion());
            logger.info("Language Name: " + engineFactory.getLanguageName());
            logger.info("Language Version: " + engineFactory.getLanguageVersion());
            logger.info("-----");
        }


        Object result = "";
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("Nashorn");
            result = engine.eval(expression);

        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return (String) result;
    }

    public static double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");
        return evaluateExpressionHelper(expression);
    }

    private static double evaluateExpressionHelper(String expression) {
        logger.info("Expression in Helper Util {}", expression);
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                operandStack.push(Double.parseDouble(number.toString()));
            } else if (ch == '(') {
                operatorStack.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    applyOperator(operandStack, operatorStack.pop());
                }
                operatorStack.pop(); // Pop '('
                i++;
            } else {
                while (!operatorStack.isEmpty() && precedence(ch) <= precedence(operatorStack.peek())) {
                    applyOperator(operandStack, operatorStack.pop());
                }
                operatorStack.push(ch);
                i++;
            }
        }

        while (!operatorStack.isEmpty()) {
            applyOperator(operandStack, operatorStack.pop());
        }

        return operandStack.pop();
    }

    private static void applyOperator(Stack<Double> operandStack, char operator) {
        logger.info("Operand Stack {}", operandStack);
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();

        if (isNotEmpty(operator)) {
            switch (operator) {
                case '+':
                    operandStack.push(operand1 + operand2);
                    break;
                case '-':
                    operandStack.push(operand1 - operand2);
                    break;
                case '*':
                    operandStack.push(operand1 * operand2);
                    break;
                case '/':
                    operandStack.push(operand1 / operand2);
                    break;
                case '^':
                    operandStack.push(Math.pow(operand1, operand2));
                    break;
            }
        }
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0; // Default precedence for unknown operators
        }
    }


    //graal.js


    public static Double limitPrecision(Double value, int maxDigitsAfterDecimal) {
        Double truncated = null;
        if (value != null) {
            int multiplier = (int) Math.pow(10, maxDigitsAfterDecimal);
            truncated = (double) ((long) ((value) * multiplier)) / multiplier;
            System.out.println(value + " ==> " + truncated);
        }
        return truncated;
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static Date convertDateOneFormatToAnother(String dateinString, String OLD_FORMAT) {
        final String NEW_FORMAT = "yyyy-MM-dd";

        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        java.util.Date dateOld = null;
        try {
            dateOld = sdf.parse(dateinString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(dateOld);
        Date shipmentDate = Date.valueOf(newDateString.toString());
        return shipmentDate;
    }


}
