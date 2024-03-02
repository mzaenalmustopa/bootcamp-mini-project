package mzaenalmstpa.eduprobackend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class RuanganRequest {

    private String kode;
    private String nama;
    private Integer kapasitas;
    private String kodeGedung;
}
