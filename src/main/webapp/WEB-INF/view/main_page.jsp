<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="/resources/main.css">
</head>
<body>
<div class="container">

    <div class="row">

        <div class="title">
            <h2>EZ Geezy Otter <span><img id="logoImg" src="/resources/otter.png" alt="Otter"></span></h2>
        </div>

    </div>

    <div class="row subTitle">
        <h5>Select Existing Order Creation Scenario</h5>
    </div>

    <div class="row">
        <div class="col">
            <form action="/placeOrder" method="post" modelAttribute="staticScenarios">
                <div class="col-5">
                    <label>Environment</label>
                    <select class="form-select" name="environment">
                        <option>Test</option>
                        <option>Stage</option>
                    </select>
                </div>
                <br/>
                <div class="col-5">
                    <label>Select a Scenario</label>
                    <select class="form-select" name="templateName">
                        <option>LineItemPromise</option>
                        <option disabled=true>Synthetic Order</option>
                        <option disabled=true>Blind Return Order</option>
                        <option>B2X</option>
                        <option>DropShip</option>
                        <option>BackOrder</option>
                    </select>
                </div>
                <br/>
                <button class="btn btn-primary" role="button" type="submit">Place an Order</button>
                <a class="btn btn-primary" href="/register" role="button">Create a Scenario</a>
                <a class="btn btn-primary" href="/upload" role="button">Upload a JSON</a>
                <button class="btn btn-info" onclick="location.href = '/orderStatusPage';" id="myButton"
                        class="float-left submit-button">Check Order Status
                </button>
            </form>
        </div>
        <div class="col">
            <div class="col-5">
               <span>Created Order Number: </span> <input id="generatedOrderNumber"/>
            </div><br>
            <div class="col-5">
                <label>Order JSON</label>
            </div><br>
            <textArea id="orderJsonField" style="height: 665px; width: 500px;">
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
</body>
   <script>
      $("form").submit(function(e) {
          e.preventDefault(); // prevent actual form submit
          var form = $(this);
          var url = form.attr('action'); //get submit url [replace url here if desired]
          $.ajax({
               type: "POST",
               url: url,
               data: form.serialize(), // serializes form input
               success: function(data){
                   console.log(data.orderNumber);
                   console.log(data.orderRequest);
                   $('#generatedOrderNumber').val(data.orderNumber);
                   $('#orderJsonField').val(JSON.stringify(data.orderRequest,null, "\t"));
               }
          });
      });
      $(document).ready(function(){

        $('#registerForm').submit(function(e){
            $.ajax({
             url: $(this).attr('action'),
             data : $(this).serialize(),
             success : function (data){
                  $("#txt").val(data);
              }
           });
            e.preventDefault();
        });

        });

      </script>
</html>