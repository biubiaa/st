package com.sylg.st.web;

import com.sylg.st.dto.GetGoodName;
import com.sylg.st.dto.GoodName;
import com.sylg.st.dto.GoodsRecord;
import com.sylg.st.mapper.GoodsMapper;
import com.sylg.st.pojo.Goods;
import com.sylg.st.pojo.GoodsRecords;
import com.sylg.st.service.impl.GoodServiceImpl;
import com.sylg.st.service.impl.MoneyServiceImpl;
import com.sylg.st.util.JsonResult;
import com.sylg.st.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class GoodController {

    @Autowired
    GoodServiceImpl goodsService;
    @Autowired
    MoneyServiceImpl moneyService;
    @Autowired
    GoodsMapper goodsMapper;
    /**
     * 添加新商品
     * */
    @RequestMapping(value = "addgood", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> buyNewGood(@RequestParam String name,
                                                 @RequestParam Integer isXiaoHao,
                                                 @RequestParam String type,
                                                 @RequestParam String location,
                                                 @RequestParam String size) throws IOException {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        Goods good = (Goods) SpringUtil.getBean("Goods");
        try {
            good.setGoodsName(name);
            good.setGoodsCount(0);
            good.setIsXiaohao(isXiaoHao);
            good.setSize(size);
            good.setGoodsType(type);
            good.setGoodsLocation(location);
            int order = goodsService.addNewGood(good);
            if (order > 0) {
                result.setResult(order);
                result.setStatus("ok");
            } else {
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
     * 添加新商品图片
     * */
    @RequestMapping(value = "addgoodpic",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addGoodPic(@RequestParam MultipartFile file){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        int order=0;
        try{
            byte[] imgs = file.getBytes();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = df.format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File file1 = new File("F:\\JavaCode\\idea_code\\st\\src\\main\\resources\\static\\img\\" + filename);
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(imgs);
            System.out.println(file.isEmpty());
            List<Goods> goods = goodsService.getAllGoods();
            Goods goods1 = goods.get(goods.size()-1);
            goods1.setGoodsJpgUrl("st\\static\\img\\"+filename);
            order = goodsMapper.updateByPrimaryKey(goods1);
            if (order > 0) {
                result.setResult(order);
                result.setStatus("ok");
            } else {
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
     * 购买新商品
     * */
    @RequestMapping(value = "buygood",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> buyGood(@RequestParam Integer id,
                                              @RequestParam Integer num,
                                              @RequestParam double money){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = goodsService.buyGood(id, num, money);
            if(order>0){
                result.setStatus("ok");
                result.setResult(order);
            }else{
                result.setStatus("fail");
                result.setResult(order);
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);

    }
    /**
     * 获取所有商品名字+id
     * */
    @RequestMapping(value = "getgoodname",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getGoodsName(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<GetGoodName> getGoodNames = new ArrayList<GetGoodName>();
            List<Goods> goods = goodsService.getAllGoods();
            for (Goods g: goods
                 ) {
                GetGoodName getGoodName  = new GetGoodName();
                getGoodName.setGoodId(g.getGoodsId());
                getGoodName.setGoodName(g.getGoodsName());
                getGoodNames.add(getGoodName);
            }
            result.setStatus("ok");
            result.setResult(getGoodNames);
        }catch (Exception e){
            result.setStatus("error");
            result.setStatus(e.getClass()+""+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 获得所有商品信息
     * */
    @RequestMapping(value = "getgoods",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getAllGoods(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<Goods> goods = goodsService.getAllGoods();
            result.setResult(goods);
            result.setStatus("ok");
        }catch (Exception e){
            result.setStatus("fail");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    };
    /**
     * 获取商品图片(不知道对不对)
     * */

//    @RequestMapping(value = "goodpic",method = RequestMethod.GET)
//    public String downloadImg(@RequestParam String path,
//                              HttpServletResponse response) {
//        String imgPath = path;
//        if (imgPath != null) {
//            File file = new File(imgPath);
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition",
//                        "attachment;fileName=" + file.getName());// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//            }
//        }
//        return null;
//    }


    /**
     * 获取物品的使用记录
     * */
    @RequestMapping(value = "goodrecords",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getCGoodsRecords() {
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            List<GoodsRecord> goodsRecords = goodsService.getDtoGoodRecords();
            result.setStatus("ok");
            result.setResult(goodsRecords);
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 借good
     * */
    @RequestMapping(value = "borrow",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> borrowGoods(@RequestParam Integer goodId,
                                                  @RequestParam Integer memberId,
                                                  @RequestParam Integer num){
        JsonResult result= (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            int order = goodsService.borrowGood(memberId,goodId,num);
            if (order>0) {
                result.setResult(order);
                result.setStatus("ok");
            }else{
                if(order==0)
                result.setResult("所要借取商品数量大于该商品总数，请核对后再借");
                result.setStatus("fail");
                if(order==-2){
                    result.setResult("库中并没有该种类货物，请核对货物名称");
                    result.setStatus("fail");
                }
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 归还good
     * */
    @RequestMapping(value = "giveback",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> giveBack(@RequestParam Integer memberId,@RequestParam Integer goodId,
                                               @RequestParam Integer num){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = goodsService.giveBackGood(memberId,goodId,num);
            if(order>0){
                result.setStatus("ok");
                result.setResult("归还成功");
            }else {
                if (order==0) {
                    result.setStatus("fail");
                    result.setResult("系统内部异常");
                }
                else{
                    result.setStatus("fail");
                    result.setResult("所还数量小于欠的数量，请一起归还");
                }
            }
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 查询某个成员欠的物品
     * */
    @RequestMapping(value = "memberowe",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> memberOwe(@RequestParam Integer memberId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try{
            List<GoodName> goodNames = goodsService.getMemberOwe(memberId);
            for(int i =0;i<goodNames.size()-1;i++){
                for(int j=goodNames.size()-1;j>i;j--){
                    if(goodNames.get(i).getGoodId()==goodNames.get(j).getGoodId()){
                        goodNames.remove(j);
                    }
                }
            }
            result.setResult(goodNames);
            result.setStatus("ok");
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 获取所有未还商品的记录
     * */
    @RequestMapping(value = "getnogiveback",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getNoGiveBack(){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            List<GoodsRecords> records = goodsService.getNoGivrBackList();
            result.setStatus("ok");
            result.setResult(records);
        }catch (Exception e){
            result.setStatus("error");
            result.setResult(e.getClass()+":"+e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 删除good
     * */
    @RequestMapping(value = "deletegood",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteGoods(@RequestParam Integer goodId){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = goodsService.deleteGood(goodId);
            if (order > 0) {
                result.setResult(order);
                result.setStatus("ok");
            } else {
                result.setResult(order);
                result.setStatus("fail");
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
};

    /**
     * 删除goodrecord
     * */
    @RequestMapping(value = "deletegoodrecord",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> deleteCRecords(@RequestParam Integer goodrecordid){
        JsonResult result = (JsonResult) SpringUtil.getBean("JsonResult");
        try {
            int order = goodsService.deleteGoodRecord(goodrecordid);
            if (order > 0) {
                result.setResult(order);
                result.setStatus("ok");
            } else {
                result.setResult(order);
                result.setResult("fail");
            }
        }catch (Exception e){
            result.setResult(e.getClass()+":"+e.getMessage());
            result.setStatus("error");
        }
        return ResponseEntity.ok(result);
    }
}
