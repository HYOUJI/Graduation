package zebra.manager;

import android.content.Context;
import android.widget.Toast;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


import org.junit.experimental.categories.Category;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;
import zebra.activity.MyPageActivity;
import zebra.json.Login;
import zebra.json.MyReview;
import zebra.json.Review;
import zebra.json.Search;

/**
 * Created by multimedia on 2016-05-18.
 */
public class NetworkManager {

    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    AsyncHttpClient client;
    Gson gson;

    private NetworkManager() {
        client = new AsyncHttpClient();
        client.setTimeout(3000);

        gson = new Gson();
    }

    public interface OnResultListener<T> {
        public void onSuccess(T result);

        public void onFail(int code);
    }

    public interface OnResultResponseListener<T> {
        public void onSuccess(T result);

        public void onFail(int code, String responseString);
    }

    public HttpClient getHttpClient() {
        return client.getHttpClient();
    }

    private static final String SERVER_URL = "http://113.198.84.84:8080/ZEBRA/";

    private static final String LOGIN_URL = SERVER_URL + "/appLogin";

    public void login(final Context context, String id, String password, final OnResultResponseListener<Login> listener) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("password", password);
        client.post(context, LOGIN_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                Login result = gson.fromJson(jsonResponseString, Login.class);
                listener.onSuccess(result);
            }
        });
    }

    private static final String REVIEW_REGISTER_URL = SERVER_URL + "/appReviewRegister";

    public void reviewRegister(Context context, String id, String reviewText, String barcode, double starPoint, String productUrl, String memberUrl, String level, final OnResultResponseListener<Review> listener) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("reviewText", reviewText);
        params.put("barcode", barcode);
        params.put("starPoint", starPoint);
        params.put("memberUrl", memberUrl);
        params.put("productUrl", productUrl);
        params.put("level", level);

        client.post(context, REVIEW_REGISTER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                Review result = gson.fromJson(jsonResponseString, Review.class);
                if (result.reviews == null) {
                    result = null;
                    listener.onSuccess(result);
                } else {
                    listener.onSuccess(result);
                }
            }
        });
    }

    private static final String SCAN_URL = SERVER_URL + "/appScan";

    public void review(Context context, String barcode, final OnResultResponseListener<Review> listener) {
        RequestParams params = new RequestParams();
        params.put("barcode", barcode);

        client.post(context, SCAN_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                if (jsonResponseString.equals("exist")) {
                    Review result = null;
                    listener.onSuccess(result);
                } else {
                    Review result = gson.fromJson(jsonResponseString, Review.class);
                    listener.onSuccess(result);
                }
            }
        });
    }

    private static final String PRODUCT_REGISTER_URL = SERVER_URL + "/appProductRegister";

    public void productRegister(Context context, String id, String barcode, String productName, double starPoint, String reviewText, String level, final OnResultListener<String> listener) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("barcode", barcode);
        params.put("productName", productName);
        params.put("starPoint", starPoint);
        params.put("reviewText", reviewText);
        params.put("level", level);

        client.post(context, PRODUCT_REGISTER_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                listener.onSuccess(jsonResponseString);
            }
        });
    }

    private static final String PRODUCT_SEARCH_URL = SERVER_URL + "/appProductSearch";

    public void productSearch(final Context context, String keyword, final OnResultResponseListener<Search> listener) {
        RequestParams params = new RequestParams();
        params.put("keyword", keyword);

        client.post(context, PRODUCT_SEARCH_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode, responseString);
                Toast.makeText(context, "실패 " + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(context, "성공 " + statusCode, Toast.LENGTH_LONG).show();
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                Search result = gson.fromJson(jsonResponseString, Search.class);
                listener.onSuccess(result);
            }
        });
    }

    private static final String CATEGORY_URL = SERVER_URL + "/appCategory";

    public void category(final Context context, String category, final OnResultResponseListener<Search> listener) {
        RequestParams params = new RequestParams();
        params.put("category", category);

        client.post(context, CATEGORY_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(context, "성공 " + statusCode, Toast.LENGTH_LONG).show();
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                Search result = gson.fromJson(jsonResponseString, Search.class);
                listener.onSuccess(result);
            }
        });
    }

    private static final String MY_REVIEW = SERVER_URL + "/appShowMyReview";

    public void myReview(final Context context, String id, final OnResultResponseListener<MyReview> listener) {
        RequestParams params = new RequestParams();
        params.put("id", "a");

        client.post(context, MY_REVIEW, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(context, "실패 " + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                String jsonResponseString = responseString.replaceAll("[\n \r]", "");
                MyReview result = gson.fromJson(jsonResponseString, MyReview.class);
                listener.onSuccess(result);
            }
        });
    }
    /**
     * MainActivity에서 바코드가 스캔 되면 그 값을 변수로 잡아서 MainActivity에서
     * NetworkManager.getInstance().scan(MainActivity.this, idView.getText(), );
     * 으로 Tomcat 서버에 request를 보내자
     * */
}
