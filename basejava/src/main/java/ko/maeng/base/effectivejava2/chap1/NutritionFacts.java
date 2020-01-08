package ko.maeng.base.effectivejava2.chap1;

public class NutritionFacts {
    // 빌더 패턴 적용을 통해 불변(Immutable) 객체를 만듬.
    // 빌더 패턴은 인자가 많은 생성자나 정적 팩터리가 필요한 클래스를 설계할 때,
    // 특히 대부분의 인자가 선택적 인자인 상황에 유용하다.
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class Builder {
        // 필수 인자
        private final int servingSize;
        private final int servings;

        // 선택적 인자 - 기본값으로 초기화
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val; return this;
        }
        public Builder fat(int val) {
            fat = val; return this;
        }
        public Builder sodium(int val) {
            sodium = val; return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val; return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getServings() {
        return servings;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }
}
