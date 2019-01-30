package com.sylg.st.util;

import com.sylg.st.dto.GoodsRecord;
import com.sylg.st.dto.MoneyRecord;
import com.sylg.st.pojo.*;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@Configuration
public class BeanConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:63342");
            }
        };
    }
    @Bean(name = "Goods")
    public Goods getGoods(){
        Goods good = new Goods();
        return good;
    }
    @Bean(name = "Member")
    public Member getMember(){
        Member member = new Member();
        return member;
    }
    @Bean(name = "MoneyRecords")
    public Moneyrecords getMoney(){
        Moneyrecords moneyRecords = new Moneyrecords();
        return moneyRecords;
    }
    @Bean(name ="JsonResult")
    public JsonResult getJsonR(){
        JsonResult jsonResult = new JsonResult();
        return jsonResult;
    }
    @Bean(name =  "GoodsRecord")
    public GoodsRecord getGoodRecord(){
        GoodsRecord goodsRecord = new GoodsRecord();
        return goodsRecord;
    }
    @Bean(name =  "GoodsRecords")
    public GoodsRecords getGoodRecords(){
        GoodsRecords goodsRecords = new GoodsRecords();
        return goodsRecords;
    }
    @Bean(name =  "MoneyRecords")
    public Moneyrecords getMoneyRecords(){
        Moneyrecords monRecords = new Moneyrecords();
        return monRecords;
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240000KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("10240000KB");
        return factory.createMultipartConfig();
    }
    @Bean(name =  "MoneyRecord")
    public MoneyRecord getMoneyRecord(){
        MoneyRecord monRecord = new MoneyRecord();
        return monRecord;
    }
    @Bean(name="Competation")
    public Competation getCompetation(){
        Competation competation = new Competation();
        return competation;
    }
    @Bean(name ="ComApplication")
    public ComApplication getComApplication(){
        ComApplication comApplication = new ComApplication();
        return comApplication;
    }

    @Bean(name="Train")
    public Train getTrain(){
        Train train = new Train();
        return train;
    }
    @Bean(name = "Comment")
    public Comment getComment(){
        Comment comment = new Comment();
        return comment;
    }
    @Bean(name="Membermac")
    public Membermac getMembermac(){
        Membermac membermac = new Membermac();
        return membermac;
    }

}