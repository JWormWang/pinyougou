package com.oracle.pinyougou.mapper;

import com.oracle.pinyougou.pojo.TbAreas;
import com.oracle.pinyougou.pojo.TbAreasExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TbAreasMapper {
    int countByExample(TbAreasExample example);

    int deleteByExample(TbAreasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAreas record);

    int insertSelective(TbAreas record);

    List<TbAreas> selectByExample(TbAreasExample example);

    TbAreas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAreas record, @Param("example") TbAreasExample example);

    int updateByExample(@Param("record") TbAreas record, @Param("example") TbAreasExample example);

    int updateByPrimaryKeySelective(TbAreas record);

    int updateByPrimaryKey(TbAreas record);
}