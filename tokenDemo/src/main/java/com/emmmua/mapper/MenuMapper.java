package com.emmmua.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emmmua.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
