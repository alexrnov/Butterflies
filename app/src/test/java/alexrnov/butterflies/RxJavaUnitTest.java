package alexrnov.butterflies;

import org.junit.Before;
import org.junit.Test;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.google.common.truth.Truth.assertThat;

public class RxJavaUnitTest {
  String value;

  @Before
  public void initValue() {
    value = "";
  }

  @Test
  public void rxJavaTest() {
    Observable<String> observer = Observable.just("value"); // provides data
    Disposable disposable = observer.subscribe(s -> value = s); // callable as subscriber
    assertThat(value.equals("value")).isEqualTo(true);
    // dispose the subscription when not interested in the emitted data any more
    disposable.dispose();
  }
}
