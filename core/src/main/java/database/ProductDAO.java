package database;

import entities.Product;
import org.jetbrains.annotations.NotNull;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static generated.Tables.*;

public class ProductDAO {

    private final @NotNull Connection connection;
    private final @NotNull DSLContext context;

    public ProductDAO(@NotNull Connection connection) {
        this.connection = connection;
        this.context = DSL.using(connection, SQLDialect.POSTGRES);
    }

    public ProductDAO() throws SQLException {
        this(JDBCCredentials.getConnection());
    }

    private @NotNull List<Product> list(@NotNull Result<Record> records){
        List<Product> list = new LinkedList<>();
        for(Record record : records) list.add(Product.create(record));
        return list;
    }

    public @NotNull List<Product> getAll() {
        Result<Record> records = context
                .select()
                .from(PRODUCT)
                .join(ORGANIZATION)
                .on(PRODUCT.ORGANIZATION_NAME.eq(ORGANIZATION.NAME))
                .fetch();
        return list(records);
    }

    public @NotNull List<Product> getAll(@NotNull String organizationName) {
        Result<Record> records = context
                .select()
                .from(PRODUCT)
                .join(ORGANIZATION)
                .on(
                        PRODUCT.ORGANIZATION_NAME.eq(ORGANIZATION.NAME)
                        .and(ORGANIZATION.NAME.eq(organizationName))
                )
                .fetch();
        return list(records);
    }

    public @NotNull Integer save(@NotNull String name, @NotNull String organizationName, @NotNull Integer quantity) {
        OrganizationDAO organizationDAO = new OrganizationDAO(connection);
        organizationDAO.existsOrSave(organizationName);
        return context
                .insertInto(
                        PRODUCT,
                        PRODUCT.NAME,
                        PRODUCT.ORGANIZATION_NAME,
                        PRODUCT.QUANTITY
                )
                .values(
                        name,
                        organizationName,
                        quantity
                )
                .execute();
    }

    public @NotNull Integer delete(@NotNull String name) {
        return context.delete(PRODUCT)
                .where(PRODUCT.NAME.eq(name))
                .execute();
    }

}
