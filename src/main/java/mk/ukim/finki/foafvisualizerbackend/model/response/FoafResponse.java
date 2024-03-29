package mk.ukim.finki.foafvisualizerbackend.model.response;

import java.util.List;

public record FoafResponse(List<String> accounts,
                           String age,
                           String aimChatID,
                           String basedNear,
                           String birthday,
                           String currentProject,
                           String depiction,
                           String familyName,
                           @Deprecated
                           String family_name,
                           String firstName,
                           String gender,
                           String givenName,
                           @Deprecated
                           String givenname,
                           String homepage,
                           String icqChatID,
                           List<String> images,
                           List<String> interests,
                           String jabberID,
                           List<KnowsResponse> knows,
                           String lastName,
                           List<String> madeList,
                           List<String> mboxes,
                           List<String> mboxSha1sums,
                           String msnChatID,
                           String myersBriggs,
                           String name,
                           String nick,
                           String openID,
                           String page,
                           String pastProject,
                           List<String> phones,
                           List<String> publications,
                           String schoolHomepage,
                           String skypeID,
                           @Deprecated
                           String surname,
                           String tipJar,
                           String title,
                           String weblog,
                           String workInfoHomepage,
                           String workplaceHomepage,
                           String yahooChatID) {
}
