package com.wehavescience.resources;

import com.github.mustachejava.Mustache;
import com.wehavescience.domain.Article;
import com.wehavescience.domain.Author;
import com.wehavescience.domain.Comment;
import com.wehavescience.qualifiers.Template;
import com.wehavescience.repositories.Repository;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import static javax.ws.rs.core.MediaType.TEXT_HTML_TYPE;
import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Path("/articles")
public class ArticleResource {
    @Inject
    private Repository<Article> articleRepository;

    @Inject
    private Repository<Author> authorRepository;

    @Inject
    @Template(mustacheFile = "pages/article.mustache")
    private Mustache articleTemplate;

    @Inject
    private Event<Set<Author>> notifySubscribers;

    @GET
    @Produces("application/json")
    public Response findAll() {
        return ok(articleRepository.findAll()).build();
    }

    @PUT
    @Path("/{articleTitle}/comments/{author}/approve")
    public Response approveComment(@PathParam("articleTitle") String articleTitle, @PathParam("author") String author){
        Article article = articleRepository.findOneBy("title", articleTitle);

        for (Comment comment : article.comments()) {
            if(comment.author().equals(author)){
                comment.approved(true);
            }
        }

        notifyAllSubscribers(article);
        return response(ok(article.comments()));
    }

    private void notifyAllSubscribers(Article article) {
        Set<Author> authors = new HashSet<>();
        article.comments().forEach(c -> authors.add(new Author(c.author(), c.email())));
        notifySubscribers.fire(authors);
    }

    @POST
    @Path("/{name}/comments")
    public Response addComment(@PathParam("name") String name, @FormParam("name") String personName, @FormParam("email") String email, @FormParam("content") String content) {
        Article article = articleRepository.findOneBy("title", name);

        Comment comment = new Comment();

        comment
                .approved(false)
                .email(email)
                .content(content)
                .date(new Date());

        if (article.comments() == null) {
            article.comments(new ArrayList<>());
        }

        article.comments().add(comment);
        articleRepository.save(article);

        return response(ok(comment));
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(Article article) {
        Author author = authorRepository.findOneBy("authorName", article.author().authorName());

        if(author == null){
            author = article.author();
            authorRepository.save(author);
        }

        article.author(author);
        articleRepository.save(article);
        return response(ok(articleRepository.findOneBy("title", article.title())));
    }

    @GET
    @Path("/{name}")
    public Response findOne(@PathParam("name") String resource) throws IOException {
        Article article = articleRepository.findOneBy("resource", resource);

        if(article == null){
            return response(noContent());
        }

        StringWriter writer = new StringWriter();
        articleTemplate.execute(writer, article).flush();

        return response(ok(writer.toString()).type(TEXT_HTML_TYPE));
    }

    private Response response(Response.ResponseBuilder responseBuilder){
        return responseBuilder.build();
    }
}
