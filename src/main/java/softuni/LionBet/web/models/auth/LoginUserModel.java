package softuni.LionBet.web.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginUserModel {
    private String username;
    private String password;
}
