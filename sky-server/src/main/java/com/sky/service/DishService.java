package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    public void saveWithFlavor(DishDTO dishDTO);

    /*
     * 菜品分页查询
     * */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /*
    * 菜品批量删除
    * */
    void deleteBatch(List<Long> ids);

    /*
    * 起售禁售菜品
    * */
    void startOrStop(Integer status, Long id);

    /*
    * 根据id查询菜品
    * */
    DishVO getByIdWithFlavor(Long id);

    /*
    * 修改菜品
    * */
    void updateWithFlavor(DishDTO dishDTO);
}
