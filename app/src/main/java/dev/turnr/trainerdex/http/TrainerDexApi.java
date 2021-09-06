package dev.turnr.trainerdex.http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrainerDexApi {

    private static final String domain = "https://trainerdex.app/";
    public final OkHttpClient client = new OkHttpClient();

    void getJWTFromUsernameAndPassword(String username, String password) {
        // TODO: 04/09/2021
        // Get JSON Web Token
        // Waiting on JWT on backend
    }

    void getLeaderboardData() throws IOException {
        return getLeaderboardData("total_xp");
    }


    /**
     * This retrieves the leaderboard filtered to entries from the last three months
     * @param stat This is the Pokemon Go stat
     * @return json
      */
    void getLeaderboardData(String stat) throws IOException {
        Request request = new Request.Builder()
                .url("https://trainerdex.app/api/v1/leaderboard/v1.1/${stat}/")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        response.body().
    }

}
