package dy.controller;


import dy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单请求controller
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create/{userId}/{goodsId}/{num}")
    public String createOrder (@PathVariable Integer userId, @PathVariable Integer goodsId, @PathVariable Integer num) {
        orderService.createOrder(userId, goodsId, num);// 创建
        return "success";
    }

    @RequestMapping("index")
    public String index () {
        System.out.println("here is order-service:9000");
        return "here is order-service:9000";
    }

//    @GetMapping("/stock/deduct/{id}/{num}")
//    public String rest (@PathVariable Integer id, @PathVariable Integer num) {
//        return restTemplate.getForObject("http://stock-service/stock/deduct/" + id + "/" + num, String.class);
//    }

}
