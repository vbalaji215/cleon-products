package com.cleon.products.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * vbala created on 3/23/2020
 * Inside the package - com.cleon.products.domain
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(schema = "CLEON_PRODUCTS")
public class ProductType {

    /**
     * Primary Key for each product type
     */
    @Id
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_sequence")
    @SequenceGenerator(name="product_type_sequence", sequenceName = "product_type_seq", //schema = "CLEON_PRODUCTS",
            initialValue = 20001, allocationSize = 1)
    @Column(updatable = false, nullable = false)
    private Long productTypeId;

    /**
     * Name of the product type
     */
    @NotBlank
    @Column(length = 50, columnDefinition = "varchar", nullable = false, unique = true)
    private String productTypeName;

    /**
     * Short description of the product type
     */
    @NotBlank
    @Column(length = 100, columnDefinition = "varchar", nullable = false)
    private String productTypeDescription;

    /**
     * The product type can be associated to only one product category,
     * but the product category can have many product types
     *
     * The default fetch type for Many to One relationship is EAGER
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="product_category_id", nullable = false)
    private ProductCategory productCategory;

    /**
     * The product type can be associated to many products
     * A product can be associated with only one product type
     *
     * The default fetch type for One to Many relationship is LAZY
     */
    @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    /**
     * The date and time on which the product type was created
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    /**
     * The last occurrence when the product type was updated
     */
    @UpdateTimestamp
    @Column(updatable = false)
    private Timestamp lastModifiedDate;
}
