package ko.maeng.base.javajigi.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
  public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
  public static final int LOTTO_NUMBER_UPPER_BOUND = 45;
  private static final List<LottoNumber> CACHE = new ArrayList<>();

  static {
    for(int i=LOTTO_NUMBER_LOWER_BOUND; i<=LOTTO_NUMBER_UPPER_BOUND; i++) {
      CACHE.add(new LottoNumber(i));
    }
  }

  private final int number;

  public LottoNumber(int number) {
    validate(number);
    this.number = number;
  }

  public static LottoNumber valueOf(int number) {
    LottoNumber lottoNumber = CACHE.get(number);

    if(Objects.isNull(lottoNumber)) {
      lottoNumber = new LottoNumber(number);
    }
    return lottoNumber;
  }

  public static List<LottoNumber> values() {
    return CACHE;
  }

  private void validate(int number) {
    if(number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_UPPER_BOUND) {
      throw new IllegalArgumentException("로또 번호가 유효하지 않습니다.");
    }
  }
}
