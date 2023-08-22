<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Upload XML Form</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
                  integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
                  crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
       <style>
          .custom-file-input.selected:lang(en)::after {
            content: "" !important;
          }
          .custom-file {
            overflow: hidden;
          }
          .custom-file-input {
            white-space: nowrap;
          }
        </style>
   </head>
   <body>
   <div class="container">

       <div class="title">
         <h2 style="text-align:center">EZ Geezy Otter <span><img id="logoImg" src="/resources/otter.png" width="200" alt="Otter"></span></h2>
       </div>

     </div>
      <div class="m-2">
               <form method="post" action="/fileuploadservlet" enctype="multipart/form-data">
               <h4>File Upload:</h4> <br />
                     Select a file to upload: <br />
                        <br />
                        <input type = "file" name = "file"/>
                        <br />
                        <br />
                        <button type="submit">Upload File</button>
                        <br />
                </form>

           <div class="col">
                           <div class="col-5">
                               <span>Created Order Number: </span> <input id="generatedOrderNumber"/>
                           </div><br>
                           <div class="row" id="orderJsonLabelDiv">
                               <label>Order JSON</label>
                           </div>
                           <div class="row">
                           <textArea id="orderJsonField">
            {
               "shippingAddress": {
                   "verificationStatus": "VERIFIED",
                   "stateProvinceCode": "CA",
                   "postalCode": "94105",
                   "phoneNumber": null,
                   "lastName": "Doe",
                   "firstName": "John",
                   "email": "john@hilton.com.invalid",
                   "countryCode": "US",
                   "cityName": "San Francisco",
                   "addressLine2": null,
                   "addressLine1": "415 Mission Street"
               },
               "shipmentPreference": {
                   "withinCutoff": null,
                   "shipMethod": "Standard Shipping",
                   "minDaysToDeliver": 3,
                   "maxDaysToDeliver": 5,
                   "inBusinessDays": false
               },
               "promotions": [
                   {
                       "promotionReasonCode": "QUALIFIED",
                       "promotionDescription": "EmployeePoints",
                       "promotionCode": "0f2b11ed-78d60f2b11ed-0f2b11ed-",
                       "promotionApplied": true,
                       "awards": [
                           {
                               "awardType": "Dollar Off",
                               "awardId": "1",
                               "awardDescription": "EmployeePoints",
                               "awardApplied": true,
                               "awardAmount": 68.00
                           }
                       ]
                   }
               ],
               "payment": {},
               "orderHeader": {
                   "vatInclusivePrices": false,
                   "thirdPartyOrderIndicator": false,
                   "thirdPartyDetails": null,
                   "taxApplied": false,
                   "originatingLocation": null,
                   "originatingDeviceInteraction": "SelfServe",
                   "originatingDeviceId": null,
                   "originatingClientApplication": "SF_B2B_CC_StageExternal",
                   "orderType": "SALES",
                   "orderPurpose": "REGULAR",
                   "orderNumber": "MO323231343",
                   "orderInvoiceCurrencyCode": "USD",
                   "orderDate": "2022-08-02T00:43:13-0000",
                   "marketCode": "US",
                   "localeCode": "en_US",
                   "lineOfBusiness": "SPECIALTY",
                   "inventoryReservedInFull": false,
                   "guestCheckout": false,
                   "fraudValidationStatus": "ACCEPT",
                   "enteredBy": "W",
                   "countryCode": "US",
                   "client": "Delta",
                   "channelCode": "Web",
                   "brandCode": "GPS"
               },
               "lines": [
                   {
                       "product": {
                           "upcCode": "501724645503",
                           "taxCode": "C1",
                           "shippingChargeApplicable": false,
                           "itemType": "Merch Products",
                           "itemStyleNumber": "997076",
                           "itemSkuNumber": "9970760020016",
                           "itemSizeDescription": {
                               "en_US": "22W"
                           },
                           "itemSizeCode": "16",
                           "itemIsHazmat": false,
                           "itemGiftWrappable": false,
                           "itemDescription": {
                               "en_US": "Woven Shorts"
                           },
                           "itemColorDescription": {
                               "en_US": "Skyline Slate"
                           },
                           "brandCode": "GPS"
                       },
                       "parentLine": false,
                       "orderedQuantity": 2,
                       "linePriceInfo": {
                           "retailPrice": 0.0,
                           "lineTax": [
                               {
                                   "unitTaxAmountInLocalCurrency": null,
                                   "unitTaxAmount": 0.0,
                                   "unitTaxableAmount": 68.0,
                                   "taxPercentage": 8.62,
                                   "taxName": "SALES_TAX",
                                   "chargeName": "Price",
                                   "chargeCategory": "Price"
                               }
                           ],
                           "lineCharge": [
                               {
                                   "discountType": "DOLLOFF",
                                   "chargePerUnit": 34.0,
                                   "chargeName": "123",
                                   "chargeId": "123",
                                   "chargeDesc": "EmployeePoints",
                                   "chargeCategory": "EmployeePoints",
                                   "awardId": "1"
                               }
                           ],
                           "currentPriceType": "REGULAR",
                           "currentPrice": 34.0
                       },
                       "lineNumber": 1,
                       "inventoryReservationId": null,
                       "intentionalBackorder": false,
                       "instructions": [],
                       "eligibleReturnLocationCode": "Online",
                       "autoAdded": false
                   }
               ],
               "headerPriceInfo": {
                   "headerTaxes": [
                       {
                           "taxPercentage": 0.0,
                           "taxName": "SALES_TAX",
                           "taxAmountInLocalCurrency": null,
                           "taxAmount": 0.0,
                           "taxableAmount": 0.0,
                           "chargeName": "ShipAndHandle",
                           "chargeCategory": "ShipAndHandle"
                       }
                   ],
                   "headerCharges": [
                       {
                           "chargeName": "ShipAndHandle",
                           "chargeCategory": "ShipAndHandle",
                           "chargeAmount": 0.0
                       }
                   ]
               },
               "gifting": null,
               "customerTracking": {
                   "webSessionId": "8017X000001KBItQAO"
               },
               "customer": {
                   "externalCustomerId": "1eed7fae4c414d3aaceb5ea94618c81c"
               },
               "billingAddress": {
                   "verificationStatus": "VERIFIED",
                   "stateProvinceCode": "CA",
                   "postalCode": "94105",
                   "phoneNumber": null,
                   "lastName": "Doe",
                   "firstName": "John",
                   "email": "john@hilton.com.invalid",
                   "countryCode": "US",
                   "cityName": "San Francisco",
                   "addressLine2": null,
                   "addressLine1": "415 Mission Street"
               }
           }
                       </textArea>

                           </div>
                       </div>
                   </div>
               </div>
           </div>
           <br/>
           <button id="myButton" class="btn btn-primary" >Real Slim Shady</button>
   </body>
    <script type="text/javascript">
               document.getElementById("myButton").onclick = function () {
                   location.href = "http://10.20.32.119:3000/";
               };
           </script>
   <script>
       $("form").submit(function (e) {
           e.preventDefault(); // prevent actual form submit
           var form = $(this);
           var url = form.attr('action'); //get submit url [replace url here if desired]
           var formData = new FormData($(this)[0]);

           $.ajax({
               type: "POST",
               url: url,
               data: formData, // serializes form input
               contentType: false,
               processData: false,
               cache: false,
               async: false,
               type : form.attr('method'),
               success: function (data) {
                   console.log(data.orderNumber);
                   console.log(data.orderRequest);
                   $('#generatedOrderNumber').val(data.orderNumber);
                   $('#orderJsonField').val(JSON.stringify(data.orderRequest,null, "\t"));
               }
           });
       });
   </script>
   <script>
      document.querySelector('.custom-file-input').addEventListener('change', function (e) {
        var name = document.getElementById("customFileInput").files[0].name;
        var nextSibling = e.target.nextElementSibling
        nextSibling.innerText = name
      })
   </script>
</html>