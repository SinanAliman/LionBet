package softuni.LionBet.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private String password;
}
