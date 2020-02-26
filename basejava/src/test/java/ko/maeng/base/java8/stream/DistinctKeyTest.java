package ko.maeng.base.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DistinctKeyTest {
    @Test
    public void name() {
        Product product = new Product(1l, "뮤즈", dbRecords());
        List<OptionGroupSpec> update = getRecords();

        Stream<OptionGroupSpec> stream = product.getOptionGroupSpecs().stream();
        Stream<OptionGroupSpec> updateStream = update.stream();
        List<OptionGroupSpec> list = Stream.concat(stream, updateStream)
                .filter(distinctByKeys(OptionGroupSpec::getDetail)).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(list.size());

        for(OptionGroupSpec spec : list){
            System.out.println(spec.getDetail());
            for(OptionSpec each : spec.getOptionSpecs()){
                System.out.println(each);
            }
        }
    }

    public static <T> Predicate<T> distinctByKeys(Function<? super T, Object>... keyExtractors) {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
        return t -> {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());
            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private static List<OptionGroupSpec> getRecords() {
        ArrayList<OptionGroupSpec> records = new ArrayList<>();
        ArrayList<OptionSpec> optionSpecs = new ArrayList<>();

        optionSpecs.addAll(Arrays.asList(
                new OptionSpec(1l, "성인", 19000),
                new OptionSpec(2l, "청소년", 16000),
                new OptionSpec(3l, "아이", 13000)
                ));


        records.add(new OptionGroupSpec(2l, "AGE", optionSpecs));

        return records;
    }

    private static List<OptionGroupSpec> dbRecords() {
        ArrayList<OptionGroupSpec> records = new ArrayList<>();
        ArrayList<OptionSpec> optionSpecs1 = new ArrayList<>();

        optionSpecs1.addAll(Arrays.asList(
                new OptionSpec(1l, "도록패키지", 19000),
                new OptionSpec(2l, "오디오패키지", 14000),
                new OptionSpec(3l, "커플패키지", 12000)
        ));

        ArrayList<OptionSpec> optionSpecs2 = new ArrayList<>();

        optionSpecs2.addAll(Arrays.asList(
                new OptionSpec(1l, "성인", 14000),
                new OptionSpec(2l, "청소년", 12000),
                new OptionSpec(3l, "아이", 10000)
        ));

        records.addAll(Arrays.asList(
                new OptionGroupSpec(1l, "PACKAGE", optionSpecs1),
                new OptionGroupSpec(2l, "AGE", optionSpecs2)
        ));

        return records;
    }

    static class Product{
        private long id;
        private String name;
        private List<OptionGroupSpec> optionGroupSpecs;

        public Product(long id, String name, List<OptionGroupSpec> optionGroupSpecs) {
            this.id = id;
            this.name = name;
            this.optionGroupSpecs = optionGroupSpecs;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<OptionGroupSpec> getOptionGroupSpecs() {
            return optionGroupSpecs;
        }

        public void setOptionGroupSpecs(List<OptionGroupSpec> optionGroupSpecs) {
            this.optionGroupSpecs = optionGroupSpecs;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return id == product.id &&
                    Objects.equals(name, product.name) &&
                    Objects.equals(optionGroupSpecs, product.optionGroupSpecs);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", optionGroupSpecs=" + optionGroupSpecs +
                    '}';
        }
    }

    static class OptionGroupSpec{
        private long id;
        private String detail;
        private List<OptionSpec> optionSpecs;

        public OptionGroupSpec(long id, String detail, List<OptionSpec> optionSpecs) {
            this.id = id;
            this.detail = detail;
            this.optionSpecs = optionSpecs;
        }

        public OptionGroupSpec(long id, String detail) {
            this.id = id;
            this.detail = detail;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public List<OptionSpec> getOptionSpecs() {
            return optionSpecs;
        }

        public void setOptionSpecs(List<OptionSpec> optionSpecs) {
            this.optionSpecs = optionSpecs;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            result = prime * result + ((detail == null) ? 0 : detail.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OptionGroupSpec that = (OptionGroupSpec) o;
            return id == that.id &&
                    Objects.equals(detail, that.detail) &&
                    Objects.equals(optionSpecs, that.optionSpecs);
        }

        @Override
        public String toString() {
            return "OptionGroupSpec{" +
                    "id=" + id +
                    ", detail='" + detail + '\'' +
                    ", optionSpecs=" + optionSpecs +
                    '}';
        }
    }

    static class OptionSpec{
        private long id;
        private String name;
        private int price;

        public OptionSpec(long id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OptionSpec that = (OptionSpec) o;
            return id == that.id &&
                    price == that.price &&
                    Objects.equals(name, that.name);
        }

        @Override
        public String toString() {
            return "OptionSpec{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
