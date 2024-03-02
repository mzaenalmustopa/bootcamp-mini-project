package mzaenalmstpa.eduprowebtemplate.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GedungResponse {

    private Integer id;
    private String kode;
    private String nama;
    private String status;
}
