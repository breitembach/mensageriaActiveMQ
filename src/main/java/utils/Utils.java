package utils;

public class Utils {

    public static boolean isNullEmpty(String text) {
        if(text == null || text.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
