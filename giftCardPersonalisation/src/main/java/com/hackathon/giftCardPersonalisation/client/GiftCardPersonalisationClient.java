package com.hackathon.giftCardPersonalisation.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.giftCardPersonalisation.model.GiftCardBalanceRequest;
import com.hackathon.giftCardPersonalisation.model.GiftCardBalanceResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;


@Service
@Log4j2
public class GiftCardPersonalisationClient {
    @Autowired
    private RestTemplate restTemplate;

    public Optional<GiftCardBalanceResponse> getGiftCardBalanceResponse(
            String url,
            GiftCardBalanceRequest giftCardBalanceRequest) {
//        log.info("url\n{}\nInvoiceSearchRequest\n{}", url, giftCardBalanceRequest);
//        HttpHeaders headers = getHttpHeadersForGetInv();
//        HttpEntity<Object> requestEntity = new HttpEntity<>(prepareInventorySearchRequest(giftCardBalanceRequest), headers);
//        GiftCardBalanceResponse giftCardBalanceResponse = new GiftCardBalanceResponse();
//        try {
//            ResponseEntity<String> responseEntity = restTemplate.exchange(
//                    url,HttpMethod.POST,requestEntity, String.class);
//            if (HttpStatus.OK == responseEntity.getStatusCode() &&  Objects.nonNull(responseEntity.getBody())) {
//                log.info("{}", responseEntity.getBody());
//                giftCardBalanceResponse.setPromiseLinesOutput(getInventoryFromXml(responseEntity.getBody(), "PromiseLinesOutput"));
//                return Optional.of(giftCardBalanceResponse);
//            }
//
//        } catch (Exception ex) {
//            log.error("Error occurred while getting inventoryDetails ", ex);
//        }
//        return Optional.of(giftCardBalanceResponse);

    }
    public Optional<InventoryAddResponse> getInventoryAddResponse(
            String url,
            InventoryAddRequest inventoryAddRequest) {
        log.info("url\n{}\nInvoiceAddRequest\n{}", url, inventoryAddRequest);

        HttpHeaders headers = getHttpHeadersForAddInv();
        HttpEntity<Object> requestEntity = new HttpEntity<>(prepareInventoryAddRequest(inventoryAddRequest), headers);
        InventoryAddResponse inventoryAddResponse = new InventoryAddResponse();
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url, HttpMethod.POST, requestEntity, String.class);
            if (HttpStatus.OK == responseEntity.getStatusCode() &&  Objects.nonNull(responseEntity.getBody())) {
                log.info("{}", responseEntity.getBody());
                inventoryAddResponse.setUserMessage(responseEntity.getBody());
                return Optional.of(inventoryAddResponse);
            }

        } catch (Exception ex) {
            log.error("Error occurred while adding inventoryDetails ", ex);
        }
        return Optional.of(inventoryAddResponse);

    }
    private List<PromiseLinesOutput> getInventoryFromXml(String response, String tagName) throws Exception {
        Document xmlDoc = loadXMLString(response);
        NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
        List<PromiseLinesOutput> inventoryDetailsList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element x = (Element) nodeList.item(i);
            String skuId = x.getElementsByTagName("skuId").item(0).getTextContent();
            String quantity = x.getElementsByTagName("quantity").item(0).getTextContent();
            String dcOrStore = x.getElementsByTagName("dc").item(0).getTextContent();
            String onHoldState = x.getElementsByTagName("onHold").item(0).getTextContent();
            PromiseLinesOutput inventoryDetails = new PromiseLinesOutput();
            inventoryDetails.setSkuId(skuId);
            inventoryDetails.setQuantity(Integer.valueOf(quantity));
            inventoryDetails.setDc(dcOrStore);
            inventoryDetails.setOnHold(onHoldState);
            inventoryDetailsList.add(inventoryDetails);
        }
        return inventoryDetailsList;
    }
    private Document loadXMLString(String response) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(response)));
        return doc;
    }



    private String  prepareInventorySearchRequest(InventorySearchRequest inventorySearchRequest) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v3=\"http://schemas.gid.gap.com/icm/findInventory/v3-1\">\n" +
                "    <soapenv:Header />\n" +
                "    <soapenv:Body>\n" +
                "        <v3:FindGlobalInventoryRequest>\n" +
                "            <market>"+inventorySearchRequest.getMarket()+"</market>\n" +
                "            <itemId>"+inventorySearchRequest.getSkuId()+"</itemId>\n" +
                "        </v3:FindGlobalInventoryRequest>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>\n";
    }
    private String prepareInventoryAddRequest(InventoryAddRequest inventoryAddRequest){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dto=\"http://schemas.gid.gap.com/inventorycapacitymanagement/v2/dto\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "              <m:AdjustInventoryRequest xmlns:m=\"http://schemas.gid.gap.com/inventorycapacitymanagement/v2/dto\">\n" +
                "\t\t  <transactionType>ADJUSTMENT_ADJ</transactionType>\n" +
                "\t\t  <fromDC></fromDC>\n" +
                "\t\t  <toDC>"+inventoryAddRequest.getToDC()+"</toDC>\n" +
                "\t\t  <orderNumber></orderNumber>\n" +
                "\t\t  <AdjustInventoryLines>\n" +
                "\t\t\t<AdjustInventoryLine>\n" +
                "\t\t\t   <skuId>"+inventoryAddRequest.getSkuId()+"</skuId>\n" +
                "\t\t\t   <adjustmentQty>"+inventoryAddRequest.getAdjustmentQty()+"</adjustmentQty>\n" +
                "\t\t\t   <inDCDate></inDCDate>\n" +
                "\t\t\t</AdjustInventoryLine>\n" +
                "\t\t  </AdjustInventoryLines>\n" +
                "\t\t  <Requestor>\n" +
                "\t\t\t<programId>QA_PROGRAM</programId>\n" +
                "\t\t\t<userId>QA_AUTOMATION</userId>\n" +
                "\t\t\t<hostname>QA_HOST</hostname>\n" +
                "\t\t\t<transRefName>ref_name</transRefName>\n" +
                "\t\t\t<transRefValue>ref_value</transRefValue>\n" +
                "\t\t  </Requestor>\n" +
                "\t\t</m:AdjustInventoryRequest>\n" +
                "\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
    private HttpHeaders getHttpHeadersForGetInv() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("text/xml;charset=UTF-8"));
       // headers.setAccept(Collections.singletonList(MediaType.valueOf("text/xml;charset=UTF-8")));
        headers.addIfAbsent("SOAPAction","findInventoryByDC");
        return headers;
    }

    private HttpHeaders getHttpHeadersForAddInv() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("text/xml;charset=UTF-8"));
        headers.setAccept(Collections.singletonList(MediaType.valueOf("text/xml;charset=UTF-8")));
        headers.addIfAbsent("SOAPAction","adjustInventory");
        return headers;
    }

    public List<Browse> getBrowse(BrowseRequest browseRequest) {
        Map<String, List<Browse>> browseMap = getBrowseMap();
        for( String str:browseMap.keySet()){
            if(browseRequest.getKeyword().toLowerCase().contains(str.toLowerCase()))
            return browseMap.get(str);
        }
        return null;
    }

    static Map<String, List<Browse>> getBrowseMap()
    {
        Map<String, List<Browse>> browseMap = new HashMap<>();
        List<Browse> browseList = new ArrayList<>();
        browseList.add(new Browse("5759670020009", "Regular XXS","https://brol.stage.gaptechol.com/webcontent/0018/530/303/cn18530303.jpg","$22.99","Untucked Slim-Fit Non-Iron Dress Shirt"));
        browseList.add(new Browse("6653730020002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0020/251/203/cn20251203.jpg","$64.50","Untucked Slim-Fit Cotton Oxford Shirt"));
        browseList.add(new Browse("7074320520002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0020/509/905/cn20509905.jpg","$64.50","Slim-Fit Tech-Stretch Cotton Shirt"));
        browseList.add(new Browse("6308870220002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0019/688/164/cn19688164.jpg","$64.50","Untucked Slim-Fit Cotton Shirt"));
         browseMap.put("shirt", browseList);
        List<Browse> browseList1 = new ArrayList<>();
         browseList1.add(new Browse("6180392520304", "Tall L","https://brol.stage.gaptechol.com/webcontent/0019/688/093/cn19688093.jpg","$34.50","Organic Soft Wash Crew-Neck T-Shirt"));
        browseList1.add(new Browse("3622460220002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0019/138/751/cn19138751.jpg","$54.50","Luxury-Touch Performance Golf Polo"));
        browseList1.add(new Browse("6786820320000", "Regular XS","https://brol.stage.gaptechol.com/webcontent/0020/313/457/cn20313457.jpg","$59.50","Core Temp Sweater Polo"));
        browseList1.add(new Browse("6180391720002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0020/304/730/cn20304730.jpg","$34.50","Organic Soft Wash Crew-Neck T-Shirt"));
        browseMap.put("polo", browseList1);
        List<Browse> browseList2 = new ArrayList<>();
        browseList2.add(new Browse("8099540220002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0019/887/332/cn19887332.jpg","$69.50","Organic French Terry Hoodie"));
        browseList2.add(new Browse("6641340220002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0019/586/587/cn19586587.jpg","$49.50","Authentic SUPIMA® T-Shirt Hoodie"));
        browseList2.add(new Browse("6545320120002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0019/566/432/cn19566432.jpg","$69.50","Core Temp Mesh T-Shirt Hoodie"));
        browseList2.add(new Browse("6545320020002", "Regular M","https://brol.stage.gaptechol.com/webcontent/0019/566/446/cn19566446.jpg","$69.50","Core Temp Mesh T-Shirt Hoodie"));
        browseMap.put("hoodie", browseList2);
        List<Browse> browseList3 = new ArrayList<>();
        browseList3.add(new Browse("5006490022828", "Regular 28W 28L","https://brol.stage.gaptechol.com/webcontent/0017/575/218/cn17575218.jpg","$98.50","Skinny Legacy Jean"));
        browseList3.add(new Browse("4886220023030", "Regular 30W 30L","https://brol.stage.gaptechol.com/webcontent/0017/566/600/cn17566600.jpg","$98.50","Slim Legacy Jean"));
        browseList3.add(new Browse("6190820222932", "Regular 29W 32L","https://brol.stage.gaptechol.com/webcontent/0019/490/042/cn19490042.jpg","$98.50","Slim Legacy Jean"));
        browseList3.add(new Browse("5006480022832", "Regular 28W 32L","https://brol.stage.gaptechol.com/webcontent/0017/566/640/cn17566640.jpg","$98.50","Skinny Legacy Jean"));
        browseMap.put("jeans", browseList3);
        return browseMap;
    }


    public Optional<CatalogSearchResponse> getCatalogSearchResponse(
            String url,
            CatalogSearchRequest catalogSearchRequest) {
        log.info("url\n{}\nCatalogSearchRequest\n{}", url, catalogSearchRequest);
        Optional<CatalogSearchResponse> catalogSearchResponse = Optional.empty();
        try {
            HttpHeaders headers = getHttpHeaders();
            HttpEntity<Object> requestEntity = new HttpEntity<>(catalogSearchRequest, headers);
            ResponseEntity<CatalogSearchResponse> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<CatalogSearchResponse>() {
                    });
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                log.info("{}", responseEntity.getBody());
                return
                        Optional.ofNullable(responseEntity.getBody());
            }

        } catch (Exception ex) {
            log.error("Error occurred while getting catalogDetails ", ex);
        }
        return catalogSearchResponse;

    }

    public Optional<OrderStatusResponse> getOrderStatusResponse(
            String url,
            OrderStatusRequest orderStatusRequest) {
        log.info("url\n{}\nOrderStatusRequest\n{}", url, orderStatusRequest);
        Optional<OrderStatusResponse> orderStatusResponse = Optional.empty();
        try {
            HttpHeaders headers = getHttpHeaders();
            HttpEntity<Object> requestEntity = new HttpEntity<>(orderStatusRequest, headers);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    url, String.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                log.info("{}", responseEntity.getBody());
                ObjectMapper obj = new ObjectMapper();
                return
                        Optional.ofNullable(obj.readValue(responseEntity.getBody(),OrderStatusResponse.class));
            }

        } catch (Exception ex) {
            log.error("Error occurred while getting order status ", ex);
        }
        return orderStatusResponse;

    }

    public String getStoreInfo(String storeNumber) {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.set("ApiKey", "gIMdhprdvO3xA4SYTeAF8PFA00Ien1T1");
        httpHeaders.set("apiVersion", "1");
        try{
        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://stage.api.azeus.gaptech.com/commerce/locations/stores/" + storeNumber,
                HttpMethod.GET,
                requestEntity,
                String.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            log.info("{}", responseEntity.getBody());
            ObjectMapper obj = new ObjectMapper();
            return obj.readValue(responseEntity.getBody(),StoreInfo.class).getStoreName();
        }
        }
        catch (Exception ex) {
            log.error("Error occurred while getting inventoryDetails ", ex);
        }
        return null;
    }


    public Optional<AdjustHeaderPriceResponse> adjustHeaderPrice(
            String url,
            AdjustHeaderPriceRequest adjustHeaderPriceRequest) {
        log.info("url\n{}\nAdjustHeaderPriceRequest\n{}", url, adjustHeaderPriceRequest);
        Optional<AdjustHeaderPriceResponse> adjustHeaderPriceResponse = Optional.empty();
        try {
            HttpHeaders headers = getHttpHeaders();
            HttpEntity<Object> requestEntity = new HttpEntity<>(adjustHeaderPriceRequest, headers);
            ResponseEntity<AdjustHeaderPriceResponse> responseEntity = restTemplate.postForEntity(
                    url, requestEntity, AdjustHeaderPriceResponse.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                log.info("{}", responseEntity.getBody());

                return responseEntity.getBody() == null ? Optional.empty() :
                        Optional.of(responseEntity.getBody());
            }

        } catch (Exception ex) {
            log.error("Error occurred while adjusting headerPrice ", ex);
        }
        return adjustHeaderPriceResponse;
    }

    public Optional<AdjustLinePriceResponse> adjustLinePrice(
            String url,
            AdjustLinePriceRequest adjustLinePriceRequest) {
        log.info("url\n{}\nAdjustLinePriceRequest\n{}", url, adjustLinePriceRequest);
        Optional<AdjustLinePriceResponse> adjustLinePriceResponse = Optional.empty();
        try {
            HttpHeaders headers = getHttpHeaders();
            HttpEntity<Object> requestEntity = new HttpEntity<>(adjustLinePriceRequest, headers);
            ResponseEntity<AdjustLinePriceResponse> responseEntity = restTemplate.postForEntity(
                    url, requestEntity, AdjustLinePriceResponse.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                log.info("{}", responseEntity.getBody());

                return responseEntity.getBody() == null ? Optional.empty() :
                        Optional.of(responseEntity.getBody());
            }

        } catch (Exception ex) {
            log.error("Error occurred while adjusting linePrice ", ex);
        }
        return adjustLinePriceResponse;
    }


    public String postBOL(BOLRequest bolRequest) {
        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.set("apiKey", "gIMdhprdvO3xA4SYTeAF8PFA00Ien1T1");
        httpHeaders.set("apiVersion", "1");
        httpHeaders.set("market", "na");
        try{
            HttpEntity<BOLRequest> requestEntity = new HttpEntity<>(bolRequest, httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    "https://bolprocessorprovider-na-stage.apps.cfplatform.dev.azeus.gaptech.com/BOLProcessorProvider/resources/shipment-notification-service" ,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
            ObjectMapper obj = new ObjectMapper();
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                log.info("post BOL success===============================");
                try {
                    log.info("bol response--{}",obj.writeValueAsString(responseEntity.getBody()));
                } catch (JsonProcessingException e) {
                    log.error(e);
                }
                log.info("BOL Success for releaseId {}", bolRequest.getShipment().get(0).getReleaseId());
                return "Success";
            }
        }
        catch (Exception ex) {
            log.error("Error occurred while getting inventoryDetails ", ex);
        }
        log.info("BOL failed for releaseId {}", bolRequest.getShipment().get(0).getReleaseId());
        return "Failure";
    }

    public Map<String, String> getReleaseInfo(Set<String> keySet) {
        Map<String, String> result = new HashMap<>();
        log.info("releaseIds to fetch releaseInfo\n{}", keySet);
        for(String releaseId: keySet){
            try {
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                        "https://releaseserviceprovider-na-stage.apps.cfplatform.dev.azeus.gaptech.com/ReleaseServiceProvider/resources/releases/v1/"+releaseId+"?marketCode=US",
                        String.class);

                if (HttpStatus.OK == responseEntity.getStatusCode()) {
                    log.info("{}", responseEntity.getBody());
                    JSONObject jsonObject =new JSONObject(responseEntity.getBody());
                    String shipNode = jsonObject.getJSONObject("ReleaseList").getJSONObject("Release").getJSONObject("ReleaseHeader").getString("ParentShipNode");
                    result.put(releaseId, Optional.ofNullable(shipNode).get());
                }

            } catch (Exception ex) {
                log.error("Error occurred while getting releaseInfo", ex);
            }
        }
        return result;

    }

}
