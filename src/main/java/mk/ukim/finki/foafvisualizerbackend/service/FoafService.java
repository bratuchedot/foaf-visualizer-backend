package mk.ukim.finki.foafvisualizerbackend.service;

import mk.ukim.finki.foafvisualizerbackend.model.exception.BadUrlException;
import mk.ukim.finki.foafvisualizerbackend.model.response.KnowsResponse;
import mk.ukim.finki.foafvisualizerbackend.model.response.FoafResponse;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDFS;
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

        List<String> accounts = getPropertyAsList(meResource, FOAF.account);
        String age = getPropertyAsString(meResource, FOAF.age);
        String aimChatID = getPropertyAsString(meResource, FOAF.aimChatID);
        String basedNear = getPropertyAsString(meResource, FOAF.based_near);
        String birthday = getPropertyAsString(meResource, FOAF.birthday);
        String currentProject = getPropertyAsString(meResource, FOAF.currentProject);
        String depiction = getPropertyAsString(meResource, FOAF.depiction);
        String familyName = getPropertyAsString(meResource, FOAF.familyName);
        @Deprecated
        String family_name = getPropertyAsString(meResource, FOAF.family_name);
        String firstName = getPropertyAsString(meResource, FOAF.firstName);
        String gender = getPropertyAsString(meResource, FOAF.gender);
        String givenName = getPropertyAsString(meResource, FOAF.givenName);
        @Deprecated
        String givenname = getPropertyAsString(meResource, FOAF.givenname);
        String homepage = getPropertyAsString(meResource, FOAF.homepage);
        String icqChatID = getPropertyAsString(meResource, FOAF.icqChatID);
        List<String> images = getPropertyAsList(meResource, FOAF.img);
        List<String> interests = getPropertyAsList(meResource, FOAF.interest);
        String jabberID = getPropertyAsString(meResource, FOAF.jabberID);
        List<KnowsResponse> knows = getPeopleYouKnow(meResource);
        String lastName = getPropertyAsString(meResource, FOAF.lastName);
        List<String> madeList = getPropertyAsList(meResource, FOAF.made);
        List<String> mboxes = getPropertyAsList(meResource, FOAF.mbox);
        List<String> mboxSha1sums = getPropertyAsList(meResource, FOAF.mbox_sha1sum);
        String msnChatID = getPropertyAsString(meResource, FOAF.msnChatID);
        String myersBriggs = getPropertyAsString(meResource, FOAF.myersBriggs);
        String name = getPropertyAsString(meResource, FOAF.name);
        String nick = getPropertyAsString(meResource, FOAF.nick);
        String openID = getPropertyAsString(meResource, FOAF.openid);
        String page = getPropertyAsString(meResource, FOAF.page);
        String pastProject = getPropertyAsString(meResource, FOAF.pastProject);
        List<String> phones = getPropertyAsList(meResource, FOAF.phone);
        List<String> publications = getPropertyAsList(meResource, FOAF.publications);
        String schoolHomepage = getPropertyAsString(meResource, FOAF.schoolHomepage);
        String skypeID = getPropertyAsString(meResource, FOAF.skypeID);
        @Deprecated
        String surname = getPropertyAsString(meResource, FOAF.surname);
        String tipJar = getPropertyAsString(meResource, FOAF.tipjar);
        String title = getPropertyAsString(meResource, FOAF.title);
        String weblog = getPropertyAsString(meResource, FOAF.weblog);
        String workInfoHomepage = getPropertyAsString(meResource, FOAF.workInfoHomepage);
        String workplaceHomepage = getPropertyAsString(meResource, FOAF.workplaceHomepage);
        String yahooChatID = getPropertyAsString(meResource, FOAF.yahooChatID);

        return new FoafResponse(accounts, age, aimChatID, basedNear, birthday, currentProject, depiction, familyName,
                family_name, firstName, gender, givenName, givenname, homepage, icqChatID, images, interests, jabberID,
                knows, lastName, madeList, mboxes, mboxSha1sums, msnChatID, myersBriggs, name, nick, openID, page,
                pastProject, phones, publications, schoolHomepage, skypeID, surname, tipJar, title, weblog,
                workInfoHomepage, workplaceHomepage, yahooChatID);
    }

    private String getPropertyAsString(Resource resource, Property property) {
        Statement stmt = resource.getProperty(property);
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

    private List<String> getPropertyAsList(Resource resource, Property property) {
        List<String> propertyValues = new ArrayList<>();
        StmtIterator iterator = resource.listProperties(property);
        while (iterator.hasNext()) {
            Statement stmt = iterator.next();
            RDFNode object = stmt.getObject();
            if (object.isLiteral()) {
                propertyValues.add(object.asLiteral().getString());
            } else {
                propertyValues.add(object.asResource().getURI());
            }
        }
        return !propertyValues.isEmpty() ? propertyValues : null;
    }

    private List<KnowsResponse> getPeopleYouKnow(Resource resource) {
        List<KnowsResponse> peopleYouKnow = new ArrayList<>();
        StmtIterator knowsIterator = resource.listProperties(FOAF.knows);
        while (knowsIterator.hasNext()) {
            Statement stmt = knowsIterator.next();
            Resource knowsResource = stmt.getObject().asResource();
            String friendName = getPropertyAsString(knowsResource, FOAF.name);
            String friendMbox = getPropertyAsString(knowsResource, FOAF.mbox);
            String friendMboxSha1sum = getPropertyAsString(knowsResource, FOAF.mbox_sha1sum);
            String seeAlso = getPropertyAsString(knowsResource, RDFS.seeAlso);
            KnowsResponse knowsResponse = new KnowsResponse(friendName, friendMbox, friendMboxSha1sum, seeAlso);
            peopleYouKnow.add(knowsResponse);
        }
        return !peopleYouKnow.isEmpty() ? peopleYouKnow : null;
    }
}
