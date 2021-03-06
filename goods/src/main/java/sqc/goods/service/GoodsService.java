package sqc.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.param.GoodsParamsPage;
import sqc.goods.entity.po.Goods;
import sqc.goods.entity.vo.GoodsVO;

import java.util.List;

public interface GoodsService extends IService<Goods> {

    int insert(Goods goods);

    List<Goods> list(GoodsParams goodsParams);

    IPage<Goods> listPage(GoodsParamsPage goodsParamsPage);

    /**
     * 自定义Model，VO
     * @param goodsParamsPage
     * @return
     */
    IPage<GoodsVO> listPageVO(GoodsParamsPage goodsParamsPage);

}
