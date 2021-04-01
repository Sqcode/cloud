package dy.service;

/**
 * 库存
 */
public interface StockService {

    /**
     * 减库存
     * @param id 商品id
     * @param num 购买数量
     */
    void deduct(Integer id, Integer num);

}
