package com.sky.service;

import com.sky.dto.SetmealDTO;

public interface SetmealService {
    /*
    * 新增套餐以及菜品
    * */
    void saveWithDish(SetmealDTO setmealDTO);
}
