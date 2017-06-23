import httplib, urllib, base64, json

###############################################
#### Update or verify the following values. ###
###############################################

# Replace the subscription_key string value with your valid subscription key.
subscription_key = 'd8e47516b5ec45d4acaa8f1191713edb'

# Replace or verify the region.
#
# You must use the same region in your REST API call as you used to obtain your subscription keys.
# For example, if you obtained your subscription keys from the westus region, replace 
# "westcentralus" in the URI below with "westus".
#
# NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
# a free trial subscription key, you should not need to change this region.
uri_base = 'westcentralus.api.cognitive.microsoft.com'

# Request headers.
headers = {
    'Content-Type': 'application/json',
    'Ocp-Apim-Subscription-Key': subscription_key,
}

# Request parameters.
params = urllib.urlencode({
    'returnFaceId': 'true',
    'returnFaceLandmarks': 'false',
    'returnFaceAttributes': 'age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise',
})

# The URL of a JPEG image to analyze.
body = "{'url':'https://lh3.googleusercontent.com/nIOKj1FXaY6xzOOPzhcFdzFjXv-OsmqQ_b-RIrQXGMYNPpHnbqz52FY9cFLfnBGBJmglR57lBp0kbvK_IKQdpyGqtwMpAXDbkbgz-dCPd_VlPAvhTrWEugQ0HEPSdpQSQxSJMk2CJRjrbK2dmoZiCOb-cYB8p_NnRyEBXLhBIs_uQdt1v16osd4Z8O7KttxgwbE3Yl7pnek9AX8W3e0sa6Xbi7NN4fILY881GDVeLCuZG4wwCLMfgSGaDFdB06ozQbgtSnVqtEVK53PlOopwTH_ApYuhKn92wL3D7FWtuGAwEXuq4SqkncQZUiVhEH00ZXpLyF8XQ5A7TPxXWM_-OLTQYB6gpstaRS1hbaxe0mLGbGKJ7I-jgKUVP7S4RAhsYkcRviE1ubIWOJa-A44RhF7jAnxMoTKe7l4N9Yc4rAHOL_pcaz60yal2ZgsWvN6jTqSdJxXVQKGaWl0b98fJ-dyIwJHdqYVTXPsefvAJs0BQjcyHmVwWND8kUSGs8kzuWqw4wc2bBB3Dp1KMIj8LhGtcaRnoBSPwyf_QePVQ-HFDA1v5gdc2ENHBmqmjeVaL9X0OAP5kzkIFkKJ_iTWCq1rO8oooLOcgr-w9iIBmxPxLRC_A-w=s0'}"

try:
    # Execute the REST API call and get the response.
    conn = httplib.HTTPSConnection(uri_base)
    conn.request("POST", "/face/v1.0/detect?%s" % params, body, headers)
    response = conn.getresponse()
    data = response.read()

    print type(data)
    # 'data' contains the JSON data. The following formats the JSON data for display.
    parsed = json.loads(data)
    print parsed[0]['emotion']
    # print ("Response:")
    # print (json.dumps(parsed, sort_keys=True, indent=2))
    conn.close()

except Exception as e:
    print("[Errno {0}] {1}".format(e.errno, e.strerror))