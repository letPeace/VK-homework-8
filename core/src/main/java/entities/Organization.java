package entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jooq.Record;

import static generated.Tables.ORGANIZATION;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization {

    @JsonProperty("name")
    private final String name;

    @JsonCreator
    public Organization(@JsonProperty(value = "name", required = true) String name) {
        this.name = name;
    }

    public static @NotNull Organization create(@NotNull Record record){
        return new Organization(
                record.getValue(ORGANIZATION.NAME)
        );
    }

    public String getName() {
        return name;
    }

}
