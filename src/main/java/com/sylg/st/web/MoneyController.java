package com.sylg.st.web;

import com.sylg.st.dto.MoneyRecord;
import com.sylg.st.pojo.Moneyrecords;
import com.sylg.st.service.MoneyService;
import com.sylg.st.service.impl.MoneyServiceImpl;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoneyController {
    @Autowired
    MoneyServiceImpl moneyService;
    /**
     * 删除账单记录
     * */
    @RequestMapping(value = "deletebill",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteMoneyRecords(@RequestParam Integer billid){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            int order = moneyService.deleteBill(billid);
            if(order>0){
                result.setResult(order);
                result.setStatus("ok");
            }else{
                result.setResult(order);
                result.setStatus("fail");
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获得所有bill
     * */
    @RequestMapping(value = "getbills",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getbills(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            List<MoneyRecord> bills = moneyService.getBills();
            result.setStatus("ok");
            result.setResult(bills);
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+";"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 添加账单记录
     * */
    @RequestMapping(value = "/addbill" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addBill(@RequestParam String reason,
                                              @RequestParam Double cost,
                                              @RequestParam String type
                                              ){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = moneyService.addBill(cost,reason,type);
            if(order >0) {
                result.setStatus("ok");
                result.setResult(order);
            }else {
                result.setStatus("fail");
                result.setResult(order);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**获得当前剩余的钱*/
    @RequestMapping(value = "getmoneywudi" ,method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getMoney(){
        JsonResult r = (JsonResult) SpringUtil.getBean("JsonResult");
        double money = moneyService.getMoney();
        r.setStatus("ok");
        r.setResult(money);
        return ResponseEntity.ok(r);
    }

}
