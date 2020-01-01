package ko.maeng.base.java.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
    private Account account;

    @Before
    public void setup(){
        account = new Account(10000);
    }

    @Test
    public void 잔고를_조회한다(){
        assertThat(account.getBalance()).isEqualTo(10000);
    }

    @Test
    public void 입금한다() {
        account.deposit(50000);
        assertThat(account.getBalance()).isEqualTo(60000);
    }

    @Test
    public void 출금한다() {
        account.withdraw(5000);
        assertThat(account.getBalance()).isEqualTo(5000);
    }
}