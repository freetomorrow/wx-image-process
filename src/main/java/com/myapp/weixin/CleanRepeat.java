package com.myapp.weixin;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除同一个目录下面重复文件
 */
public class CleanRepeat {


    static String dir = "D:/wx/WeChat Files/***/FileStorage/test/";

    public static void main(String[] args) {

        File file = new File(dir);

        if (file.isDirectory()) {
            clean(file);
        }


    }


    static void clean(File file) {

        Map map = new HashMap(256, 1);
        Arrays.stream(file.listFiles()).parallel().forEach(file1 -> {

            if (file1.isDirectory()) {
                clean(file1);
                return;
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file1));
                byte[] b=new byte[1024];

                while(bufferedInputStream.read(b)!=-1){
                    byteArrayOutputStream.write(b);
                }

                String md5=MD5Util.encrypt(byteArrayOutputStream.toByteArray());
                if(map.get(md5)!=null){
                    bufferedInputStream.close();
                    byteArrayOutputStream.close();
                    System.out.println(file1.getAbsolutePath());
                    file1.delete();
                    return;
                }
                map.put(md5,"");


            } catch (Exception e) {
                e.printStackTrace();
            }


        });


    }


}
