package sqc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "stock-service")
public interface StockService {

    /**
     * 减库存
     * @param goodsId 商品id
     * @param num 购买数量
     */
    @RequestMapping("/stock/deduct/{goodsId}/{num}")
    void deduct(@PathVariable Integer goodsId, @PathVariable Integer num);

}
