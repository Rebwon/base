package ko.maeng.base.java.etc.tutorials.io;

import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class BufferedReaderUtilsTest {
	protected static final String DATA_FILE = "src/test/resources/sampleEmployee.txt";
	private List<String> lines;

	@Before
	public void setUp() throws Exception {
		lines = BufferedReaderUtils.readDataFiles(DATA_FILE);
	}

	@Test
	public void insertTest() {
		String[] arr = lines.get(0).split(" ");
		Employee employee = new Employee();
		for (int i = 0; i < arr.length; i++) {
			if(i == 0) {
				employee.setId(arr[i]);
			} else if(i == 1) {
				employee.setName(arr[i]);
			} else if(i == 2) {
				employee.setGrade(arr[i]);
			} else {
				employee.setEmail(arr[i]);
			}
		}
		System.out.println(employee.toString());
	}

	static class Employee {
		 private String id;
		 private String name;
		 private String grade;
		 private String email;

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getGrade() {
			return grade;
		}

		public String getEmail() {
			return email;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Employee employee = (Employee)o;
			return Objects.equals(id, employee.id) &&
				Objects.equals(name, employee.name) &&
				Objects.equals(grade, employee.grade) &&
				Objects.equals(email, employee.email);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, name, grade, email);
		}

		@Override
		public String toString() {
			return "Employee{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", grade='" + grade + '\'' +
				", email='" + email + '\'' +
				'}';
		}
	}
}