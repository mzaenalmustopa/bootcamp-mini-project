package mzaenalmstpa.eduprobackend.model.response;

import lombok.*;
import mzaenalmstpa.eduprobackend.constant.DataStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GedungResponse {
    private String kode;
    private String nama;
    private DataStatus status;
}
