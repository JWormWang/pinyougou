package com.oracle.pinyougou.controller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oracle.pinyougou.pojo.TbSeckillGoods;
import com.oracle.pinyougou.seckill.service.SeckillGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Reference
    private SeckillGoodsService seckillGoodsService;
   @RequestMapping("/findList")
    public String findList(Map map){
       List<TbSeckillGoods> goods=seckillGoodsService.findList();

       for(TbSeckillGoods good:goods){
           good.setNumRate((int)(((double)good.getNum()-
                   (double)good.getStockCount())/(double)good.getNum()*100));
       }
       map.put("goods",goods);

       return "seckill-index";
   }
}
