package org.rcgonzalezf.weather.common.network;

import java.util.List;
import org.rcgonzalezf.weather.WeatherData;

public interface ApiResponse {

  List<WeatherData> getData();

}