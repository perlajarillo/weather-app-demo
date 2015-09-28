package org.rcgonzalezf.weather.openweather;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.rcgonzalezf.weather.common.ServiceConfig;
import org.rcgonzalezf.weather.common.WeatherProvider;
import org.rcgonzalezf.weather.common.WeatherRepository;
import org.rcgonzalezf.weather.common.models.ForecastData;
import org.rcgonzalezf.weather.openweather.network.OpenWeatherApiRequestParameters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class) public class OpenWeatherApiRepositoryTest {

  private WeatherRepository<OpenWeatherApiRequestParameters> mWeatherRepository;
  private ServiceConfig mServiceConfig;
  private ServiceConfig mRealServiceConfig;
  private OpenWeatherApiRequestParameters mRequestParameters;
  private List<ForecastData> mForecastData;

  @Before public void setUpServiceConfig() {
    mRealServiceConfig = ServiceConfig.getInstance();
    mServiceConfig = spy(mRealServiceConfig);
    ServiceConfig.setInstance(mServiceConfig);
    when(mServiceConfig.getApiKey()).thenReturn("someString");
    when(mServiceConfig.getWeatherProvider()).thenReturn(WeatherProvider.OpenWeather);
    mWeatherRepository = mServiceConfig.getWeatherRepository();
  }

  @After public void rollbackServiceConfig() {
    ServiceConfig.setInstance(mRealServiceConfig);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void shouldReturnExceptionThisRepositoryDoesNotAcceptSyncCalls() {
    givenRequestParametersWithCityId(123456);
    whenGettingWeather();
    thenWeatherDataListShouldHaveOneElement();
  }

  private void thenWeatherDataListShouldHaveOneElement() {
    assertEquals(1, mForecastData.size());
  }

  private void whenGettingWeather() {
    mForecastData = mWeatherRepository.findWeather(mRequestParameters);
  }

  private void givenRequestParametersWithCityId(Integer someCityId) {
    mRequestParameters =
        new OpenWeatherApiRequestParameters.OpenWeatherApiRequestBuilder().withCityId(someCityId)
            .build();
  }
}
