/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package zkhan93.github.io.gcpjoker;

import com.example.Joker;
import com.example.model.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "gcpjoker.io.github.zkhan93",
                ownerName = "gcpjoker.io.github.zkhan93",
                packagePath = ""
        )
)
public class GCPJoker {
    private Joker joker = new Joker();

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "tellJoke")
    public Joke tellJoke() {
        return joker.getJoke();
    }

}
