package dy.feign;

import dy.feign.fallback.CreditServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "credit-service", fallback = CreditServiceFallback.class)
@Component
public interface CreditService {

    @RequestMapping("/credit/add/{userId}/{creditCount}")
    String addCreditCount (@PathVariable Integer userId, @PathVariable Integer creditCount);
}
