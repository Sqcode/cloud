package sqc.service.impl;

import sqc.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Override
    public void deduct(Integer id, Integer num) {
        System.out.println("商品：" + id + "，减数量：" + num);
    }
}
