package com.oracle.pinyougou.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.oracle.pinyougou.pojo.TbSeckillGoods;
import com.oracle.pinyougou.seckill.service.SeckillGoodsService;
import entity.PageResult;

import java.util.List;
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService{

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
        return null;
    }

    @Override
    public TbSeckillGoods findOneFromRedis(Long id) {
       return null;
    }
}
