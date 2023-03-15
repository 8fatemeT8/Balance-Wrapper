package com.refah.walletwrapper.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsiEventLogger {
    final static Logger eventLogger = LoggerFactory.getLogger("csi.event.log");

    public static void main(String[] args) {
        logEvent("0968e148-f787-4734-9b9e-9cbdcfd63d81#1", null, null, null, null, null, null);
    }

    private static void logEvent(String transactionId, String service, Integer latency,
                                 String statusCode, String responseCode, String responseDescription, String remarks) {
        String uriEntry = "msisdn:9099547175";
        String tenant = "porsche";
        String country = "US";
        String action = "Subscriptions.Add.Prepaid";
        service = "AddPrepaid";
        latency = 323;
        statusCode = "200";
        responseCode = "30001270001";
        responseDescription = "success";

        try {
            eventLogger.debug(transactionId + "," + uriEntry + "," + tenant + "," + country + "," + action +
                    "," + service + "," + latency + "," + statusCode + "," + responseCode + "," + responseDescription +
                    "," + "faultcode" + "," + "faultdesc" + "," + remarks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
