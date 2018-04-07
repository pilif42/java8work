package com.philippe.app.service.strings;

import java.util.Map;

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
}
