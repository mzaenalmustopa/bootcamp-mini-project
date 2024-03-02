package mzaenalmstpa.eduprowebtemplate.constant;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class BackEndUrl {

    @Value("${spring.application.back-end-url}")
    private String baseUrl;

    public String gedungUrl(){
        return Strings.concat(baseUrl, "/gedung");
    }
}
