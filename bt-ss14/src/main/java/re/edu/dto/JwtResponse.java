package re.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String username;

    public JwtResponse(String accessToken, String username) {
        this.accessToken = accessToken;
        this.username = username;
    }
}
