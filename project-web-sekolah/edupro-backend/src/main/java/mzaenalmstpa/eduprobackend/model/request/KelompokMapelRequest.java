package mzaenalmstpa.eduprobackend.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class KelompokMapelRequest {

    private Integer idLembaga;
    private String kode;
    private String nama;
}
