package dao;

import api.TagResponse;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(String tagName, Integer receiptId) {
        TagsRecord tagsRecord = dsl
                .insertInto(TAGS, TAGS.TAG_NAME, TAGS.RECEIPT_ID)
                .values(tagName, receiptId)
                .returning(TAGS.ID)
                .fetchOne();

        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");

        return tagsRecord.getId();
    }

    public int check(String tagName, Integer receiptId) {
        return dsl.selectFrom(TAGS).where(TAGS.TAG_NAME.eq(tagName)).and(TAGS.RECEIPT_ID.eq(receiptId)).fetch().getValues(TAGS.TAG_NAME).indexOf(tagName);
    }

    public void delete(String tagName, Integer receiptId) {
        dsl.delete(TAGS).where(TAGS.TAG_NAME.eq(tagName)).and(TAGS.RECEIPT_ID.eq(receiptId)).execute();
    }

    public List<TagsRecord> getTags(String tagName) {
        return dsl.selectFrom(TAGS).where(TAGS.TAG_NAME.eq(tagName)).fetch();
    }
}