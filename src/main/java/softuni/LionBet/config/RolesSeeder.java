package softuni.LionBet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.LionBet.data.models.entities.Role;
import softuni.LionBet.data.repositories.RoleRepository;

import javax.annotation.PostConstruct;

@Component
public class RolesSeeder {
    private final RoleRepository roleRepository;

    @Autowired
    public RolesSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void seed() {
        if (this.roleRepository.findAll().isEmpty()) {
            Role userRole = new Role();
            userRole.setName("USER");

            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            Role moderatorRole = new Role();
            moderatorRole.setName("MODERATOR");

            this.roleRepository.save(userRole);
            this.roleRepository.save(adminRole);
            this.roleRepository.save(moderatorRole);
        }
    }
}
