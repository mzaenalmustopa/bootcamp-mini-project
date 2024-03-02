package mzaenalmstpa.eduprobackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprobackend.model.request.GedungRequest;
import mzaenalmstpa.eduprobackend.model.response.GedungResponse;
import mzaenalmstpa.eduprobackend.model.response.Response;
import mzaenalmstpa.eduprobackend.service.GedungService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gedung")
@RequiredArgsConstructor
public class GedungController extends BaseController<GedungResponse> {
    private final GedungService gedungService;

    @GetMapping
    private ResponseEntity<Response> getAll(){
        var result = this.gedungService.getAll();

        return this.get(result);
    }

    @GetMapping("/{kode}")
    private ResponseEntity<Response> getById(@PathVariable("kode") String kode){
        var result = this.gedungService.geyById(kode);
        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid GedungRequest request){
        var result = this.gedungService.save(request);
        return getResponse(result);
    }

    @PutMapping("/{kode}")
    private ResponseEntity<Response> update(@RequestBody @Valid GedungRequest request,
                                            @PathVariable("kode") String kode){
        var result = this.gedungService.update(request, kode);
        return getResponse(result);
    }

    @DeleteMapping("/{kode}")
    private ResponseEntity<Response> delete(@PathVariable("kode") String kode){
        var result = this.gedungService.delete(kode);
        return getResponse(result);
    }
}
