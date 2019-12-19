package softuni.LionBet.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserServiceModel {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterUserServiceModel(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
