package mk.ukim.finki.foafvisualizerbackend.model.response;

import java.util.List;

public record FoafResponse(String name,
                           String title,
                           String firstName,
                           String lastName,
                           String nickname,
                           String email,
                           String homepage,
                           String pictureUrl,
                           String phoneNumber,
                           String workHomepage,
                           String workDescription,
                           String schoolHomepage,
                           List<FriendResponse> peopleYouKnow) {
}
