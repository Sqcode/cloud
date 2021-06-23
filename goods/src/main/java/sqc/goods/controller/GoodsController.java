package sqc.goods.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sqc.entity.vo.Result;
import sqc.goods.entity.form.GoodsForm;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.param.GoodsParamsPage;
import sqc.goods.entity.po.Goods;
import sqc.goods.service.GoodsService;
import sqc.goods.util.excel.ExcelReaderUtil;

import javax.validation.Valid;
import java.io.IOException;
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


    @PostMapping("list")
    public Result list (GoodsParams params) {
        return Result.success(goodsService.list(params));
    }

    @PostMapping("listPage")
    @ApiOperation(value = "查询分页列表", notes = "查询商品的分页列表")
//    @Validated
    public Result listPage (@RequestBody GoodsParamsPage goodsParamsPage) {
        return Result.success(goodsService.listPageVO(goodsParamsPage));
    }

    @PostMapping("/upload")
    @CrossOrigin
    public Result file (@RequestParam("file") MultipartFile file) {
        try {
            List<String[]> excelData = ExcelReaderUtil.getExcelData(file);
            for (String[] excelDatum : excelData) {
                for (String s : excelDatum) {
                    System.out.println(s);
                }
            }
            return Result.success(excelData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.fail();
    }
}
