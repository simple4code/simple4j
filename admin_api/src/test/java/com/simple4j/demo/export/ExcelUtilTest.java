package com.simple4j.demo.export;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Chen
 * @created 2020-10-21-10:21.
 */
public class ExcelUtilTest {
    public static void getExcel(HttpServletResponse response, List list, String fileName) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //合格并单元格,使用默认标题
        //一次性写出内容,使用默认样式
        writer.write(list);
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        writer.flush(response.getOutputStream());
        //关闭writer,释放内存
        writer.close();
    }

    public static void main(String[] args) {
        //testWriteList();
        //testWriteMap();
        //testWriteBean();
        testWriteBean();
    }

    private static void testWriteList() {
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> lists = Arrays.<List<String>>asList(row1, row2, row3, row4, row5);
        List<List<String>> rows = CollUtil.newArrayList(lists);

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
//通过构造方法创建writer
//ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

//跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

//合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
//一次性写出内容，强制输出标题
        writer.write(rows, true);
//关闭writer，释放内存
        writer.close();


    }

    private static void testWriteMap() {
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTest.xlsx");
// 合并单元格后的标题行，使用默认标题样式
        writer.merge(rows.size() - 1, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
// 关闭writer，释放内存
        writer.close();
    }

    private static void testWriteBean() {


        TestBean bean1 = new TestBean();
        bean1.setName("张三");
        bean1.setAge(22);
        bean1.setPass(true);
        bean1.setScore(66.30);
        bean1.setExamDate(DateUtil.date());

        TestBean bean2 = new TestBean();
        bean2.setName("李四");
        bean2.setAge(28);
        bean2.setPass(false);
        bean2.setScore(38.50);
        bean2.setExamDate(DateUtil.date());
        List<TestBean> lists = Arrays.<TestBean>asList(bean1, bean2);
        List<TestBean> rows = CollUtil.newArrayList(lists);
        // 通过工具类创建writer
        ExcelWriter writer = new ExcelWriter("d:/writeBeanTest_" + System.currentTimeMillis() + ".xlsx", "表1");
        // 合并单元格后的标题行，使用默认标题样式
        String title1 = "1";

        //name	age	score	isPass	examDate
        genSheet(rows, writer, title1);
        String title2 = "2";
        writer.setSheet("表" + title2);
        genSheet(rows, writer, title2);


// 关闭writer，释放内存
        writer.close();
    }

    private static void genSheet(List<TestBean> rows, ExcelWriter writer, String index) {
        //name	age	score	isPass	examDate
        writer.merge(4, index + "班成绩单");
        writer.addHeaderAlias("name", index + "姓名");
        writer.addHeaderAlias("age", index + "年龄");
        writer.addHeaderAlias("score", index + "成绩");
        writer.addHeaderAlias("isPass", index + "是否合格");
        writer.addHeaderAlias("examDate", index + "考试日期");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
    }
}
