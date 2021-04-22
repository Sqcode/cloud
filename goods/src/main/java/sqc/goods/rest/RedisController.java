package sqc.goods.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sqc.entity.vo.Result;
import sqc.goods.entity.po.Goods;

import java.io.Serializable;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    @PostMapping("/set")
    public Result setPOJO (@RequestBody Goods goods) {
        redisTemplate.opsForValue().set("goods", goods);
        return Result.success();
    }

    @RequestMapping("/get")
    public Result getPOJO(){
        return Result.success(redisTemplate.opsForValue().get("goods"));
    }

}
