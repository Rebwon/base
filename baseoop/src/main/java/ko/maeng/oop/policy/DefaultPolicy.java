package ko.maeng.oop.policy;

public class DefaultPolicy implements Policy<PriceInfoHelper, PriceInfo> {

  @Override
  public boolean evaluate(PriceInfoHelper priceInfoHelper, PolicyConditions conditions) {
    // return 해당 정책 적용 여부
    return false;
  }

  @Override
  public PriceInfo apply(PriceInfoHelper priceInfoHelper, PolicyConditions conditions) {
    // return 해당 정책 반영 결과
    return null;
  }
}
