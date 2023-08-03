package com.proeffico.einvoiceService.config;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESEncryption {

    public static String encrypt(String data, String key) throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String jsonData = "{\n" +
                    "  \"version\": \"1.1\",\n" +
                    "  \"tranDtls\": {\n" +
                    "    \"taxSch\": \"GST\",\n" +
                    "    \"supTyp\": \"B2B\",\n" +
                    "    \"regRev\": \"Y\",\n" +
                    "    \"ecmGstin\": null,\n" +
                    "    \"igstOnIntra\": \"N\"\n" +
                    "  },\n" +
                    "  \"docDtls\": {\n" +
                    "    \"typ\": \"INV\",\n" +
                    "    \"no\": \"DOC/001\",\n" +
                    "    \"dt\": \"18/08/2020\"\n" +
                    "  },\n" +
                    "  \"sellerDtls\": {\n" +
                    "    \"gstin\": \"37ARZPT4384Q1MT\",\n" +
                    "    \"lglNm\": \"NIC company pvt ltd\",\n" +
                    "    \"trdNm\": \"NIC Industries\",\n" +
                    "    \"addr1\": \"5th block, kuvempu layout\",\n" +
                    "    \"addr2\": \"kuvempu layout\",\n" +
                    "    \"loc\": \"GANDHINAGAR\",\n" +
                    "    \"pin\": 518001,\n" +
                    "    \"stcd\": \"37\",\n" +
                    "    \"ph\": \"9000000000\",\n" +
                    "    \"em\": \"abc@gmail.com\"\n" +
                    "  },\n" +
                    "  \"buyerDtls\": {\n" +
                    "    \"gstin\": \"29AWGPV7107B1Z1\",\n" +
                    "    \"lglNm\": \"XYZ company pvt ltd\",\n" +
                    "    \"trdNm\": \"XYZ Industries\",\n" +
                    "    \"pos\": \"12\",\n" +
                    "    \"addr1\": \"7th block, kuvempu layout\",\n" +
                    "    \"addr2\": \"kuvempu layout\",\n" +
                    "    \"loc\": \"GANDHINAGAR\",\n" +
                    "    \"pin\": 562160,\n" +
                    "    \"stcd\": \"29\",\n" +
                    "    \"ph\": \"91111111111\",\n" +
                    "    \"em\": \"xyz@yahoo.com\"\n" +
                    "  },\n" +
                    "  \"dispDtls\": {\n" +
                    "    \"nm\": \"ABC company pvt ltd\",\n" +
                    "    \"addr1\": \"7th block, kuvempu layout\",\n" +
                    "    \"addr2\": \"kuvempu layout\",\n" +
                    "    \"loc\": \"Banagalore\",\n" +
                    "    \"pin\": 562160,\n" +
                    "    \"stcd\": \"29\"\n" +
                    "  },\n" +
                    "  \"shipDtls\": {\n" +
                    "    \"gstin\": \"29AWGPV7107B1Z1\",\n" +
                    "    \"lglNm\": \"CBE company pvt ltd\",\n" +
                    "    \"trdNm\": \"kuvempu layout\",\n" +
                    "    \"addr1\": \"7th block, kuvempu layout\",\n" +
                    "    \"addr2\": \"kuvempu layout\",\n" +
                    "    \"loc\": \"Banagalore\",\n" +
                    "    \"pin\": 562160,\n" +
                    "    \"stcd\": \"29\"\n" +
                    "  },\n" +
                    "  \"itemList\": [\n" +
                    "    {\n" +
                    "      \"slNo\": \"1\",\n" +
                    "      \"prdDesc\": \"Rice\",\n" +
                    "      \"isServc\": \"N\",\n" +
                    "      \"hsnCd\": \"1001\",\n" +
                    "      \"barcde\": \"123456\",\n" +
                    "      \"qty\": 100.345,\n" +
                    "      \"freeQty\": 10,\n" +
                    "      \"unit\": \"BAG\",\n" +
                    "      \"unitPrice\": 99.545,\n" +
                    "      \"totAmt\": 9988.84,\n" +
                    "      \"discount\": 10,\n" +
                    "      \"preTaxVal\": 1,\n" +
                    "      \"assAmt\": 9978.84,\n" +
                    "      \"gstRt\": 12.0,\n" +
                    "      \"igstAmt\": 1197.46,\n" +
                    "      \"cgstAmt\": 0,\n" +
                    "      \"sgstAmt\": 0,\n" +
                    "      \"cesRt\": 5,\n" +
                    "      \"cesAmt\": 498.94,\n" +
                    "      \"cesNonAdvlAmt\": 10,\n" +
                    "      \"stateCesRt\": 12,\n" +
                    "      \"stateCesAmt\": 1197.46,\n" +
                    "      \"stateCesNonAdvlAmt\": 5,\n" +
                    "      \"othChrg\": 10,\n" +
                    "      \"totItemVal\": 12897.7,\n" +
                    "      \"ordLineRef\": \"3256\",\n" +
                    "      \"orgCntry\": \"AG\",\n" +
                    "      \"prdSlNo\": \"12345\",\n" +
                    "      \"bchDtls\": {\n" +
                    "        \"nm\": \"123456\",\n" +
                    "        \"expDt\": \"01/08/2020\",\n" +
                    "        \"wrDt\": \"01/09/2020\"\n" +
                    "      },\n" +
                    "      \"attribDtls\": [\n" +
                    "        {\n" +
                    "          \"nm\": \"Rice\",\n" +
                    "          \"val\": \"10000\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"valDtls\": {\n" +
                    "    \"assVal\": 9978.84,\n" +
                    "    \"cgstVal\": 0,\n" +
                    "    \"sgstVal\": 0,\n" +
                    "    \"igstVal\": 1197.46,\n" +
                    "    \"cesVal\": 508.94,\n" +
                    "    \"stCesVal\": 1202.46,\n" +
                    "    \"discount\": 10,\n" +
                    "    \"othChrg\": 20,\n" +
                    "    \"rndOffAmt\": 0.3,\n" +
                    "    \"totInvVal\": 12908,\n" +
                    "    \"totInvValFc\": 12897.7\n" +
                    "  },\n" +
                    "  \"payDtls\": {\n" +
                    "    \"nm\": \"ABCDE\",\n" +
                    "    \"accDet\": \"5697389713210\",\n" +
                    "    \"mode\": \"Cash\",\n" +
                    "    \"finInsBr\": \"SBIN11000\",\n" +
                    "    \"payTerm\": \"100\",\n" +
                    "    \"payInstr\": \"Gift\",\n" +
                    "    \"crTrn\": \"test\",\n" +
                    "    \"dirDr\": \"test\",\n" +
                    "    \"crDay\": 100,\n" +
                    "    \"paidAmt\": 10000,\n" +
                    "    \"paymtDue\": 5000\n" +
                    "  },\n" +
                    "  \"refDtls\": {\n" +
                    "    \"invRm\": \"TEST\",\n" +
                    "    \"docPerdDtls\": {\n" +
                    "      \"invStDt\": \"01/08/2020\",\n" +
                    "      \"invEndDt\": \"01/09/2020\"\n" +
                    "    },\n" +
                    "    \"precDocDtls\": [\n" +
                    "      {\n" +
                    "        \"invNo\": \"DOC/002\",\n" +
                    "        \"invDt\": \"01/08/2020\",\n" +
                    "        \"othRefNo\": \"123456\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"contrDtls\": [\n" +
                    "      {\n" +
                    "        \"recAdvRefr\": \"Doc/003\",\n" +
                    "        \"recAdvDt\": \"01/08/2020\",\n" +
                    "        \"tendRefr\": \"Abc001\",\n" +
                    "        \"contrRefr\": \"Co123\",\n" +
                    "        \"extRefr\": \"Yo456\",\n" +
                    "        \"projRefr\": \"Doc-456\",\n" +
                    "        \"poRefr\": \"Doc-789\",\n" +
                    "        \"poRefDt\": \"01/08/2020\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  \"addlDocDtls\": [\n" +
                    "    {\n" +
                    "      \"url\": \"https://einv-apisandbox.nic.in\",\n" +
                    "      \"docs\": \"Test Doc\",\n" +
                    "      \"info\": \"Document Test\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"expDtls\": {\n" +
                    "    \"shipBNo\": \"A-248\",\n" +
                    "    \"shipBDt\": \"01/08/2020\",\n" +
                    "    \"port\": \"INABG1\",\n" +
                    "    \"refClm\": \"N\",\n" +
                    "    \"forCur\": \"AED\",\n" +
                    "    \"cntCode\": \"AE\",\n" +
                    "    \"expDuty\": null\n" +
                    "  },\n" +
                    "  \"ewbDtls\": {\n" +
                    "    \"transId\": \"12AWGPV7107B1Z1\",\n" +
                    "    \"transName\": \"XYZ EXPORTS\",\n" +
                    "    \"distance\": 100,\n" +
                    "    \"transDocNo\": \"DOC01\",\n" +
                    "    \"transDocDt\": \"18/08/2020\",\n" +
                    "    \"vehNo\": \"ka123456\",\n" +
                    "    \"vehType\": \"R\",\n" +
                    "    \"transMode\": \"1\"\n" +
                    "  }\n" +
                    "}";
 String Data = "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";
            String key = "F9qDAGk3PvcwkbNPDAl7m/b4QLqJZUUUa4rPpvgAQIw=";

            Data = "\"{\\\"Version\\\":\\\"1.1\\\",\\\"TranDtls\\\":{\\\"TaxSch\\\":\\\"GST\\\",\\\"SupTyp\\\":\\\"B2B\\\",\\\"RegRev\\\":\\\"Y\\\",\\\"EcmGstin\\\":null,\\\"IgstOnIntra\\\":\\\"N\\\"},\\\"DocDtls\\\":{\\\"Typ\\\":\\\"INV\\\",\\\"No\\\":\\\"DOC\\/001\\\",\\\"Dt\\\":\\\"18\\/08\\/2020\\\"},\\\"SellerDtls\\\":{\\\"Gstin\\\":\\\"37ARZPT4384Q1MT\\\",\\\"LglNm\\\":\\\"NIC company pvt ltd\\\",\\\"TrdNm\\\":\\\"NIC Industries\\\",\\\"Addr1\\\":\\\"5th block, kuvempu layout\\\",\\\"Addr2\\\":\\\"kuvempu layout\\\",\\\"Loc\\\":\\\"GANDHINAGAR\\\",\\\"Pin\\\":518001,\\\"Stcd\\\":\\\"37\\\",\\\"Ph\\\":\\\"9000000000\\\",\\\"Em\\\":\\\"abc@gmail.com\\\"},\\\"BuyerDtls\\\":{\\\"Gstin\\\":\\\"29AWGPV7107B1Z1\\\",\\\"LglNm\\\":\\\"XYZ company pvt ltd\\\",\\\"TrdNm\\\":\\\"XYZ Industries\\\",\\\"Pos\\\":\\\"12\\\",\\\"Addr1\\\":\\\"7th block, kuvempu layout\\\",\\\"Addr2\\\":\\\"kuvempu layout\\\",\\\"Loc\\\":\\\"GANDHINAGAR\\\",\\\"Pin\\\":562160,\\\"Stcd\\\":\\\"29\\\",\\\"Ph\\\":\\\"91111111111\\\",\\\"Em\\\":\\\"xyz@yahoo.com\\\"},\\\"DispDtls\\\":{\\\"Nm\\\":\\\"ABC company pvt ltd\\\",\\\"Addr1\\\":\\\"7th block, kuvempu layout\\\",\\\"Addr2\\\":\\\"kuvempu layout\\\",\\\"Loc\\\":\\\"Banagalore\\\",\\\"Pin\\\":562160,\\\"Stcd\\\":\\\"29\\\"},\\\"ShipDtls\\\":{\\\"Gstin\\\":\\\"29AWGPV7107B1Z1\\\",\\\"LglNm\\\":\\\"CBE company pvt ltd\\\",\\\"TrdNm\\\":\\\"kuvempu layout\\\",\\\"Addr1\\\":\\\"7th block, kuvempu layout\\\",\\\"Addr2\\\":\\\"kuvempu layout\\\",\\\"Loc\\\":\\\"Banagalore\\\",\\\"Pin\\\":562160,\\\"Stcd\\\":\\\"29\\\"},\\\"ItemList\\\":[{\\\"SlNo\\\":\\\"1\\\",\\\"PrdDesc\\\":\\\"Rice\\\",\\\"IsServc\\\":\\\"N\\\",\\\"HsnCd\\\":\\\"1001\\\",\\\"Barcde\\\":\\\"123456\\\",\\\"Qty\\\":100.345,\\\"FreeQty\\\":10,\\\"Unit\\\":\\\"BAG\\\",\\\"UnitPrice\\\":99.545,\\\"TotAmt\\\":9988.84,\\\"Discount\\\":10,\\\"PreTaxVal\\\":1,\\\"AssAmt\\\":9978.84,\\\"GstRt\\\":12,\\\"IgstAmt\\\":1197.46,\\\"CgstAmt\\\":0,\\\"SgstAmt\\\":0,\\\"CesRt\\\":5,\\\"CesAmt\\\":498.94,\\\"CesNonAdvlAmt\\\":10,\\\"StateCesRt\\\":12,\\\"StateCesAmt\\\":1197.46,\\\"StateCesNonAdvlAmt\\\":5,\\\"OthChrg\\\":10,\\\"TotItemVal\\\":12897.7,\\\"OrdLineRef\\\":\\\"3256\\\",\\\"OrgCntry\\\":\\\"AG\\\",\\\"PrdSlNo\\\":\\\"12345\\\",\\\"BchDtls\\\":{\\\"Nm\\\":\\\"123456\\\",\\\"ExpDt\\\":\\\"01\\/08\\/2020\\\",\\\"WrDt\\\":\\\"01\\/09\\/2020\\\"},\\\"AttribDtls\\\":[{\\\"Nm\\\":\\\"Rice\\\",\\\"Val\\\":\\\"10000\\\"}]}],\\\"ValDtls\\\":{\\\"AssVal\\\":9978.84,\\\"CgstVal\\\":0,\\\"SgstVal\\\":0,\\\"IgstVal\\\":1197.46,\\\"CesVal\\\":508.94,\\\"StCesVal\\\":1202.46,\\\"Discount\\\":10,\\\"OthChrg\\\":20,\\\"RndOffAmt\\\":0.3,\\\"TotInvVal\\\":12908,\\\"TotInvValFc\\\":12897.7},\\\"PayDtls\\\":{\\\"Nm\\\":\\\"ABCDE\\\",\\\"AccDet\\\":\\\"5697389713210\\\",\\\"Mode\\\":\\\"Cash\\\",\\\"FinInsBr\\\":\\\"SBIN11000\\\",\\\"PayTerm\\\":\\\"100\\\",\\\"PayInstr\\\":\\\"Gift\\\",\\\"CrTrn\\\":\\\"test\\\",\\\"DirDr\\\":\\\"test\\\",\\\"CrDay\\\":100,\\\"PaidAmt\\\":10000,\\\"PaymtDue\\\":5000},\\\"RefDtls\\\":{\\\"InvRm\\\":\\\"TEST\\\",\\\"DocPerdDtls\\\":{\\\"InvStDt\\\":\\\"01\\/08\\/2020\\\",\\\"InvEndDt\\\":\\\"01\\/09\\/2020\\\"},\\\"PrecDocDtls\\\":[{\\\"InvNo\\\":\\\"DOC\\/002\\\",\\\"InvDt\\\":\\\"01\\/08\\/2020\\\",\\\"OthRefNo\\\":\\\"123456\\\"}],\\\"ContrDtls\\\":[{\\\"RecAdvRefr\\\":\\\"Doc\\/003\\\",\\\"RecAdvDt\\\":\\\"01\\/08\\/2020\\\",\\\"TendRefr\\\":\\\"Abc001\\\",\\\"ContrRefr\\\":\\\"Co123\\\",\\\"ExtRefr\\\":\\\"Yo456\\\",\\\"ProjRefr\\\":\\\"Doc-456\\\",\\\"PORefr\\\":\\\"Doc-789\\\",\\\"PORefDt\\\":\\\"01\\/08\\/2020\\\"}]},\\\"AddlDocDtls\\\":[{\\\"Url\\\":\\\"https:\\/\\/einv-apisandbox.nic.in\\\",\\\"Docs\\\":\\\"Test Doc\\\",\\\"Info\\\":\\\"Document Test\\\"}],\\\"ExpDtls\\\":{\\\"ShipBNo\\\":\\\"A-248\\\",\\\"ShipBDt\\\":\\\"01\\/08\\/2020\\\",\\\"Port\\\":\\\"INABG1\\\",\\\"RefClm\\\":\\\"N\\\",\\\"ForCur\\\":\\\"AED\\\",\\\"CntCode\\\":\\\"AE\\\",\\\"ExpDuty\\\":null},\\\"EwbDtls\\\":{\\\"TransId\\\":\\\"12AWGPV7107B1Z1\\\",\\\"TransName\\\":\\\"XYZ EXPORTS\\\",\\\"Distance\\\":100,\\\"TransDocNo\\\":\\\"DOC01\\\",\\\"TransDocDt\\\":\\\"18\\/08\\/2020\\\",\\\"VehNo\\\":\\\"ka123456\\\",\\\"VehType\\\":\\\"R\\\",\\\"TransMode\\\":\\\"1\\\"}}\"";
            String encryptedData = encrypt(Data,key);
            System.out.println("Encrypted Output:");
            System.out.println(encryptedData);
            System.out.println(Data.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
