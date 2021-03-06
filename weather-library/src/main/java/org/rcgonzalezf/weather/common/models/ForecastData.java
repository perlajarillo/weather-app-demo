package org.rcgonzalezf.weather.common.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import org.rcgonzalezf.weather.openweather.model.City;

public class ForecastData {

  @SerializedName("city")
  private City mCity;
  @SerializedName("cnt")
  private int mCount;
  @SerializedName("list")
  private List<WeatherData> mWeatherList;


  public ForecastData(
      City city,
      int count) {
    setCity(city);
    setCount(count);
    mWeatherList = new ArrayList<>(count);
  }

  @Override public String toString() {
    return "ForecastData [name=" + getCity().getName() + ", count=" + mCount +"";
  }

  public City getCity() {
    return mCity;
  }

  public void setCity(City mCity) {
    this.mCity = mCity;
  }

  public int getCount() {
    return mCount;
  }

  public void setCount(int count) {
    mCount = count;
  }

  public List<WeatherData> getWeatherList() {
    return mWeatherList;
  }

  public void addWeatherItem(WeatherData weatherData) {
    mWeatherList.add(weatherData);
  }

}
