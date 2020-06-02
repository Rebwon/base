package ko.maeng.cleancoders.tdd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MailBoxTest {
	@Test
	void newMailBox_should_empty() {
		MailBox mailbox = new MailBox();
		assertThat(mailbox.messageCount()).isEqualTo(0);
	}

	private class MailBox {
		public int messageCount() {
			return 0;
		}
	}
}
