package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    //抽取出删除缓存方法
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    //新增菜品
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavor(dishDTO);
        //清理缓存
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key);
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

        //删除缓存
        cleanCache("dish_*");
        return Result.success();
    }
    /*
    * 起售禁售菜品
    * */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){

        dishService.startOrStop(status,id);
        //删除缓存
        cleanCache("dish_*");
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
        //清除缓存
        cleanCache("dish_*");
        return Result.success();
    }
    /*
    * 根据分类id查询菜品
    * */
    @GetMapping("/list")
    public Result<List<Dish>> selectByCategoryId(Long categoryId){
        List<Dish> dish = dishService.selectByCategoryId(categoryId);
        return Result.success(dish);
    }
}
