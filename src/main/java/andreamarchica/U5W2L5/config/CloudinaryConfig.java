package andreamarchica.U5W2L5.config;

import com.cloudinary.Cloudinary;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
public class CloudinaryConfig {
    @Value("${cloudinary.name}")
    private String name;

    @Value("${cloudinary.apikey}")
    private String apikey;

    @Value("${cloudinary.secret}")
    private String secret;

    @Bean
    public Cloudinary uploader() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", name);
        config.put("api_key", apikey);
        config.put("api_secret", secret);
        return new Cloudinary(config);
    }
}
