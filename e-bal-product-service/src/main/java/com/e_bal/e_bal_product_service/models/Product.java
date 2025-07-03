package com.e_bal.e_bal_product_service.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Product entity representing a product in the system.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long shopid;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 100)
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    /**
     * Adds an image to the product. Max 5 images allowed.
     *
     * @param url the image URL
     */
    public void addImage(String url) {
        if (this.images.size() >= 5) {
            throw new IllegalStateException("A product can have at most 5 images.");
        }
        ProductImage image = new ProductImage();
        image.setUrl(url);
        image.setProduct(this);
        this.images.add(image);
    }

    /**
     * Replaces all product images (max 5).
     *
     * @param imageUrls list of image URLs
     */
    public void setImagesFromUrls(List<String> imageUrls) {
        if (imageUrls != null && imageUrls.size() > 5) {
            throw new IllegalArgumentException("A product can have at most 5 images.");
        }
        this.images.clear();
        if (imageUrls != null) {
            imageUrls.forEach(this::addImage);
        }
    }

    @PrePersist
    @PreUpdate
    private void validateImageLimit() {
        if (this.images != null && this.images.size() > 5) {
            throw new IllegalStateException("A product can have at most 5 images.");
        }
    }
}
