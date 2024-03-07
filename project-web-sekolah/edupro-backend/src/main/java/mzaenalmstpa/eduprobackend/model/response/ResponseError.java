package mzaenalmstpa.eduprobackend.model.response;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError<T> {

    private int statusCode;
    private String message;
    private Object errors;
}
