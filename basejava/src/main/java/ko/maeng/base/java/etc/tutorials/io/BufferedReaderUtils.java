package ko.maeng.base.java.etc.tutorials.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferedReaderUtils {
	protected static List<String> readDataFiles(String filename) throws IOException {
		List<String> result = new ArrayList<>();

		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while(br.ready()) {
				result.add(br.readLine());
			}
			return result;
		}
	}
}
