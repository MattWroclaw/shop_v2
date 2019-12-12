package pl.mojeprojekty.shop_v2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private String password;

    private String city;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    private List <Address> shippingAddress;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "shipping_id")
//    private Address shippingAddress;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "home_id")
//    private Address homeAddress;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "invoice_id")
//    private Address invoiceAddress;


}
