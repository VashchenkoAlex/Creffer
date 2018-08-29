package com.creffer.models.users;

import com.creffer.models.system.RoleModel;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "users")
public class UserModel implements Serializable,UserDetails,CredentialsContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @JsonView(UI.class)
    private int id;

    @Column(name = "email")
    @JsonView(UI.class)
    private String email;

    @Column(name = "company")
    @JsonView(UI.class)
    private String company;

    @Column(name = "skype")
    @JsonView(UI.class)
    private String skype;

    @Column(name = "first_name")
    @JsonView(UI.class)
    private String firstName;

    @Column(name = "last_name")
    @JsonView(UI.class)
    private String lastName;

    @Column(name = "address1")
    @JsonView(UI.class)
    private String address1;

    @Column(name = "address2")
    @JsonView(UI.class)
    private String address2;

    @Column(name = "city")
    @JsonView(UI.class)
    private String city;

    @Column(name = "country")
    @JsonView(UI.class)
    private String country;

    @Column(name = "region")
    @JsonView(UI.class)
    private String region;

    @Column(name = "zip")
    @JsonView(UI.class)
    private String zip;

    @Column(name = "phone")
    @JsonView(UI.class)
    private String phone;

    @Column(name = "traffic")
    @JsonView(UI.class)
    private String traffic;

    @Column(name = "type_traffic")
    @JsonView(UI.class)
    private String typeTraffic;

    @Column(name = "company_url")
    @JsonView(UI.class)
    private String companyUrl;

    @Column(name = "info")
    @JsonView(UI.class)
    private String info;

    @Column(name = "active")
    @JsonView(UI.class)
    private int active;

    @Column(name = "notes")
    @JsonView(UI.class)
    private String notes;

    @Column(name = "password")
    @JsonView(EXPORT.class)
    private String password;
    @Column(name = "live_password")
    private boolean livePassword;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> roles;

    @Override
    public void eraseCredentials() {
        password = null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setRoles(ArrayList<RoleModel> roles) {
        this.roles = roles;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public int getActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    //Accesses Markers
    public interface EXPORT{}
    public interface UI{}
    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>
     * .
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return active==2;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return active==3;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return livePassword;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return active==1;
    }
}
