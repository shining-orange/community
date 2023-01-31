package com.nowcoder.community;

import java.io.IOException;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2023-01-30 14:40
 **/

public class WkTests {
    public static void main(String[] args) {
        String cmd="G:/wkhtmltox/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com D:/Phase_I_project/data/wk-images/3.png";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}