package mzaenalmstpa.eduprobackend.model.response;

import lombok.*;
import mzaenalmstpa.eduprobackend.constant.DataStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KelompokMapelResponse {

    private Integer idLembaga;
    private String kode;
    private String nama;
    private DataStatus status;
}
