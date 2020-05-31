package org.afrinnov;

public class AUrlUtils {
    public static final String CARS = "/cars";
    public static final String CARS_NEW = "/cars/new";
    public static final String CARS_EDIT = "/cars/edit";

    private AUrlUtils() {
    }

    public static String redirect(String url) {
        return "redirect:" + url;
    }

    public static String failedRedirect(String url, int errorCode) {
        return String.format("redirect:%s?error-code=%d", url,errorCode);
    }
}
