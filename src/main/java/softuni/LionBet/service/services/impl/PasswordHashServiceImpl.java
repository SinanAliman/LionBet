package softuni.LionBet.service.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import softuni.LionBet.service.services.PasswordHashService;

@Service
public class PasswordHashServiceImpl implements PasswordHashService {
    @Override
    public String hashPassword(String password) {
        return DigestUtils.sha3_256Hex(password);
    }
}
