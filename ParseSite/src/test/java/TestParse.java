import org.junit.Assert;
import org.junit.Test;
import service.Impl.ParseSiteImpl;
import service.ParseSite;

import java.io.IOException;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public class TestParse {

    private static String FIRST_PATH ="https://agileengine.bitbucket.io/keFivpUlPMtzhfAy/samples/sample-0-origin.html";

    private static String SECOND_PATH ="https://agileengine.bitbucket.io/keFivpUlPMtzhfAy/samples/sample-1-evil-gemini.html";

    private static String THIRD_PATH ="https://agileengine.bitbucket.io/keFivpUlPMtzhfAy/samples/sample-2-container-and-clone.html";

    private static String ID = "make-everything-ok-button";

    private static String PLATFORM = "java -cp";

    @Test
    public void testParsSite() throws IOException {
        ParseSite parseSite = new ParseSiteImpl();

        parseSite.setAttributeId(FIRST_PATH,ID);

        String actual = "<a id=\"make-everything-ok-button\" href=\"#ok\" class=\"btn btn-success\"> Make everything OK </a>";
        String current =  parseSite.parse(FIRST_PATH, PLATFORM);
        Assert.assertEquals(actual, current);

        String actual_sec = "<a href=\"#ok\" class=\"btn btn-success\"> Make everything OK </a>";
        String current_sec =  parseSite.parse(SECOND_PATH, PLATFORM);
        Assert.assertEquals(actual_sec, current_sec);

        String actual_third = "<a href=\"Im-the-true-OK-button\" class=\"btn btn-success\" style=\"display:none\"> Make everything OK </a>";
        String current_third  =  parseSite.parse(THIRD_PATH, PLATFORM);
        Assert.assertEquals(actual_third, current_third);
    }

}
