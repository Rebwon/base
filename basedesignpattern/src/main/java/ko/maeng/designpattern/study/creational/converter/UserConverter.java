package ko.maeng.designpattern.study.creational.converter;

public class UserConverter extends Converter<UserDto, User> {
	public UserConverter() {
		super(UserConverter::convertToEntity, UserConverter::convertToDto);
	}

	private static UserDto convertToDto(User user) {
		return new UserDto(user.getFirstName(), user.getLastName(), user.isActive(), user.getUserId());
	}

	private static User convertToEntity(UserDto dto) {
		return new User(dto.getFirstName(), dto.getLastName(), dto.isActive(), dto.getEmail());
	}
}
