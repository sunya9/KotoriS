package net.unsweets.kotoris;

import android.graphics.Bitmap;
import android.os.AsyncTask;

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

    public static class AsyncImageLoad{
        private final AsyncTask<String, Void, Bitmap> mAsyncTask;
        private AsyncImageLoadListener mListener;

        public AsyncImageLoad(){
            mAsyncTask = new AsyncTask<String, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(String... params) {

                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (mListener != null)
                        mListener.onPostExecute(bitmap);
                }
            };
        }

        public void execute(String url){
            if(mAsyncTask != null) mAsyncTask.execute(url);
        }

        public void setPostExecute(AsyncImageLoadListener listener){
            mListener = listener;
        }

        public interface AsyncImageLoadListener {
            void onPostExecute(Bitmap bitmap);
        }
    }
}
