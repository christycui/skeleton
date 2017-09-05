package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.TagsRecord;

import java.math.BigDecimal;
import java.sql.Time;

public class TagResponse {

    @JsonProperty
    String tagName;

    @JsonProperty
    Integer id;

    @JsonProperty
    Time created;

    public TagResponse(TagsRecord dbRecord) {
        this.tagName = dbRecord.getTagName();
        this.created = dbRecord.getCreated();
        this.id = dbRecord.getId();
    }
}
