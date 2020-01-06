package com.lenovo.springboot.controller.utils;

import java.io.*;

public class StockFile {
    public static void main(String[] args) {
        StockFile.handdleSqlFile("c:\\origin.txt", "c:\\result.sql", "stock");
//        StockFile.handdleSqlFile(args[0], args[1], args[2]);
    }

    public static  void handdleSqlFile(String src, String dest, String table_name){
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            String str = "";
            String str1 = "";
            String stock_name = "";
            String stock_id = "";
            int i=1;
            fileInputStream = new FileInputStream(src);
            inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            fileOutputStream = new FileOutputStream(dest);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while ((str = bufferedReader.readLine()) != null){
                if(i%3 == 1){
                    stock_name = str;
                }

                if(i%3 == 2) {
                    stock_id = str;
                }

                if(i%3 == 0) {
                    str1 = "insert into "+ table_name + " (stock_name, stock_id) values ('" + stock_name + "','" + stock_id + "');\n";
                    System.out.print(str1);
                    bufferedWriter.write(str1);
                }
                i++;
            }
        } catch (FileNotFoundException e){
            System.out.println("找不到指定文件");
        } catch (IOException e){
            System.out.println("读取文件失败");
        } finally {
            try{
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();

                bufferedWriter.close();
                outputStreamWriter.close();
                fileOutputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
