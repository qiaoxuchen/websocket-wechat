package com.qiao.common;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Base64Util {


    /**
      * 使用Base64进行加密
      * @param res 密文
      * @return
      */
    public static String Base64Encode(String res) {
        return Base64.encode(res.getBytes());
    }


    /**
      * 使用Base64进行解密
      * @param res
      * @return
      */
    public static String Base64Decode(String res) {
        return new String(Base64.decode(res));
    }

    public static void main(String[] args) {

        //System.out.println(Base64Encode("1111111"));
        System.out.println(Base64Decode("MTExMTExMQ=="));

    }
}
