package com.oracle.pinyougou.controller.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oracle.pinyougou.WeixinPayService;
import com.oracle.pinyougou.pojo.TbSeckillGoods;
import com.oracle.pinyougou.pojo.TbSeckillOrder;
import com.oracle.pinyougou.seckill.service.SeckillGoodsService;
import com.oracle.pinyougou.seckill.service.SeckillOrderService;
import entity.Result;
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
    @Reference
    private WeixinPayService weixinPayService;

    @RequestMapping("/findList")
    public String findList(Map map) {
        List<TbSeckillGoods> goods = seckillGoodsService.findList();

        for (TbSeckillGoods good : goods) {
            good.setNumRate((int) (((double) good.getNum() -
                    (double) good.getStockCount()) / (double) good.getNum() * 100));
        }
        map.put("goods", goods);
        return "seckill-index";
    }

    @RequestMapping("/view")
    public String view(long id, Map map) {
        TbSeckillGoods tbSeckillGoods = seckillGoodsService.findOneFromRedis(id);
        map.put("goods", tbSeckillGoods);
        return "/seckill-item";
    }

    @RequestMapping("/submitOrder")
    @ResponseBody
    public Map submitOrder(Long seckillid) {
        String userid = "zhangsan";
        Map map = new HashMap();
        try {
            String orderid = orderService.submitOrder(seckillid, userid);
            map.put("flag", true);
            map.put("orderid", orderid);
        } catch (Exception e) {
            map.put("flag", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("/pay")
    public String pay(long orderid, Map map) {
        String userid = "zhangsan";
        //到缓存中查询订单，将订单传到付款页面，显示订单的信息
        TbSeckillOrder order = orderService.searchOrderFromRedisByUserId(userid, String.valueOf(orderid));
        map.put("order", order);
        return "/pay";
    }

    @RequestMapping("/createNative")
    @ResponseBody
    public Map createNative(String orderId) {
//1.获取当前登录用户
        String userId = "zhangsan";
//2.提取秒杀订单（从缓存 ）
        TbSeckillOrder seckillOrder = orderService.searchOrderFromRedisByUserId(userId, orderId);
//3.调用微信支付接口
        if (seckillOrder != null) {
            return weixinPayService.createNative(seckillOrder.getId() + "", (long)
                    (seckillOrder.getMoney().doubleValue() * 100) + "");
        }
        return null;
    }

    //查询支付状态
    @RequestMapping("/queryPayStatus")
    @ResponseBody
    public Result queryPayStatus(String out_trade_no) {
        //1.获取当前登录用户
        String username = "zhangsan";
        Result result = null;
        int x = 0;
        while (true) {
            Map<String, String> map = weixinPayService.queryPayStatus(out_trade_no);//调用查询
            if (map == null) {
                result = new Result(false, "支付发生错误");
                break;
            }
            if (map.get("trade_state").equals("SUCCESS")) {//支付成功
                result = new Result(true, "支付成功");
//保存订单
                orderService.saveOrderFromRedisToDb(username, Long.valueOf(out_trade_no)
                        , map.get("transaction_id"));
                break;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            x++;
            if (x >= 100) {
                result = new Result(false, "二维码超时");
// 关闭支付
                Map<String, String> payResult = weixinPayService.closePay(out_trade_no);
                if (payResult != null && "FAIL".equals(payResult.get("return_code"))) {
                    if ("ORDERPAID".equals(payResult.get("err_code"))) {

                        result = new Result(true, "支付成功");
//保存订单
                        orderService.saveOrderFromRedisToDb(username,
                                Long.valueOf(out_trade_no), map.get("transaction_id"));
                    }
                }
//删除订单
                if (result.isSuccess() == false) {
                    orderService.deleteOrderFromRedis(username,
                            Long.valueOf(out_trade_no));
                }
                break;
            }
        }
        return result;
    }


}
