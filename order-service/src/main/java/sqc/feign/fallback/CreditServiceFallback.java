package sqc.feign.fallback;


import sqc.feign.CreditService;
import org.springframework.stereotype.Component;

@Component
public class CreditServiceFallback implements CreditService {
    @Override
    public String addCreditCount(Integer userId, Integer creditCount) {
        System.out.println("调用积分服务失败，降级处理: - 用户" + userId + "需加积分：" + creditCount);
        return "error";
    }
}
