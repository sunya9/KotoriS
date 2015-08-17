package net.unsweets.kotoris;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mugen_000 on 2015/08/17.
 */
public class Util {
    private static final String TAG = Util.class.getSimpleName();

    public static Map<String, String> getQueryMap(String uri) {
        Map<String, String> map = new HashMap<String, String>();
        String[] uriStrings = uri.split("\\?");
        if (uriStrings.length == 1) return map;

        String[] queryParams = uriStrings[1].split("&");
        for (String qp : queryParams) {
            String[] params = qp.split("=");

            if (params.length == 1) {
                map.put(params[0], null);
            } else {
                map.put(params[0], params[1]);
            }
        }

        return map;
    }
}
