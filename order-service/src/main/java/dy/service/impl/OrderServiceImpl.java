package dy.service.impl;

import dy.feign.CreditService;
import dy.feign.StockService;
import dy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StockService stockService;
    @Autowired
    private CreditService creditService;
//    @Autowired
//    private AccountService accountService;

    @Override
    public void createOrder(Integer userId, Integer goodsId, Integer num) {

        System.out.println("创建订单：商品：" + goodsId + "，数量：" + num);
        stockService.deduct(goodsId, num);// 减库存

//        accountService.debit(userId, orderMoney);
//
//        Order order = new Order();
//        order.userId = userId;
//        order.commodityCode = commodityCode;
//        order.count = orderCount;
//        order.money = orderMoney;

        // INSERT INTO orders ...

        creditService.addCreditCount(userId, 100);

    }
}
