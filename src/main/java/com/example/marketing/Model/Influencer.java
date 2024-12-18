package com.example.marketing.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotEmpty(message = "Influencer name cannot be Empty")
    @Size(min = 2, max = 100, message = "Influencer name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String Influencer_name;

    @NotEmpty(message = "Influencer phone cannot be Empty")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be a valid format (e.g., +966234567890)")
    @Column(unique = true, nullable = false)
    private String Influencer_phone;

    @NotEmpty(message = "Influencer email cannot be Empty")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String Influencer_email;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private Set<Platform> platforms;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private Set<BookingOneTime> bookingOneTimes;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    private Set<Package> packages;

}
