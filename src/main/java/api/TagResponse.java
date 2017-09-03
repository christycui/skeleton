package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.TagsRecord;

import java.math.BigDecimal;
import java.sql.Time;

public class TagResponse {
    @JsonProperty
    Integer id;

    @JsonProperty
    String tagName;

    @JsonProperty
    Integer receiptId;

    @JsonProperty
    Time created;

    public TagResponse(TagsRecord dbRecord) {
        this.tagName = dbRecord.getTagName();
        this.receiptId = dbRecord.getReceiptId();
        this.created = dbRecord.getCreated();
        this.id = dbRecord.getId();
    }
}
