package controllers;

import dao.TagDao;
import static org.mockito.Mockito.*;

import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class TagControllerTest {

    TagDao tagDao = mock(TagDao.class);

    TagController tagController = new TagController(tagDao);

    @Test
    public void testCheck() {
        tagController.toggleTag("test", 1);
        verify(tagDao).check(anyString(), anyInt());
    }

    @Test
    public void testGetTags() {
        tagController.getTags("ex");
        verify(tagDao).getTags(anyString());
    }

}