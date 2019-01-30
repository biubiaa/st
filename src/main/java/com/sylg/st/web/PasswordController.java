package com.sylg.st.web;

import com.sylg.st.mapper.AdminpasswordMapper;
import com.sylg.st.mapper.MemberMapper;
import com.sylg.st.mapper.MemberpasswordMapper;
import com.sylg.st.pojo.Adminpassword;
import com.sylg.st.pojo.Member;
import com.sylg.st.pojo.Memberpassword;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
    @Autowired
    MemberpasswordMapper memberpasswordMapper;
    @Autowired
    AdminpasswordMapper adminpasswordMapper;
    @Autowired
    MemberMapper memberMapper;
    //member登录
    @RequestMapping(value = "checkmemberpassword",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> checkMemberPassword(@RequestParam Integer memberId,@RequestParam String password){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        Memberpassword memberpassword = memberpasswordMapper.selectByPrimaryKey(memberId);
        if(memberpassword==null){
            Member member = memberMapper.selectByMemberId(memberId);
            if (password.equals(memberId.toString())){
                result.setStatus("ok");
                result.setResult(1);
            }else {
                result.setStatus("fail");
                result.setResult("账号或密码错误");
            }
        }else{
            if(memberpassword.getPassword() .equals(password) ){
                result.setStatus("ok");
                result.setResult(1);
            }else{
                result.setStatus("fail");
                result.setResult("账号或者密码错误");
            }
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 修改密码
     * */
    @RequestMapping(value = "changepassword",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> changePassword(@RequestParam String memberId,@RequestParam String password){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            Memberpassword m = memberpasswordMapper.selectByPrimaryKey(Integer.parseInt(memberId));
            if (m!=null) {
                Memberpassword memberpassword = new Memberpassword();
                memberpassword.setMemberId(Integer.parseInt(memberId));
                memberpassword.setPassword(password);
                memberpasswordMapper.updateByPrimaryKey(memberpassword);
            }else {
                Memberpassword memberpassword = new Memberpassword();
                memberpassword.setMemberId(Integer.parseInt(memberId));
                memberpassword.setPassword(password);
                memberpasswordMapper.insert(memberpassword);
            }
            result.setResult(1);
            result.setStatus("ok");
        }catch (Exception e){
            result.setStatus("fail");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
//    admin登录
    @RequestMapping(value = "checkadminpassword",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> checkAdminPassword(@RequestParam String adminId,@RequestParam String password){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        Adminpassword adminpassword = adminpasswordMapper.selectByPrimaryKey(adminId);
        if(adminpassword==null){
            result.setStatus("fail");
            result.setResult("账号或密码错误");
        }else{
            if(adminpassword.getPassword() .equals(password)){
                result.setStatus("ok");
                result.setResult(1);
            }else{
                result.setStatus("fail");
                result.setResult("账号或者密码错误");
            }
        }
        return ResponseEntity.ok(result);
    }

    //添加成员密码信息
    @RequestMapping(value = "addmemberpassword",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addMemberPassword(@RequestParam Integer memberId,@RequestParam String password){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            Memberpassword memberpassword = memberpasswordMapper.selectByPrimaryKey(memberId);
            if (memberpassword == null) {
                memberpassword.setMemberId(memberId);
                memberpassword.setPassword(password);
                result.setStatus("ok");
                result.setResult(1);
            } else {
                result.setStatus("fail");
                result.setResult("你输入的账号已存在");
            }
        }catch (NullPointerException e){
            Memberpassword memberpassword  = new Memberpassword();
            memberpassword.setPassword(password);
            memberpassword.setMemberId(memberId);
            memberpasswordMapper.insert(memberpassword);
            result.setStatus("ok");
            result.setResult(1);
        }
        return ResponseEntity.ok(result);
    }
}
