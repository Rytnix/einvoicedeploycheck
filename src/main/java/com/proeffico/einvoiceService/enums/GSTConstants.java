package com.proeffico.einvoiceService.enums;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class GSTConstants {

    public static String USERNAME ="nsdlTest" ;
    public static String PASSWORD = "Test@123";
    public static String ASPID = "08ABACS8130M000832";
    public static String AUTH_URL = "https://test.proteangsp.co.in/gus/irp/nic/eivital/v1.04/auth" ;
    public static String GET_EINVOICE_URL = "https://test.proteangsp.co.in/gus/irp/nic/eicore/v1.03/Invoice/irn/";
    public static String GENERATE_EINVOICE_URL = "https://test.proteangsp.co.in/gus/irp/nic/eicore/v1.03/Invoice";
}
