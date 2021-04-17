package com.simple4j.demo.export;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Chen
 * @created 2020-10-21-12:04.
 */
public class ExcelExportTest /*extends ExampleDemoApplicationTests*/ {
   /* @Resource
    private SiFaultDataMapper siFaultDataMapper;*/

    public static void main(String[] args) {

    }

    @Test
    public void test() {
        testWriteBean();
    }

    private void testWriteBean() {

        String destFilePath = "d:/" + DateUtil.date().toDateStr() + "/writeBeanTest_" + System.currentTimeMillis() + ".xlsx";
        List lists = Lists.newArrayList();
        for (int i = 0; i < 15; i++) {
            TestBean bean = new TestBean();
            bean.setName("张三");
            bean.setAge(22);
            bean.setPass(true);
            bean.setScore(66.30);
            bean.setExamDate(DateUtil.date());
            lists.add(bean);
        }


        List<TestBean> rows = CollUtil.newArrayList(lists);
        // 通过工具类创建writer

        ExcelWriter writer = new ExcelWriter(destFilePath,"1");
        // 合并单元格后的标题行，使用默认标题样式

        for (int i = 1; i <= 3; i++) {
            if (i>1){
                writer.setSheet(String.valueOf(i));
            }

            genSheetRow(rows, writer, i);
        }
        //name	age	score	isPass	examDate
        //genSheetRow(rows, writer, title1);
        //String title2 = "2";
        //writer.setSheet(  title2);
        //genSheetRow(rows, writer, title2);


// 关闭writer，释放内存
        writer.close();
    }

    private void genSheetRow(List<TestBean> rows, ExcelWriter writer, Integer index) {
        //name	age	score isPass	examDate

        //writer.merge(4, index + "班成绩单");
        writer.addHeaderAlias("name", index + "线路");
        writer.addHeaderAlias("age", index + "区间");
        writer.addHeaderAlias("score", index + "上/下行/其它");
        writer.addHeaderAlias("isPass", index + "环数/米");
        writer.addHeaderAlias("isPass", index + "加权分");
        writer.addHeaderAlias("isPass", index + "病害分类2");
        writer.addHeaderAlias("isPass", index + "具体部位");
        writer.addHeaderAlias("isPass", index + "病害分类4");
        writer.addHeaderAlias("isPass", index + "病害量");
        writer.addHeaderAlias("isPass", index + "病害等级");
        writer.addHeaderAlias("isPass", index + "病害权重");
        writer.addHeaderAlias("isPass", index + "设备权重");
        writer.addHeaderAlias("isPass", index + "影响设备");
        writer.addHeaderAlias("isPass", index + "数据异常");
        writer.addHeaderAlias("isPass", index + "砂性土层(是/否)");
        writer.addHeaderAlias("isPass", index + "照片编号");
        writer.addHeaderAlias("examDate", index + "病害发现日期");
        writer.addHeaderAlias("examDate", index + "备注");
        writer.addHeaderAlias("examDate", index + "是否变化(降级/升级/消除/平级 含日期)");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
    }


}
