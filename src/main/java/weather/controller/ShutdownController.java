package weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weather.model.PowerLine;
import weather.model.Shutdown;
import weather.service.ShutdownService;

@RestController
@RequestMapping("/shutdowns")
public class ShutdownController {

    private ShutdownService shutdownService;

    @Autowired
    public ShutdownController(ShutdownService shutdownService) {
        this.shutdownService = shutdownService;
    }

    @PostMapping("/power-lines/{powerLineId}")
    public ResponseEntity<Shutdown> createShutdown(@PathVariable("powerLineId") long powerLineId) {
        PowerLine powerLine = new PowerLine();
        powerLine.setPowerLineId(powerLineId);
        Shutdown shutdown = shutdownService.shutdownOnPowerLine(powerLine);
        return ResponseEntity.status(HttpStatus.OK).body(shutdown);
    }
}
