package dy.controller;

import dy.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/deduct/{id}/{num}")
    public String deductStock (@PathVariable Integer id, @PathVariable Integer num) {
        System.out.println("/stock/deduct");
        stockService.deduct(id, num);// 减库存

        return "success";
    }

}
