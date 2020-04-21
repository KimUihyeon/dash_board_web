

export class WeatherModel {
    constructor(location, time , temp, feelsTemp, humidity , windSpeed, weather){
        this.location = location;
        this.time = time;
        this.feelsTemp = feelsTemp;
        this.humidity =humidity;
        this.temp = temp;
        this.windSpeed = windSpeed;
    }
}

export class WeatherOneDay extends WeatherModel {
    constructor(location, time , temp, feelsTemp, humidity, windSpeed , weather , maxTemp, minTemp){
        super(location, time , temp, feelsTemp, humidity, windSpeed, weather);
        this.maxTemp = maxTemp
        this.minTemp = minTemp;
    }

}

export class WeatherDetail{
    constructor(location, weatherOneDay , dayils){
        this.location = location;
        this.current = weatherOneDay;
        this.dayils = dayils;
    }

}