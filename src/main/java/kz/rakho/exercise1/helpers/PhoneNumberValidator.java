package kz.rakho.exercise1.helpers;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static final String PHONE_NUMBER__PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER__PATTERN);


    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
