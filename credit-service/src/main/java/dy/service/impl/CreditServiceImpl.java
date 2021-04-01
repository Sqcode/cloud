package dy.service.impl;

import dy.service.CreditService;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    @Override
    public void addCreditCount(Integer userId, Integer creditCount) {
        System.out.println("用户：" + userId + "需添加积分：" + creditCount);
    }
}
