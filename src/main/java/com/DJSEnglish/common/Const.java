package com.DJSEnglish.common;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String ID = "id";

    public static final Set<String> sexSet = Sets.newHashSet("男", "女");

    public static boolean checkSex(String sex)
    {
        return sexSet.contains(sex);
    }
}
