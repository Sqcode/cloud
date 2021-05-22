package sqc.goods.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sqc.goods.dao.GoodsMapper;
import sqc.goods.entity.param.GoodsParams;
import sqc.goods.entity.param.GoodsParamsPage;
import sqc.goods.entity.po.Goods;
import sqc.goods.entity.vo.GoodsVO;
import sqc.goods.service.GoodsService;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @CreateCache(name = "goods-", cacheType = CacheType.BOTH, expire = 120)
    private Cache<String, Goods> goodsCache;

    @Autowired
    GoodsMapper goodsMapper;

    @Cached(name = "goods::", key = "#goods.id", expire = 60)
    @Override
    public int insert(Goods goods) {
        int insert = goodsMapper.insert(goods);
        goodsCache.put(goods.getId(), goods);
        return insert;
    }

    @Override
    public List<Goods> list(GoodsParams goodsParams) {

        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flag", true);

        List<Goods> list = this.list(queryWrapper);

        return list;
    }

    @Override
    public IPage<GoodsVO> listPage(GoodsParamsPage goodsParamsPage) {
        IPage iPage = baseMapper.selectListPage(goodsParamsPage.getPage());
//
//        IPage<Goods> goodsIPage = goodsMapper.selectListPage(page);
        return iPage;
    }
}
