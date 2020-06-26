package ko.maeng.oop.boardoop.common;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
public class Id {
	private final UUID id;

	public Id() {
		this.id = UUID.randomUUID();
	}
}
