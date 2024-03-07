package mzaenalmstpa.eduprobackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprobackend.model.request.KelompokMapelRequest;
import mzaenalmstpa.eduprobackend.model.response.KelompokMapelResponse;
import mzaenalmstpa.eduprobackend.model.response.Response;
import mzaenalmstpa.eduprobackend.service.KelompokMapelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/kelompok")
@RequiredArgsConstructor
public class KelompokMapelController extends BaseController<KelompokMapelResponse> {

    private final KelompokMapelService mapelService;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = mapelService.getAll();

        return this.get(result);
    }

    @GetMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> get(@PathVariable("id") Integer id,
                                   @PathVariable("kode") String kode){
        var result = mapelService.getById(id, kode);
        return getResponse(result);
    }

    @PostMapping("/mapel")
    private ResponseEntity<Response> save(@RequestBody @Valid KelompokMapelRequest request){
        var result = mapelService.save(request);
        return getResponse(result);
    }

    @PutMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> update(@RequestBody @Valid KelompokMapelRequest request,
                                            @PathVariable("id") Integer id,
                                            @PathVariable("kode") String kode){
        var result = mapelService.update(request, id, kode);
        return getResponse(result);
    }

    @DeleteMapping("/mapel/{id}/{kode}")
    private ResponseEntity<Response> delete(@PathVariable("id") Integer id,
                                            @PathVariable("kode") String kode){
        var result = mapelService.delete(id, kode);
        return getResponse(result);
    }
}
