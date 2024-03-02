package mzaenalmstpa.eduprobackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mzaenalmstpa.eduprobackend.constant.DataStatus;

@Data
@AllArgsConstructor
@Getter
@Setter
public class KelompokMapelResponse {

    private Integer idLembaga;
    private String kode;
    private String nama;
    private DataStatus status;
}
