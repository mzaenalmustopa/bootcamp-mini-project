package mzaenalmstpa.eduprobackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mzaenalmstpa.eduprobackend.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonRes {
    private String kode;
    private String nama;
    private DataStatus status;
}
