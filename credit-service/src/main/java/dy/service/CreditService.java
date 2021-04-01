package dy.service;

public interface CreditService {

    /**
     * 加积分
     * @param userId
     * @param creditCount
     */
    void addCreditCount (Integer userId, Integer creditCount);
}
