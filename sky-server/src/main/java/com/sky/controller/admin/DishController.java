package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }
    /*
    * 菜品分页查询
    * */
    @GetMapping("/page")
    public Result<PageResult> page (DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    /*
    * 菜品批量删除
    * */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        dishService.deleteBatch(ids);

        return Result.success();
    }
    /*
    * 起售禁售菜品
    * */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){

        dishService.startOrStop(status,id);
        return Result.success();
    }
    /*
    * 根据id查询菜品
    * */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id){
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }
    /*
     * 修改菜品
     * */
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }
}
