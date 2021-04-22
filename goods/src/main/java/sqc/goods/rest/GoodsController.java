package sqc.goods.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        System.out.println("list");
        return Result.success(list);
    }

    @RequestMapping("listPage")
    public Result listPage (@RequestBody  Page page) {
        IPage<Goods> goodsIPage = goodsService.listPage(page);
        System.out.println("listPage");
        return Result.success(goodsIPage);
    }
}
