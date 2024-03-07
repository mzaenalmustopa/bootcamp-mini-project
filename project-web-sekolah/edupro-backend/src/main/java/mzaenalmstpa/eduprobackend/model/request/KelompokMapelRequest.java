package mzaenalmstpa.eduprobackend.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class KelompokMapelRequest {

    @NotNull(message = "id Lembaga wajib di isi")
    private Integer idLembaga;

    @NotEmpty
    @Size(min = 2, max = 10, message = "kode minimal 2 dan maximal 10")
    private String kode;

    @NotEmpty
    @Size(min = 5, max = 32, message = "Nama minimal 5, dan maximal 32")
    private String nama;
}
