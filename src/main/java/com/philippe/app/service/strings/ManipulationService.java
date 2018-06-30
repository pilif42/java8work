package com.philippe.app.service.strings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ManipulationService {
    /**
     * Transform aString TXT=/opt/app/data/sample.txt,/opt/app/data/sample_1.txt|JSON=/opt/app/data/a.json,/opt/app/data/b.json
     * into a Map:
     *      - TXT: /opt/app/data/sample.txt,/opt/app/data/sample_1.txt
     *      - JSON: /opt/app/data/a.json,/opt/app/data/b.json
     *
     * @param aString
     * @return
     */
    Map<String, String> process(String aString);

    /**
     * Filter urls to return the first valid https url, ie ending with https port 443
     *
     * @param urlList a list of url strings
     * @return a valid https url if one exists in the input list
     */
    Optional<String> filterUrls(List<String> urlList);

    byte[] transform(String input);
}
