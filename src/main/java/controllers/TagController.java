package controllers;

import api.CreateTagRequest;
import api.TagResponse;
import dao.TagDao;
import generated.tables.records.TagsRecord;
import jdk.nashorn.internal.objects.annotations.Getter;
import sun.font.TrueTypeFont;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags/{tag}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;
    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    public void toggleTag(@PathParam("tag") String tagName, @Valid @NotNull CreateTagRequest tag) {
        // check if tag already exists for receipt
        if (tags.check(tagName, tag.receipt_id) == -1) {
            System.out.println("check");
            tags.insert(tagName, tag.receipt_id);
        } else {
            System.out.println("delete");
            tags.delete(tagName, tag.receipt_id);
        }
    }

    @GET
    public List<TagResponse> getTags(@PathParam("tag") String tagName) {
        List<TagsRecord> tagRecords = tags.getTags(tagName);
        return tagRecords.stream().map(TagResponse::new).collect(toList());
    }
}