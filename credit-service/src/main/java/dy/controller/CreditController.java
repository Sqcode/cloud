package dy.controller;

import dy.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/add/{userId}/{creditCount}")
    public String addCreditCount (@PathVariable Integer userId, @PathVariable Integer creditCount) {
        System.out.println("/credit/add");
        creditService.addCreditCount(userId, creditCount);

        return "success";
    }

}
