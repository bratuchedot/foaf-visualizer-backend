package mk.ukim.finki.foafvisualizerbackend.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.foafvisualizerbackend.model.FoafResponse;
import mk.ukim.finki.foafvisualizerbackend.service.FoafService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/foaf")
public class FoafController {

    private final FoafService service;

    @RequestMapping("/analyze")
    public FoafResponse analyze(@RequestParam String foafUrl) {
        return service.analyze(foafUrl);
    }
}
