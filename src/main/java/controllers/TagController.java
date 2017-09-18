package controllers;

import api.TagResponse;
import dao.TagDao;
import generated.tables.records.TagsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;
    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @Path("/{tag}")
    @PUT
    public void toggleTag(@PathParam("tag") String tagName, int receipt_id) {
        // check if tag already exists for receipt
        if (tags.check(tagName, receipt_id) == -1) {
            tags.insert(tagName, receipt_id);
        } else {
            tags.delete(tagName, receipt_id);
        }
    }

    @Path("/{tag}")
    @GET
    public List<TagResponse> getTags(@PathParam("tag") String tagName) {
        List<TagsRecord> tagRecords = tags.getTags(tagName);
        return tagRecords.stream().map(TagResponse::new).collect(toList());
    }

    @GET
    public List<TagResponse> getAllTags() {
        List<TagsRecord> tagRecords = tags.getAllTags();
        return tagRecords.stream().map(TagResponse::new).collect(toList());
    }
}