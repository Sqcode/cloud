package sqc.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sqc.goods.dao.GoodsMapper;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.po.Goods;
import sqc.goods.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {


    @Override
    public List<Goods> list(GoodsParams goodsParams) {

        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flag", true);

        List<Goods> list = this.list(queryWrapper);

        return list;
    }
}
