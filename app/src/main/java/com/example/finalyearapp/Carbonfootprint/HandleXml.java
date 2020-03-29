package com.example.finalyearapp.Carbonfootprint;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleXml {

    private String transport = "transport";
    private String home = "home";
    private String purchasing = "purchasing";
    private String food = "food";
    private String total = "total";
    private String urlstring = null;
    private XmlPullParserFactory xmlFactoryObject;

    public String getTotal() {
        return total;
    }

    public volatile boolean parsingComplete=true;

    public String getTransport() {
        return transport;
    }

    public String getHome() {
        return home;
    }

    public String getPurchasing() {
        return purchasing;
    }

    public String getFood() {
        return food;
    }

    public HandleXml(String url){
        this.urlstring=url;

    }

    public void parseXMLAndStoreIt(XmlPullParser myParser){
        int event;
        String text = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("result_transport_total")) {
                            transport =  myParser.getText();
                        } else if (name.equals("result_housing_total")) {
                            home = myParser.getText();
                        } else if (name.equals("result_food_totalood")) {
                            food =  myParser.getText();
                        } else if (name.equals("result_goods_total")) {
                            purchasing = myParser.getText();
                        }
                        else if (name.equals("result_grand_total")) {
                            total = myParser.getText();
                        }else {
                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        }
        catch (Exception e){
                e.printStackTrace();

            }



        }

    public void fetchXML(){
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(urlstring);
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setReadTimeout(100000);
                connect.setConnectTimeout(150000);
                connect.setRequestMethod("GET");
                connect.setDoInput(true);
                connect.connect();

                InputStream stream = connect.getInputStream();
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser myparser = xmlFactoryObject.newPullParser();
                myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                myparser.setInput(stream, null);
                parseXMLAndStoreIt(myparser);
                stream.close();


            }catch (Exception e){
                e.printStackTrace();
            }


        }
    });
thread.start();
}
}
