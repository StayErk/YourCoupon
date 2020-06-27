package model;

public class BackendValidation {
    public static boolean checkNumeroTelefono (String numeroTelefono) {
        String regex = "(\\d{3})[-]{1}(\\d{6,7})";
        return numeroTelefono != null && numeroTelefono.matches(regex);
    }

    public static boolean checkEmail (String email) {
        String regex = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";
        return email != null && email.matches(regex);
    }

    public static  boolean checkStelle (int stelle) {
        return  stelle != 0 && stelle >= 1 && stelle <= 5;
    }

    public static  boolean checkID(String id) {
        return id != null && id.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}");
    }

    public static  boolean checkTesto(String testo, int maxlenght) {
        return  testo != null && testo.length() <= maxlenght;
    }

    public static boolean checkCosto(Double costo) {
        return  costo >= 0 && costo<= 9999999.99999;
    }

    public static boolean checkPwd(String pwd) {
        return pwd != null &&  pwd.length() >= 8;
    }
}
