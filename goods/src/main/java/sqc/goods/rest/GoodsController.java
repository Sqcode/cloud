package sqc.goods.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sqc.entity.vo.Result;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.po.Goods;
import sqc.goods.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("list")
    public Result list (GoodsParams params) {
        List<Goods> list = goodsService.list(params);
        return Result.success(list);
    }
}
