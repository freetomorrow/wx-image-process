package com.myapp.weixin;

import java.io.File;
import java.util.Arrays;

/**
 * 删除空目录 a
 */
public class CleanEmpty {

    static  String dir="D:/wx/WeChat Files/***/FileStorage/MsgAttach";

    public static void main(String[] args) {

        File file=new File(dir);

        if(file.isDirectory()){
            clean(file);
        }


    }


    static void clean(File file){

        if(!file.isDirectory()){
            return;
        }
        if(file.listFiles().length==0){
            System.out.println(file.getAbsolutePath());
            file.delete();
            return;
        }

        Arrays.stream(file.listFiles()).parallel().forEach(file1->{
            clean(file1);
        });


    }

}
