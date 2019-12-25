package com.xiaokang.login_MP.config;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
 }
}
