/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String organizationName;
    private final Integer quantity;

    public Product(Product value) {
        this.name = value.name;
        this.organizationName = value.organizationName;
        this.quantity = value.quantity;
    }

    public Product(
        String name,
        String organizationName,
        Integer quantity
    ) {
        this.name = name;
        this.organizationName = organizationName;
        this.quantity = quantity;
    }

    /**
     * Getter for <code>core.product.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>core.product.organization_name</code>.
     */
    public String getOrganizationName() {
        return this.organizationName;
    }

    /**
     * Getter for <code>core.product.quantity</code>.
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Product other = (Product) obj;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.organizationName == null) {
            if (other.organizationName != null)
                return false;
        }
        else if (!this.organizationName.equals(other.organizationName))
            return false;
        if (this.quantity == null) {
            if (other.quantity != null)
                return false;
        }
        else if (!this.quantity.equals(other.quantity))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.organizationName == null) ? 0 : this.organizationName.hashCode());
        result = prime * result + ((this.quantity == null) ? 0 : this.quantity.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product (");

        sb.append(name);
        sb.append(", ").append(organizationName);
        sb.append(", ").append(quantity);

        sb.append(")");
        return sb.toString();
    }
}
