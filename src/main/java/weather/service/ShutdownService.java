package weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.model.PowerLine;
import weather.model.Shutdown;
import weather.model.Tower;
import weather.model.Weather;
import weather.respository.PowerLineRepository;
import weather.respository.ShutdownRepository;

import java.util.List;

@Service
public class ShutdownService {

    private WeatherService weatherService;
    private ShutdownRepository shutdownRepository;
    private PowerLineRepository powerLineRepository;

    @Autowired
    public ShutdownService(WeatherService weatherService, ShutdownRepository shutdownRepository,
                           PowerLineRepository powerLineRepository) {
        this.weatherService = weatherService;
        this.shutdownRepository = shutdownRepository;
        this.powerLineRepository = powerLineRepository;
    }

    public Shutdown shutdownOnPowerLine(PowerLine powerLine) {
        var shutdown = new Shutdown();
        var powerLineOptional = powerLineRepository.findById(powerLine.getPowerLineId());
        if (powerLineOptional.isEmpty()) {
            return null;
        }
        powerLine = powerLineOptional.get();
        List<Tower> towers = powerLine.getTowers();
        double lat = 0;
        double lon = 0;
        for (Tower pl : towers) {
            lat += pl.getLat();
            lon += pl.getLon();
        }
        Weather weather = weatherService.getCurrentWeatherForCoord(lat / towers.size(), lon / towers.size());
        weatherService.save(weather);
        shutdown.setPowerLine(powerLine);
        shutdown.setWeather(weather);
        return shutdownRepository.save(shutdown);
    }
}
