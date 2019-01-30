package com.sylg.st.web;

import com.sylg.st.pojo.Member;
import com.sylg.st.service.impl.MemberServiceImpl;
import com.sylg.st.util.CompaInt;
import com.sylg.st.util.GetMemberListExcel;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberServiceImpl memberService;

    /**
     * 招新上传excel处理
     */
    @RequestMapping(value = "zhaoxin", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> zhaoXin(@RequestParam MultipartFile file) throws IOException {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        List<Member> members = new ArrayList<Member>();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheetAt(0);
            java.text.DecimalFormat format = new java.text.DecimalFormat("##########");
            int lastRowNum = sheet.getLastRowNum();
            int sheetMergeCount = sheet.getNumMergedRegions();
            String newTh = "";
            for (int i = 0; i < sheetMergeCount; i++) {
                CellRangeAddress ca = sheet.getMergedRegion(i);
                int firstColumn = ca.getFirstColumn();
                int lastColumn = ca.getLastColumn();
                int firstRow = ca.getFirstRow();
                int lastRow = ca.getLastRow();
                if (0 >= firstRow && 2 <= lastRow) {
                    if (0 >= firstColumn && 6 <= lastColumn) {
                        Row fRow = sheet.getRow(firstRow);
                        Cell fCell = fRow.getCell(firstColumn);
                        newTh = fCell.getStringCellValue();
                    }
                }
                char[] thNum = newTh.toCharArray();
                newTh = "";
                for (int z = 0; z < thNum.length; z++) {

                    if (thNum[z] >= 48 && thNum[z] <= 57) {
                        newTh = newTh + thNum[z];
                    }
                }
                String qq = "";
                String phone ="";
                for (int k = 4; k <= lastRowNum; k++) {
                    HSSFRow row = sheet.getRow(k);
                    Member member = new Member();
                    member.setMemberId(Integer.parseInt(format.format(row.getCell(0).getNumericCellValue())));
                    member.setMemberName(row.getCell(1).getStringCellValue());
                    member.setMemberSex(row.getCell(2).getStringCellValue());
                    member.setSchoolName(row.getCell(3).getStringCellValue());
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    qq = row.getCell(4).getStringCellValue();
                    member.setMemberQq(Integer.parseInt(qq));
                    member.setMemberWechat(row.getCell(5).getStringCellValue());
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    phone = row.getCell(6).getStringCellValue();
                    member.setMemberPhone(phone);
                    member.setMemberTh(Integer.parseInt(newTh));
                    member.setMemberIdentify("成员");
                    member.setDepartmentName("无");
                    members.add(member);
                }
                for (Member memb : members
                        ) {
                    memberService.addMember(memb);
                }
                result.setResult(1);
            }
        } catch (Exception e) {
            result.setStatus("error");
            result.setResult(e.getClass() + ":" + e.getMessage() + e.getStackTrace());
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 换届上传excel
     */
    @RequestMapping(value = "updatemembers", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> huanJie(@RequestParam MultipartFile file) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        List<Member> members = new ArrayList<Member>();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workbook.getSheetAt(0);
            java.text.DecimalFormat format = new java.text.DecimalFormat("##########");
            int lastRowNum = sheet.getLastRowNum();
            int sheetMergeCount = sheet.getNumMergedRegions();
            String newTh = "";
            for (int i = 0; i < sheetMergeCount; i++) {
                CellRangeAddress ca = sheet.getMergedRegion(i);
                int firstColumn = ca.getFirstColumn();
                int lastColumn = ca.getLastColumn();
                int firstRow = ca.getFirstRow();
                int lastRow = ca.getLastRow();
                if (0 >= firstRow && 2 <= lastRow) {
                    if (0 >= firstColumn && 8 <= lastColumn) {
                        Row fRow = sheet.getRow(firstRow);
                        Cell fCell = fRow.getCell(firstColumn);
                        newTh = fCell.getStringCellValue();
                    }
                }
                char[] thNum = newTh.toCharArray();
                newTh = "";
                for (int z = 0; z < thNum.length; z++) {

                    if (thNum[z] >= 48 && thNum[z] <= 57) {
                        newTh = newTh + thNum[z];
                    }
                }
                String id = "";
                String qq = "";
                for (int k = 4; k <= lastRowNum; k++) {
                    HSSFRow row = sheet.getRow(k);
                    Member member = new Member();
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    id = row.getCell(0).getStringCellValue();
                    member.setMemberId(Integer.parseInt(id));
                    member.setMemberName(row.getCell(1).getStringCellValue());
                    member.setMemberSex(row.getCell(2).getStringCellValue());
                    member.setSchoolName(row.getCell(3).getStringCellValue());
                    member.setDepartmentName(row.getCell(4).getStringCellValue());
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    qq = row.getCell(5).getStringCellValue();
                    member.setMemberQq(Integer.parseInt(qq));

                    member.setMemberWechat(row.getCell(6).getStringCellValue());
                    member.setMemberPhone(format.format(row.getCell(7).getNumericCellValue()));

                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    member.setMemberIdentify(row.getCell(8).getStringCellValue());
                    member.setMemberTh(Integer.parseInt(newTh));
                    members.add(member);
                }
                for (Member memb : members
                        ) {
                    Member member = new Member();
                    member = memberService.getMemberById(memb.getMemberId());
                    if (member == null) {
                        memberService.addMember(memb);
                    } else
                        memberService.updateMembber(memb);
                }
                result.setResult(1);
            }
        } catch (Exception e) {
            result.setStatus("error");
            result.setResult(e.getClass() + ":" + e.getMessage() + e.getStackTrace());
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 获取招新excel
     */
    @RequestMapping(value = "templet2", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getZhaoXin(/*HttpServletResponse response*/) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
//        String imgPath = "src\\main\\resources\\static\\招新模板.xls";
////        File file = new File(imgPath);
////        if (file.exists()) {
////            response.setContentType("application/force-download");// 设置强制下载不打开
////            response.addHeader("Content-Disposition",
////                    "attachment;fileName=" + file.getName());// 设置文件名
////            byte[] buffer = new byte[1024];
////            FileInputStream fis = null;
////            BufferedInputStream bis = null;
////            try {
////                fis = new FileInputStream(file);
////                bis = new BufferedInputStream(fis);
////                OutputStream os = response.getOutputStream();
////                int i = bis.read(buffer);
////                while (i != -1) {
////                    os.write(buffer, 0, i);
////                    i = bis.read(buffer);
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////            } finally {
////                if (bis != null) {
////                    try {
////                        bis.close();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////                if (fis != null) {
////                    try {
////                        fis.close();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        }
        String path = "st\\static\\招新模板.xls";
        result.setResult(path);
        result.setStatus("ok");
        return ResponseEntity.ok(result);
    }
    /**
     * 获取换届excel
     * */
    @RequestMapping(value = "templet1",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getHuanJie(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        String path = "st\\static\\换届模板.xls";
        result.setResult(path);
        result.setStatus("ok");
        return ResponseEntity.ok(result);
    }
    /**
     *下载社团某届人员信息excel
     */
    @RequestMapping(value = "getthexcel",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getThExcel(@RequestParam Integer th){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            String path  = memberService.memberExcel(th);
            if(path!=null){
                result.setStatus("ok");
                result.setResult(path);
            }else {
                result.setResult(path);
                result.setStatus("fail");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    @RequestMapping(value = "getmemberbyid",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getMemberById(@RequestParam Integer id){
        JsonResult jsonResult = new JsonResult();
        try{
            Member member = memberService.getMemberById(id);
            jsonResult.setResult(member);
            jsonResult.setStatus("ok");
        }catch (Exception e){
            jsonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }
    /**
     * 获取当前所有的“届
     * */
    @RequestMapping(value = "getths",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getThs(){
        JsonResult result = new JsonResult();
        try{
            List<Integer> ths = memberService.getThs();
            List<Integer> newThs = new ArrayList<>();
            for (int i =ths.size()-1;i>=0;i--){
                newThs.add(ths.get(i));
            }
            result.setResult(newThs);
            result.setStatus("ok");
        }catch (Exception e){
            result.setResult(e.getClass().getName() + ":" + e.getMessage());
            result.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    /**
     *根据th查询成员列表
     * @param th
     * @return
     * */

    @RequestMapping(value = "getthmember" ,method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getMemversByTh(@RequestParam Integer th){
        JsonResult r = new JsonResult();
        try {
            List<Member> members = memberService.getMembersByTh(th);
            List<Member> newMember = new ArrayList<>();
            for (int i =0;i<members.size();i++){
                if(members.get(i).getMemberIdentify().equals("社长")){
                    newMember.add(members.get(i));
                    break;
                }
            }
            for (int i =0;i<members.size();i++){
                if(members.get(i).getMemberIdentify().equals("副社长")){
                    newMember.add(members.get(i));
                }
            }
            for (int i =0;i<members.size();i++){
                if(members.get(i).getMemberIdentify().equals("部长")){
                    newMember.add(members.get(i));
                }
            }
            System.out.println(newMember.size());
            for (int i =0;i<members.size();i++){
                if(members.get(i).getMemberIdentify().equals("副部长")){
                    newMember.add(members.get(i));
                }
            }
            System.out.println(newMember.size());
            for (int i =0;i<members.size();i++){
                if(members.get(i).getMemberIdentify().equals("成员")){
                    newMember.add(members.get(i));
                }
            }
            r.setResult(newMember);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
    /**
     * 获取所有参加过社团的成员
     * */
    @RequestMapping(value = "getallmembers" ,method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAllMembers(){
        JsonResult r = new JsonResult();
        try {
            List<Member> members = memberService.getAllMembers();
            r.setResult(members);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
    /**
     * 添加一个新成员
     * */

    @RequestMapping(value = "addmember" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addMember(@RequestParam Integer memberId, @RequestParam String memberName,
                                                @RequestParam String memberSex, @RequestParam String schoolName,
                                                @RequestParam String departmentName, @RequestParam Integer memberQq,
                                                @RequestParam String memberWechat, @RequestParam String memberPhone,
                                                @RequestParam String memberIdentify, @RequestParam Integer memberTh){
        JsonResult r = new JsonResult();
        Member member = (Member) SpringUtil.getBean("Member");
        member.setMemberId(memberId);
        member.setMemberName(memberName);
        member.setMemberSex(memberSex);
        member.setSchoolName(schoolName);
        member.setDepartmentName(departmentName);
        member.setMemberQq(memberQq);
        member.setMemberWechat(memberWechat);
        member.setMemberPhone(memberPhone);
        member.setMemberIdentify(memberIdentify);
        member.setMemberTh(memberTh);
        try{
            int order = memberService.addMember(member);
            if(order<0){
                r.setResult(order);
                r.setStatus("fail");
            }else{
                r.setResult(order);
                r.setStatus("ok");
            }
        }catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
    /**
     * 根据id删除成员
     * @param id
     * @return
     */
    @RequestMapping(value = "deletemember", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> delete (@RequestParam Integer id){
        JsonResult r = new JsonResult();
        try {
            int ret = memberService.deleteMemberById(id);
            if (ret < 0) {
                r.setResult(ret);
                r.setStatus("fail");
            } else {
                r.setResult(ret);
                r.setStatus("ok");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
    /**
     * 根据id修改用户信息
     *
     * @return
     */

    @RequestMapping(value = "modifymember", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> update ( @RequestParam Integer memberId, @RequestParam String memberName,
                                               @RequestParam String memberSex, @RequestParam String schoolName,
                                               @RequestParam String departmentName, @RequestParam Integer memberQq,
                                               @RequestParam String memberWechat, @RequestParam String memberPhone,
                                               @RequestParam String memberIdentify, @RequestParam Integer memberTh){
        JsonResult r = new JsonResult();
        Member member = (Member) SpringUtil.getBean("Member");
        member.setMemberId(memberId);
        member.setMemberName(memberName);
        member.setMemberSex(memberSex);
        member.setSchoolName(schoolName);
        member.setDepartmentName(departmentName);
        member.setMemberQq(memberQq);
        member.setMemberWechat(memberWechat);
        member.setMemberPhone(memberPhone);
        member.setMemberIdentify(memberIdentify);
        member.setMemberTh(memberTh);
        try {
            int ret = memberService.updateMembber(member);
            if (ret < 0) {
                r.setResult(ret);
                r.setStatus("fail");
            } else {
                r.setResult(ret);
                r.setStatus("ok");
            }
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
