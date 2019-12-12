package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    @Size(min = 3)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prediction> predictions;

    @Column
    @NotNull
    @Min(0)
    private int points;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User() {
        this.setPoints(0);
        this.roles = new HashSet<>();
        this.predictions = new ArrayList<>();
    }

    @Override
    @Transient
    public Collection<Role> getAuthorities() {
        return this.roles;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
