package org.rcgonzalezf.weather.openweather.converter;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rcgonzalezf.weather.BuildConfig;
import org.rcgonzalezf.weather.R;
import org.rcgonzalezf.weather.common.models.ForecastData;
import org.rcgonzalezf.weather.tests.ConverterHelperTest;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, sdk = 21)
public class OpenWeatherApiModelConverterTest extends ConverterHelperTest {

  private OpenWeatherApiModelConverter mOpenWeatherApiModelConverter;
  private List<ForecastData> mModel;
  private ForecastData mForecastData;

  @Before public void initModelConverter() {
    mOpenWeatherApiModelConverter = new OpenWeatherApiModelConverter();
    mOpenWeatherApiModelConverter = spy(mOpenWeatherApiModelConverter);
  }

  @Test public void shouldReturnModelWithOneCountryGivenInputStreamByCityIdMoscow()
      throws IOException {
    givenInputStreamByCityIdMoscow();
    whenGenerateModel();
    thenShouldHaveOneElement();
  }

  @Test public void shouldReturnModelWithValuesForGivenCountryInputStreamByCityIdMoscow()
      throws IOException {
    givenInputStreamByCityIdMoscow();
    whenGenerateModel();
    thenShouldHaveOneElement();
    thenNameShouldBe("");
    thenSpeedShouldBe(0.0);
    thenDegShouldBe(0.0);
    thenTempShouldBe(0.0);
    thenHumidityShouldBe(0L);
    thenSunriseShouldBe(0L);
    thenSunsetShouldBe(0L);
  }

  @Test public void shouldReturnModelWithValuesForCountriesInputStreamFoundByName()
      throws IOException {
    givenInputStreamByCityNameLondon();
    whenGenerateModel();
    thenNameShouldBe("");
    thenSpeedShouldBe(0.0);
    thenDegShouldBe(0.0);
    thenTempShouldBe(0.0);
    thenHumidityShouldBe(0L);
    thenSunriseShouldBe(0L);
    thenSunsetShouldBe(0L);
  }


  @Test public void shouldReturnModelWithValuesForCountriesInputStreamFoundByLatLon()
      throws IOException {
    givenInputStreamByLatLon();
    whenGenerateModel();
    thenShouldHaveOneElement();
    thenNameShouldBe("");
    thenSpeedShouldBe(0.0);
    thenDegShouldBe(0.0);
    thenTempShouldBe(0.0);
    thenHumidityShouldBe(0L);
    thenSunriseShouldBe(0L);
    thenSunsetShouldBe(0L);
  }

  private void givenInputStreamByLatLon() {
    givenJson(R.raw.shunzenji_forecast_by_latlon);
  }

  private void givenInputStreamByCityNameLondon() {
    givenJson(R.raw.find_london_like);
  }

  private void thenSunsetShouldBe(long expected) {
    assertEquals(expected, mForecastData.mSunset);
  }

  private void thenSunriseShouldBe(long expected) {
    assertEquals(expected, mForecastData.mSunrise);
  }

  private void thenHumidityShouldBe(long expected) {
    assertEquals(expected, mForecastData.mHumidity);
  }

  private void thenTempShouldBe(double expected) {
    assertEquals(expected, mForecastData.mTemp, 0.1);
  }

  private void thenDegShouldBe(double expected) {
    assertEquals(expected, mForecastData.mDeg, 0.1);
  }

  private void thenSpeedShouldBe(double expected) {
    assertEquals(expected, mForecastData.mSpeed, 0.1);
  }

  private void thenNameShouldBe(String expected) {
    assertEquals(expected, mForecastData.mName);
  }

  private void thenShouldHaveOneElement() {
    assertEquals(1, mModel.size());
    mForecastData = mModel.get(0);
  }

  private void whenGenerateModel() throws IOException {
    mOpenWeatherApiModelConverter.fromInputStream(mInputStream);
    mModel = mOpenWeatherApiModelConverter.getModel();
  }

  private void givenInputStreamByCityIdMoscow() throws IOException {
    givenJson(R.raw.moscow_forecast);
  }
}