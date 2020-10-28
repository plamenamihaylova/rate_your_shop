package com.ratingapp.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shops",
        uniqueConstraints = { @UniqueConstraint(columnNames = "shop_name", name = "uniqueShopNameConstraint")})
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name="shop_sequence",
            sequenceName = "shop_sequence",
            allocationSize = 3
    )
    @Column(name = "shop_id")
    private Long id;

    @NonNull
    @NotNull(message = "Shop name is mandatory")
    @NotEmpty
    @Column(name = "shop_name", unique = true)
    private String shopName;

    @NonNull
    @NotNull(message = "Shop category is mandatory")
    @ManyToOne (targetEntity = Category.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NonNull
    @NotNull(message = "Shop url is mandatory")
    @NotEmpty
    @URL
    @Column(name = "shop_url")
    private String url;

    @URL
    @Size(min = 2)
    @Column(name = "shop_logo")
    private String logo;

    @Size(min = 2)
    @Column(name = "shop_phone")
    private String phone;

    @Size(min = 5)
    @Email(message = "must be valid email address")
    @Column(name = "shop_email")
    private String email;
}
