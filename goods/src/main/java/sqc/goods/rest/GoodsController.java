package sqc.goods.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqc.entity.vo.Result;
import sqc.goods.entity.form.GoodsForm;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.po.Goods;
import sqc.goods.entity.vo.GoodsVO;
import sqc.goods.service.GoodsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("goods")
@Api("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增", notes = "新增商品")
    @ApiImplicitParam(value = "goodsForm", required = true, dataType = "GoodsForm")
    public int insert(@Valid @RequestBody GoodsForm goodsForm) {
        return goodsService.insert(goodsForm.toPo(Goods.class));
    }


    @GetMapping("list")
    public Result list (GoodsParams params) {
        List<Goods> list = goodsService.list(params);
        System.out.println("list");
        return Result.success(list);
    }

    @RequestMapping("listPage")
    public Result listPage (@RequestBody  Page page) {
        IPage<GoodsVO> goodsIPage = goodsService.listPage(page);
        System.out.println("listPage");
        return Result.success(goodsIPage);
    }
}
