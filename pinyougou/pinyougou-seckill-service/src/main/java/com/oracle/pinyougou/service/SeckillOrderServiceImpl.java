package com.oracle.pinyougou.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.oracle.pinyougou.mapper.TbSeckillGoodsMapper;
import com.oracle.pinyougou.pojo.TbSeckillGoods;
import com.oracle.pinyougou.pojo.TbSeckillOrder;
import com.oracle.pinyougou.seckill.service.SeckillOrderService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import util.Constant;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService{
    @Override
    public List<TbSeckillOrder> findAll() {
        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public void add(TbSeckillOrder seckillOrder) {

    }

    @Override
    public void update(TbSeckillOrder seckillOrder) {

    }

    @Override
    public TbSeckillOrder findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public PageResult findPage(TbSeckillOrder seckillOrder, int pageNum, int pageSize) {
        return null;
    }
@Autowired
private RedisTemplate redisTemplate;
    @Autowired
    private TbSeckillGoodsMapper seckillGoodsMapper;
    @Override
    public String submitOrder(Long seckillId, String userId) {
        //查询秒杀商品
        TbSeckillGoods seckillGoods=(TbSeckillGoods) redisTemplate.boundHashOps(Constant.SECKILLGOOD_KEY).get(seckillId);
       if(seckillGoods==null||seckillGoods.getStockCount()==0){
           throw new  RuntimeException("抢购失败");
       }
//        if(seckillGoods.getStockCount()==0){
//            throw new  RuntimeException("商品不存在");
//        }
       //减库存，更新缓存，如果为0，更新数据库，删除缓存
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
       redisTemplate.boundHashOps(Constant.SECKILLGOOD_KEY).put(seckillId,seckillGoods);
       if(seckillGoods.getStockCount()==0){
         seckillGoodsMapper.updateByPrimaryKey(seckillGoods);
         redisTemplate.boundHashOps(Constant.SECKILLGOOD_KEY).delete(seckillId);
       }
       //生成订单
        TbSeckillOrder order=new TbSeckillOrder();
        IdWorker idWorker=new IdWorker(0,0);
       order.setId(idWorker.nextId());
       order.setSeckillId(seckillGoods.getId());
       order.setMoney(seckillGoods.getCostPrice());
       order.setUserId(userId);
       order.setSellerId(seckillGoods.getSellerId());
       order.setCreateTime(new Date());
      // redisTemplate.boundHashOps(Constant.SECKILLORDER_KEY).put(userId,order);
        //hash 中userid 为key,hashnmap 为value,hashMap中的key为订单编号，值为订单对象

      Object obj= redisTemplate.boundHashOps(Constant.SECKILLORDER_KEY).get(userId);
      if(obj==null){
          Map map=new HashMap();
          map.put(String.valueOf(order.getId()),order);
          redisTemplate.boundHashOps(Constant.SECKILLORDER_KEY).put(userId,map);
      }else{
          Map map=(Map)obj;
          map.put(String.valueOf(order.getId()),order);
          redisTemplate.boundHashOps(Constant.SECKILLORDER_KEY).put(userId,map);

      }
      return  String.valueOf(order.getId());
    }

    @Override
    public TbSeckillOrder searchOrderFromRedisByUserId(String userId,String  orderid) {
       Map map= (Map)redisTemplate.boundHashOps(Constant.SECKILLORDER_KEY).get(userId);
        TbSeckillOrder order=(TbSeckillOrder)map.get(orderid);
        return order;
    }

    @Override
    public void saveOrderFromRedisToDb(String userId, Long orderId, String transactionId) {
    }

    @Override
    public void deleteOrderFromRedis(String userId, Long orderId) {

    }
}
