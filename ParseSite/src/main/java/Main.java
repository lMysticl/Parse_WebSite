import lombok.extern.slf4j.Slf4j;
import service.Impl.ParseSiteImpl;
import service.ParseSite;

import java.io.IOException;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
@Slf4j
public class Main {


    private static String FIRST_PATH = "https://agileengine.bitbucket.io/keFivpUlPMtzhfAy/samples/sample-0-origin.html";

    private static String SECOND_PATH = "https://agileengine.bitbucket.io/keFivpUlPMtzhfAy/samples/sample-1-evil-gemini.html";

    private static String THIRD_PATH = "https://agileengine.bitbucket.io/keFivpUlPMtzhfAy/samples/sample-2-container-and-clone.html";

    private static String ID = "make-everything-ok-button";

    private static String PLATFORM = "java -cp";

    public static void main(String[] args) throws IOException {

        ParseSite parseSite = new ParseSiteImpl();

        parseSite.setAttributeId(FIRST_PATH, ID );

        log.info(parseSite.parse(FIRST_PATH,PLATFORM));

        log.info(parseSite.parse(SECOND_PATH,PLATFORM));

        log.info(parseSite.parse(THIRD_PATH,PLATFORM));


    }
}
