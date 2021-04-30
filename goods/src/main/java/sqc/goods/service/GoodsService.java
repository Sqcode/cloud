package sqc.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.po.Goods;
import sqc.goods.entity.vo.GoodsVO;

import java.util.List;

public interface GoodsService extends IService<Goods> {

    int insert(Goods goods);

    List<Goods> list(GoodsParams goodsParams);

    IPage<GoodsVO> listPage(Page<?> page);

}
