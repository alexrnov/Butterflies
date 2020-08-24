package alexrnov.butterflies;

import org.junit.Before;
import org.junit.Test;
import io.reactivex.Observable;

import static com.google.common.truth.Truth.assertThat;

public class RxJavaUnitTest {
  String value;

  @Before
  public void initValue() {
    value = "";
  }

  @Test
  public void rxJavaTest() {
    Observable<String> observer = Observable.just("value");
    observer.subscribe(s -> value = s);
    assertThat(value.equals("value")).isEqualTo(true);
  }
}
