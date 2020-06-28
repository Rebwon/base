package ko.maeng.oop.boardoop.activity;

import java.util.ArrayList;
import java.util.List;

import ko.maeng.oop.boardoop.common.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Activities {
	private final List<Activity> activities = new ArrayList<>();

	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	public Integer calculateTotalScore(Id accountId) {
		return activities.stream()
			.filter(activity -> activity.getAccount().getId().equals(accountId))
			.mapToInt(Activity::getScore)
			.sum();
	}
}
