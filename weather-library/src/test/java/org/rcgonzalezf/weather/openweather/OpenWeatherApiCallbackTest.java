package org.rcgonzalezf.weather.openweather;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.rcgonzalezf.weather.common.listeners.OnUpdateWeatherListListener;
import org.rcgonzalezf.weather.common.models.Forecast;
import org.rcgonzalezf.weather.openweather.network.OpenWeatherApiError;
import org.rcgonzalezf.weather.openweather.network.OpenWeatherApiResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class OpenWeatherApiCallbackTest {

  private OpenWeatherApiCallback uut;

  private boolean mListUpdated;
  private boolean mOnErrorCalled;
  private OnUpdateWeatherListListener mOnUpdateWeatherListListener = new OnUpdateWeatherListListener() {

    @Override public void updateList(List<Forecast> forecastList) {
      mListUpdated = true;
    }

    @Override public void onError(String error) {
      mOnErrorCalled = true;
    }
  };

  @Test
  public void shouldUpdateTheListOnSuccess() {
    givenUut();

    whenCallbackIsNotifiedForSuccess(mock(OpenWeatherApiResponse.class));

    thenListUpdated(true);
  }


  @Test
  public void shouldNotifyErrorOnError() {
    givenUut();

    whenCallbackIsNotifiedForError(new OpenWeatherApiError());

    thenOnErrorIsCalled(true);
  }

  private void thenOnErrorIsCalled(boolean expected) {
    assertEquals(expected, mOnErrorCalled);
  }

  private void whenCallbackIsNotifiedForError(OpenWeatherApiError openWeatherApiError) {
    uut.onError(openWeatherApiError);
  }

  private void givenUut() {
    uut = new OpenWeatherApiCallback(mOnUpdateWeatherListListener);
  }

  private void thenListUpdated(boolean expected) {
    assertEquals(expected, mListUpdated);
  }

  private void whenCallbackIsNotifiedForSuccess(OpenWeatherApiResponse openWeatherApiResponse) {
    uut.onSuccess(openWeatherApiResponse);
  }
}
