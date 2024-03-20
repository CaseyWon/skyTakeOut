package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api(tags="店铺营业状态")
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;
    @ApiOperation("查询店铺状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){
        log.info("查询店铺状态");
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get("SHOP_STATUS");
        return Result.success(shopStatus);
    }
}
