package sqc.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.po.Goods;

import java.util.List;

public interface GoodsService extends IService<Goods> {

    List<Goods> list(GoodsParams goodsParams);
}
