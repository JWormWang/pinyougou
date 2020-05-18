package com.oracle.pinyougou.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.oracle.pinyougou.pojo.TbSeckillOrder;
import com.oracle.pinyougou.seckill.service.SeckillOrderService;
import entity.PageResult;

import java.util.List;
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

    @Override
    public void submitOrder(Long seckillId, String userId) {
    }

    @Override
    public TbSeckillOrder searchOrderFromRedisByUserId(String userId) {
        return null;
    }

    @Override
    public void saveOrderFromRedisToDb(String userId, Long orderId, String transactionId) {
    }

    @Override
    public void deleteOrderFromRedis(String userId, Long orderId) {

    }
}
