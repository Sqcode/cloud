package sqc.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqc.entity.vo.Result;
import sqc.goods.entity.form.GoodsForm;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.param.GoodsParamsPage;
import sqc.goods.entity.po.Goods;
import sqc.goods.entity.vo.GoodsVO;
import sqc.goods.service.GoodsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("goods")
@Api("goods")
@Slf4j
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增", notes = "新增商品")
    @ApiImplicitParam(value = "goodsForm", required = true, dataType = "GoodsForm")
    public Result insert(@Valid @RequestBody GoodsForm goodsForm) {
        return Result.success(goodsService.insert(goodsForm.toPo(Goods.class)));
    }


    @GetMapping("list")
    public Result list (GoodsParams params) {
        List<Goods> list = goodsService.list(params);
        log.debug("list");
        return Result.success(list);
    }

    @PostMapping("listPage")
    @ApiOperation(value = "查询分页列表", notes = "查询商品的分页列表")
//    @Validated
    public Result listPage (@RequestBody GoodsParamsPage goodsParamsPage) {
        IPage<GoodsVO> goodsIPage = goodsService.listPage(goodsParamsPage);
        log.debug("listPage");
        return Result.success(goodsIPage);
    }
}
