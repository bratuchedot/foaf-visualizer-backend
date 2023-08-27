package mk.ukim.finki.foafvisualizerbackend.service;

import mk.ukim.finki.foafvisualizerbackend.model.exception.BadUrlException;
import mk.ukim.finki.foafvisualizerbackend.model.response.FoafResponse;
import mk.ukim.finki.foafvisualizerbackend.model.response.FriendResponse;
import org.apache.jena.rdf.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoafService {

    public FoafResponse analyze(String foafUrl) {
        Model model = ModelFactory.createDefaultModel();

        try (InputStream in = new URL(foafUrl).openStream()) {
            model.read(in, "", "TTL");
        } catch (IOException e) {
            throw new BadUrlException(foafUrl);
        }

        Resource meResource = model.getResource(foafUrl + "#me");
        String name = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/name");
        String title = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/title");
        String firstName = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/givenname");
        String lastName = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/family_name");
        String nickname = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/nick");
        String email = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/mbox_sha1sum");
        String homepage = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/homepage");
        String pictureUrl = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/depiction");
        String phoneNumber = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/phone");
        String workHomepage = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/workplaceHomepage");
        String workDescription = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/workInfoHomepage");
        String schoolHomepage = getPropertyAsString(model, meResource, "http://xmlns.com/foaf/0.1/schoolHomepage");

        List<FriendResponse> peopleYouKnow = new ArrayList<>();
        StmtIterator friendIterator = meResource.listProperties(model.getProperty("http://xmlns.com/foaf/0.1/knows"));
        while (friendIterator.hasNext()) {
            Statement stmt = friendIterator.next();
            Resource friendResource = stmt.getObject().asResource();
            String friendName = getPropertyAsString(model, friendResource, "http://xmlns.com/foaf/0.1/name");
            String friendEmail = getPropertyAsString(model, friendResource, "http://xmlns.com/foaf/0.1/mbox_sha1sum");
            String seeAlso = getPropertyAsString(model, friendResource, "http://www.w3.org/2000/01/rdf-schema#seeAlso");
            FriendResponse friendResponse = new FriendResponse(friendName, friendEmail, seeAlso);
            peopleYouKnow.add(friendResponse);
        }

        return new FoafResponse(name, title, firstName, lastName, nickname, email, homepage, pictureUrl, phoneNumber,
                workHomepage, workDescription, schoolHomepage, peopleYouKnow);
    }

    private String getPropertyAsString(Model model, Resource resource, String propertyUri) {
        Statement stmt = resource.getProperty(model.getProperty(propertyUri));
        if (stmt != null) {
            RDFNode object = stmt.getObject();
            if (object.isLiteral()) {
                return object.asLiteral().getString();
            } else {
                return object.asResource().getURI();
            }
        }
        return null;
    }
}
