package service;

import java.io.IOException;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public interface ParseSite {

    String parse(String url, String PLATFORM) throws IOException;

    void setAttributeId(String url, String id) throws IOException;
}
