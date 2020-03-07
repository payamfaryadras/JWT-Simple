package com.payam.learn.usermanagement.constans;

public class SecurityConstant {

    //Login URL
    public static final String AUTH_LOGIN_URL = "/token";

    public static final String ROLE_CLIMS = "rol";

    //Jwt secret key
    public static final String SECRET_KEY = "d4r5u8x/A%D*G-KaPdSgCkYp5d6v9y$C&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    //JWT default
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstant(){
        throw new IllegalStateException("Cannot create instance of util class!!!");
    }
}
