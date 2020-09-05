package ko.maeng.base.javajigi.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
  private static final int VALID_SIZE = 6;

  public static List<LottoNumber> generate() {
    List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
    Collections.shuffle(lottoNumbers);

    return lottoNumbers.subList(0, VALID_SIZE);
  }

}
