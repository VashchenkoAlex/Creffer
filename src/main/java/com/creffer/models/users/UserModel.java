package com.creffer.models.users;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
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

    //Accesses Markers
    public interface EXPORT{}
    public interface UI{}

    public UserModel() {
    }

    //Getters and setters

    public boolean hasUseEmail(String email){
        return this.email.equals(email);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTypeTraffic() {
        return typeTraffic;
    }

    public void setTypeTraffic(String typeTraffic) {
        this.typeTraffic = typeTraffic;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isLivePassword() {
        return livePassword;
    }

    public void setLivePassword(boolean livePassword) {
        this.livePassword = livePassword;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                active == userModel.active &&
                livePassword == userModel.livePassword &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(company, userModel.company) &&
                Objects.equals(skype, userModel.skype) &&
                Objects.equals(firstName, userModel.firstName) &&
                Objects.equals(lastName, userModel.lastName) &&
                Objects.equals(address1, userModel.address1) &&
                Objects.equals(address2, userModel.address2) &&
                Objects.equals(city, userModel.city) &&
                Objects.equals(country, userModel.country) &&
                Objects.equals(region, userModel.region) &&
                Objects.equals(zip, userModel.zip) &&
                Objects.equals(phone, userModel.phone) &&
                Objects.equals(traffic, userModel.traffic) &&
                Objects.equals(typeTraffic, userModel.typeTraffic) &&
                Objects.equals(companyUrl, userModel.companyUrl) &&
                Objects.equals(info, userModel.info) &&
                Objects.equals(notes, userModel.notes) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(roles, userModel.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, company, skype, firstName, lastName, address1, address2, city, country, region, zip, phone, traffic, typeTraffic, companyUrl, info, active, notes, password, livePassword, roles);
    }
}
