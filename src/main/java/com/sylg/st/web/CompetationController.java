package com.sylg.st.web;

import com.sylg.st.dto.ComList;
import com.sylg.st.dto.ComMember;
import com.sylg.st.dto.SomeoneCom;
import com.sylg.st.dto.TeamMessage;

import com.sylg.st.mapper.ComApplicationMapper;
import com.sylg.st.pojo.ComApplication;
import com.sylg.st.pojo.Competation;
import com.sylg.st.service.impl.CompetationServiceImpl;
import com.sylg.st.util.GetBaomingExcel;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class CompetationController {
    @Autowired
    CompetationServiceImpl competationService;
    @Autowired
    ComApplicationMapper comApplicationMapper;
    @RequestMapping(value = "addfujian",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addFujian(@RequestParam MultipartFile file){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            int order = competationService.addFujian(file);
            if(order>0){
                result.setResult(order);
                result.setStatus("ok");
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
    //添加一个新的比赛
    @RequestMapping(value = "addcompetation",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addCompetation(@RequestParam String comName,
                                                     @RequestParam String comTime,
                                                     @RequestParam String explain,
                                                     @RequestParam String startbaoming,
                                                     @RequestParam Integer memberLimit,
                                                     @RequestParam String jiezhibaoming,
                                                     @RequestParam String generalize){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        System.out.println("comName:"+comName);
        try{
            int order = competationService.addCompetation(comName,comTime,explain,memberLimit,startbaoming,jiezhibaoming,generalize) ;
            if(order > 0){
                result.setStatus("ok");
                result.setResult(order);
            }else{
                result.setResult(order);
                result.setStatus("fail");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
    return ResponseEntity.ok(result);
    }
    //删除一个比赛
    @RequestMapping(value = "deletecompetation",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteCompetation(@RequestParam int comId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            int order = competationService.deleteCompetation(comId);
            if(order>0){
                result.setResult(order);
                result.setStatus("ok");
            }else {
                result.setStatus("fail");
                result.setResult(order);
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    //获得所有比赛列表
    @RequestMapping(value = "getallcompetations",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAllCompetations(){
        JsonResult result =(JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<ComList> competations = competationService.getAllCompetations();
            if(competations!=null){
                result.setResult(competations);
                result.setStatus("ok");
            }else {
                result.setStatus("fail");
                result.setResult(competations);
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    //获取某个比赛的详细信息
    @RequestMapping(value = "getcommessage",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getComMessage(@RequestParam int comId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            Competation competation = competationService.getCompetation(comId);
            if(competation!=null){
                result.setStatus("ok");
                result.setResult(competation);
            }else {
                result.setStatus("fail");
                result.setResult(competation);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);

    }
//    /**
//     * 获取比赛名+id
////     * */
//    @RequestMapping(value = "getcomname",method = RequestMethod.GET)
//    public ResponseEntity<JsonResult> getComName(){
//        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
//        try{
//            ArrayList<ComList> lists = new ArrayList<>();
//            List<Competation> competations = competationService.getAllCompetations();
//        }catch (Exception e){
//
//        }
//        return  ResponseEntity.ok(result);
//    }
    //获取某个比赛所有报名的人员
    @RequestMapping(value = "commembers",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getComMembers(@RequestParam int comId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<ComMember> comMembers = competationService.getAllComMembers(comId);
            if(comMembers!=null){
                result.setStatus("ok");
                result.setResult(comMembers);
            }else {
                result.setStatus("fail");
                result.setResult("该比赛还没有报名人员");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

/**
 * 获取报名excel
 * */
@RequestMapping(value = "getbaomingexcel",method = RequestMethod.GET)
public ResponseEntity<JsonResult> getBaoMingExcel(@RequestParam Integer comId){
    JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
    try {
        Competation competation = competationService.getCompetation(comId);
        List<ComApplication> applications = comApplicationMapper.selectByComId(comId);
        String path = GetBaomingExcel.getBaomingExcel(competation.getCompetationName(), applications);
        if(path.equals(null)){
            result.setStatus("fail");
            result.setResult(path);
        }else {
            result.setStatus("ok");
            result.setResult("st\\"+path);
        }
    }catch (Exception e){
        result.setStatus("error");
        result.setResult(e.getClass()+":"+e.getMessage());
    }
    return ResponseEntity.ok(result);
}

    //取消报名
    @RequestMapping(value = "cancelapply",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> cancelApply(@RequestParam int memberId,@RequestParam int comId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            int order = competationService.giveUpApply(memberId,comId);
            if(order>0){
                result.setStatus("ok");
                result.setResult(order);
            }else {
                result.setStatus("fail");
                result.setResult("该比赛还没有报名人员");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    //获取单个成员报名过的所有比赛列表信息
    @RequestMapping(value = "someonecom",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> someoneCom(@RequestParam int memberId) {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            List<SomeoneCom> someoneComs = competationService.getMemberAllCom(memberId);
            if(someoneComs!=null){
                result.setStatus("ok");
                result.setResult(someoneComs);
            }else {
                result.setStatus("fail");
                result.setResult("你还没有报名过比赛");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    //获取单个成员报名过的某个比赛的具体团队信息
    @RequestMapping(value = "abcom",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> abCom(@RequestParam int comId,@RequestParam int memberId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            TeamMessage teamMessage = competationService.geTeamMembers(memberId,comId);
            if(teamMessage!=null){
                result.setStatus("ok");
                result.setResult(teamMessage);
            }else {
                result.setStatus("fail");
                result.setResult("查找的信息不存在");
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
