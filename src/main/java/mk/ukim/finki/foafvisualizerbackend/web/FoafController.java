package mk.ukim.finki.foafvisualizerbackend.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.foafvisualizerbackend.model.FoafResponse;
import mk.ukim.finki.foafvisualizerbackend.service.FoafService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@RequestMapping("/foaf")
public class FoafController {

    private final FoafService service;

    @GetMapping
    @RequestMapping("/analyze")
    public FoafResponse analyze(@RequestParam String foafUrl) {
        return service.analyze(foafUrl);
    }
}
