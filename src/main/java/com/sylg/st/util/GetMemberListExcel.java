package com.sylg.st.util;

import com.sylg.st.pojo.ComApplication;
import com.sylg.st.pojo.Member;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetMemberListExcel {
    public static String getMemberListExcel(String memberTh, List<Member>members) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        //得到一个poi工具类
        CreationHelper creationHelper = workbook.getCreationHelper();
        //新建一个sheet
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 6000);
        //新建一个标题的字体
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 17); // 字体高度
        //font.setColor(Font); // 字体颜色
        font.setFontName("宋体"); // 字体
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        //新建一个一般的字体
        Font font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 11);

        //设置标题单元格格式
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置一般单元格格式
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setFont(font1);
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);

        for (int i = 0; i < 50; i++) {
            Row row = sheet.createRow(i);
        }


        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 8));
//        File file = new File("C:\\Users\\master\\Desktop\\hahahha.xls");
        Row row = sheet.getRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(creationHelper.createRichTextString("沈理电协第"+memberTh + "届人员信息汇总表"));
        cell.setCellStyle(cellStyle1);
        Row row1 = sheet.getRow(3);
        for (int i = 0; i < 9; i++) {
            Cell cell1 = row1.createCell(i);
            cell1.setCellStyle(cellStyle2);
            switch (i) {
                case 0:
                    row1.getCell(0).setCellValue(creationHelper.createRichTextString("学号"));
                    break;
                case 1:
                    row1.getCell(1).setCellValue(creationHelper.createRichTextString("姓名"));
                    break;
                case 2:
                    row1.getCell(2).setCellValue(creationHelper.createRichTextString("性别"));
                    break;
                case 3:
                    row1.getCell(3).setCellValue(creationHelper.createRichTextString("学院"));
                    break;
                case 4:
                    row1.getCell(4).setCellValue(creationHelper.createRichTextString("部门"));
                    break;
                case 5:
                    row1.getCell(5).setCellValue(creationHelper.createRichTextString("QQ"));
                    break;
                case 6:
                    row1.getCell(6).setCellValue(creationHelper.createRichTextString("微信"));
                    break;
                case 7:
                    row1.getCell(7).setCellValue(creationHelper.createRichTextString("手机"));
                    break;
                case 8:
                    row1.getCell(8).setCellValue(creationHelper.createRichTextString("职位"));
                    break;
            }
        }
        for (int i = 0; i < members.size(); i++) {
//            sheet.createRow(i+4);
            Row row2 = sheet.getRow(i + 4);
            for (int j = 0; j < 9; j++) {
                Cell cell1 = row2.createCell(j);
                cell1.setCellStyle(cellStyle2);
                switch (j) {

                    case 0:
                        row2.getCell(0).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberId().toString()));
                        break;
                    case 1:
                        row2.getCell(1).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberName()));
                        break;
                    case 2:
                        row2.getCell(2).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberSex()));
                        break;
                    case 3:
                        row2.getCell(3).setCellValue(creationHelper.createRichTextString(members.get(i).getSchoolName()));
                        break;
                    case 4:
                        row2.getCell(4).setCellValue(creationHelper.createRichTextString(members.get(i).getDepartmentName()));
                        break;
                    case 5:
                        row2.getCell(5).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberQq().toString()));
                        break;
                    case 6:
                        row2.getCell(6).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberWechat()));
                        break;
                    case 7:
                        row2.getCell(7).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberPhone()));
                        break;
                    case 8:
                        row2.getCell(8).setCellValue(creationHelper.createRichTextString(members.get(i).getMemberIdentify()));
                        break;
                }
            }
        }

        FileOutputStream os = new FileOutputStream("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\excel\\" + memberTh+ ".xls");
        workbook.write(os);
        os.close();
        return "st\\excel\\" + memberTh+ ".xls";
    }
}
