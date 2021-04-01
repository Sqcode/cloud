package dy.service;

public interface AccountService {

    /**
     * 从用户账户中借出
     */
    void debit(Integer userId, Integer money);
}
