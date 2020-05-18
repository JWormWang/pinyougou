package com.oracle.pinyougou.mapper;

import com.oracle.pinyougou.pojo.TbSpecificationOption;
import com.oracle.pinyougou.pojo.TbSpecificationOptionExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TbSpecificationOptionMapper {
    int countByExample(TbSpecificationOptionExample example);

    int deleteByExample(TbSpecificationOptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecificationOption record);

    int insertSelective(TbSpecificationOption record);

    List<TbSpecificationOption> selectByExample(TbSpecificationOptionExample example);

    TbSpecificationOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbSpecificationOption record, @Param("example") TbSpecificationOptionExample example);

    int updateByExample(@Param("record") TbSpecificationOption record, @Param("example") TbSpecificationOptionExample example);

    int updateByPrimaryKeySelective(TbSpecificationOption record);

    int updateByPrimaryKey(TbSpecificationOption record);
}