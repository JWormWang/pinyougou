package com.oracle.pinyougou.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.oracle.pinyougou.mapper.TbSeckillGoodsMapper;
import com.oracle.pinyougou.pojo.TbSeckillGoods;
import com.oracle.pinyougou.pojo.TbSeckillGoodsExample;
import com.oracle.pinyougou.seckill.service.SeckillGoodsService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService{
   @Autowired
   private TbSeckillGoodsMapper seckillGoodsMapper;
   @Autowired
   private RedisTemplate redisTemplate;
    @Override
    public List<TbSeckillGoods> findAll() {
        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public void add(TbSeckillGoods seckillGoods) {

    }

    @Override
    public void update(TbSeckillGoods seckillGoods) {

    }

    @Override
    public TbSeckillGoods findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {

    }

    @Override
    public PageResult findPage(TbSeckillGoods seckillGoods, int pageNum, int pageSize) {
        return null;
    }


    @Override
    public List<TbSeckillGoods> findList() {
        //从缓存读取
        List<TbSeckillGoods> list=redisTemplate.boundHashOps("seckillgoods").values();
        //判断list是否为空,若为空，则缓存中没有秒杀商品数据
        if(list==null||list.size()==0){
            //从数据库读取
            TbSeckillGoodsExample goodsExample = new TbSeckillGoodsExample();
            TbSeckillGoodsExample.Criteria criteria = goodsExample.createCriteria();
            criteria.andStatusEqualTo("1"); //审核通过
            criteria.andStartTimeLessThanOrEqualTo(new Date()); //开始时间小于等于 当前时间
            criteria.andEndTimeGreaterThanOrEqualTo(new Date()); //结束时间大于等于 当前时间
             list = seckillGoodsMapper.selectByExample(goodsExample);
             //数据库读的内容写入redis
            for(TbSeckillGoods good:list){
                redisTemplate.boundHashOps("seckillgoods").put(good.getId(),good);
            }
            System.out.println("从数据库读取秒杀商品");
        }else{
            System.out.println("从缓存读取秒杀商品");
        }
      //按价格排序
        Collections.sort(list, new Comparator<TbSeckillGoods>() {
            @Override
            public int compare(TbSeckillGoods o1, TbSeckillGoods o2) {
                if(o1.getCostPrice().doubleValue()>o2.getCostPrice().doubleValue()){
                    return  1;
                }
                return -1;
            }
        });

        return list;
    }

    @Override
    public TbSeckillGoods findOneFromRedis(Long id) {
       return null;
    }
}
