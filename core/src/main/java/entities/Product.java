package entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jooq.Record;

import static generated.Tables.PRODUCT;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("organization")
    private final Organization organization;

    @JsonProperty("quantity")
    private final Integer quantity;

    @JsonCreator
    public Product(@JsonProperty(value = "name", required = true) String name,
                   @JsonProperty(value = "organization", required = true) Organization organization,
                   @JsonProperty(value = "quantity", required = true) Integer quantity) {
        this.name = name;
        this.organization = organization;
        this.quantity = quantity;
    }

    public static @NotNull Product create(@NotNull Record record){
        return new Product(
                record.getValue(PRODUCT.NAME),
                Organization.create(record),
                record.getValue(PRODUCT.QUANTITY)
        );
    }

    public String getName() {
        return name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
