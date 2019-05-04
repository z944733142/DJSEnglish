package com.DJSEnglish.common;

import java.util.HashSet;
import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String ID = "id";

    public static final Set<String> sexSet = new HashSet();

    static {
        sexSet.add("男");
        sexSet.add("女");
    }

    public static boolean checkSex(String sex)
    {
        return sexSet.contains(sex);
    }
}
