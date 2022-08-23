package cn.xibei.wechat.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;

public class EncUtils {

    public static String sortAndEncrypt(String appSecret, String timestamp, String nonce) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(appSecret);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        return DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
    }
}
