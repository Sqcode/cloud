package dy.service;

/**
 * 订单服务接口
 */
public interface OrderService {

    /**
     *
     * @param userId
     * @param goodsId
     * @param num
     */
    void createOrder(Integer userId, Integer goodsId, Integer num);

}
