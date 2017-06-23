// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

import java.net.URI;
import 

;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class javaQuickstart
{
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace the subscriptionKey string value with your valid subscription key.
    public static final String subscriptionKey = "d8e47516b5ec45d4acaa8f1191713edb";

    // Replace or verify the region.
    //
    // You must use the same region in your REST API call as you used to obtain your subscription keys.
    // For example, if you obtained your subscription keys from the westus region, replace
    // "westcentralus" in the URI below with "westus".
    //
    // NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
    // a free trial subscription key, you should not need to change this region.
    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";


    public static void main(String[] args)
    {
        HttpClient httpclient = new DefaultHttpClient();

        try
        {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceLandmarks", "false");
            builder.setParameter("returnFaceAttributes", "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise");

            // Prepare the URI for the REST API call.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            StringEntity reqEntity = new StringEntity("{\"url\":https://lh3.googleusercontent.com/nIOKj1FXaY6xzOOPzhcFdzFjXv-OsmqQ_b-RIrQXGMYNPpHnbqz52FY9cFLfnBGBJmglR57lBp0kbvK_IKQdpyGqtwMpAXDbkbgz-dCPd_VlPAvhTrWEugQ0HEPSdpQSQxSJMk2CJRjrbK2dmoZiCOb-cYB8p_NnRyEBXLhBIs_uQdt1v16osd4Z8O7KttxgwbE3Yl7pnek9AX8W3e0sa6Xbi7NN4fILY881GDVeLCuZG4wwCLMfgSGaDFdB06ozQbgtSnVqtEVK53PlOopwTH_ApYuhKn92wL3D7FWtuGAwEXuq4SqkncQZUiVhEH00ZXpLyF8XQ5A7TPxXWM_-OLTQYB6gpstaRS1hbaxe0mLGbGKJ7I-jgKUVP7S4RAhsYkcRviE1ubIWOJa-A44RhF7jAnxMoTKe7l4N9Yc4rAHOL_pcaz60yal2ZgsWvN6jTqSdJxXVQKGaWl0b98fJ-dyIwJHdqYVTXPsefvAJs0BQjcyHmVwWND8kUSGs8kzuWqw4wc2bBB3Dp1KMIj8LhGtcaRnoBSPwyf_QePVQ-HFDA1v5gdc2ENHBmqmjeVaL9X0OAP5kzkIFkKJ_iTWCq1rO8oooLOcgr-w9iIBmxPxLRC_A-w=s0}");
            request.setEntity(reqEntity);

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                System.out.println("REST Response:\n");

                String jsonString = EntityUtils.toString(entity).trim();
                if (jsonString.charAt(0) == '[') {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    System.out.println(jsonArray.toString(2));
                }
                else if (jsonString.charAt(0) == '{') {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    System.out.println(jsonObject.toString(2));
                } else {
                    System.out.println(jsonString);
                }
            }
        }
        catch (Exception e)
        {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }
}