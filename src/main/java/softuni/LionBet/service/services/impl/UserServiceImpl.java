package softuni.LionBet.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.LionBet.data.models.entities.Role;
import softuni.LionBet.data.models.entities.User;
import softuni.LionBet.data.repositories.RoleRepository;
import softuni.LionBet.data.repositories.UserRepository;
import softuni.LionBet.service.models.UserRankingServiceModel;
import softuni.LionBet.service.models.auth.RegisterUserServiceModel;
import softuni.LionBet.service.services.UserService;
import softuni.LionBet.service.services.AuthValidationService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthValidationService authValidationService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AuthValidationService authValidationService,
                           ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authValidationService = authValidationService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterUserServiceModel model) throws Exception {
        if (!authValidationService.isValidUser(model)){
            throw new Exception("Invalid data provided!");
        }
        User user = this.modelMapper.map(model, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(getRolesForRegistration());
        this.userRepository.save(user);

    }

    @Override
    public List<UserRankingServiceModel> getAllUsers() {
        return this.userRepository.findAllByPointsIsNotNullOrderByPointsDesc().stream()
                .map(user -> this.modelMapper.map(user, UserRankingServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

    }

    private Set<Role> getRolesForRegistration() {
        Set<Role> roles = new HashSet<>();

        if(this.userRepository.count() == 0) {
            roles.add(this.roleRepository.findByName("ADMIN"));
            roles.add(this.roleRepository.findByName("MODERATOR"));
            roles.add(this.roleRepository.findByName("USER"));
        } else {
            roles.add(this.roleRepository.findByName("USER"));
        }

        return roles;
    }
}
