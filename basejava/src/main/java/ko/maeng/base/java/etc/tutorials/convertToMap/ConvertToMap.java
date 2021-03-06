package ko.maeng.base.java.etc.tutorials.convertToMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertToMap {
    public Map<String, String> listToMap(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getIsbn, Book::getName));
    }

    public Map<Integer, Book> listToMapWithDupKeyError(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity()));
    }

    public Map<Integer, Book> listToMapWithDupKey(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(), (exist, replace) -> exist));
    }

    public Map<Integer, Book> listToConcurrentmap(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    public TreeMap<String, Book> listToSortedMap(List<Book> books) {
        return books.stream()
                .collect(Collectors.toMap(Book::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new));
    }
}
