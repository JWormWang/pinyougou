package com.oracle.pinyougou.controller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oracle.pinyougou.pojo.TbSeckillGoods;
import com.oracle.pinyougou.pojo.TbSeckillOrder;
import com.oracle.pinyougou.seckill.service.SeckillGoodsService;
import com.oracle.pinyougou.seckill.service.SeckillOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Reference
    private SeckillGoodsService seckillGoodsService;
    @Reference
    private SeckillOrderService orderService;
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
   @RequestMapping("/view")
    public String view(long id,Map map){
    TbSeckillGoods tbSeckillGoods=seckillGoodsService.findOneFromRedis(id);
    map.put("goods",tbSeckillGoods);

       return  "/seckill-item";
   }
    @RequestMapping("/submitOrder")
    @ResponseBody
    public Map submitOrder(Long  seckillid){
       String userid="zhangsan";
        Map map=new HashMap();
     try{
        String orderid=  orderService.submitOrder(seckillid,userid);
         map.put("flag",true);
         map.put("orderid",orderid);
     }catch (Exception e)
     {
         map.put("flag",false);
         map.put("msg",e.getMessage());
     }


        return  map;
    }
    @RequestMapping("/pay")
    public String pay(long orderid,Map map){
       String userid="zhangsan";
       //到缓存中查询订单，将订单传到付款页面，显示订单的信息
        TbSeckillOrder order=orderService.searchOrderFromRedisByUserId(userid,String.valueOf(orderid) );
       map.put("order",order);

       return "/pay";
    }

}
