package mzaenalmstpa.eduprowebtemplate.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GedungRequest {

    private Integer id;

    @NotEmpty(message = "kode gedung wajib di isi")
    @Size(min = 3, max = 10, message = "minimal 3 dan maximal 10")
    private String kode;

    @NotEmpty(message = "nama gedung tidak boleh kosong")
    @Size(min = 5, max = 100, message = "minimal 5 dan maximal 100")
    private String nama;
}
