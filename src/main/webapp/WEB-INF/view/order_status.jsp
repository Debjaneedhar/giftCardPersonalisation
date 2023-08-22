<!doctype html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Order Status Page</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.21.0/dist/bootstrap-table.min.css">
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
    <h5>Check Order Status</h5>
  </div>


  <div>
    <label>Order Number</label>
    <input type="text" id="orderNo"/>
    <input type="hidden" id="hiddenOrderNo"/>
    <button onclick="getOrderstatus()">Check Order Status</button>
  </div>
  <h3 style="text-align:center"> Order Status</h3>
  <br/>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Line Number</th>
      <th scope="col">Status</th>
      <th scope="col">SkuNumber</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
  </table>
  </br>
  </br>
  <div class="container" >
    <h4>Actions on Order</h4>

    <form id="shipOrderButton" action="/bol/{orderId}">
      <input class="hideActions btn btn-primary" type="submit" value="      Ship Order     " />
    </form>
    </br>
    <form id="lineBackButton" action="/lineBack">
      <input class="hideActions btn btn-primary" type="submit" value="         LB                " />
    </form>
    </br>
    <form id="priceAdjHeadButton" action="/bol/{orderId}">
      <input class="hideActions btn btn-primary" type="submit" value="Price Adjustment Header" />
    </form>
    </br>
    <form id="priceAdjLineButton" action="/bol/{orderId}">
      <input class="hideActions btn btn-primary" type="submit" value="Price Adjustment Line" />
    </form>
    </br>
    <input class="hideActions btn btn-primary" type="submit" value="    Cancel Line  " />
  </div>
  <div align="center">
    <label>Response from action:</label>
    <input type="text" readonly id="actionResponse"/>
  </div>


  <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/bootstrap-table@1.21.0/dist/bootstrap-table.min.js"></script>
</div>
</body>

  <script>
    function getOrderstatus(){
               var orderNo = $("#orderNo").val();
                $.ajax({
                 url: "/getOrderStatus/"+orderNo,
                 success : function (data){
                    $('h3').html('Order Status for '+data.orderNumber);
                    $('#hiddenOrderNo').val(data.orderNumber);
                    $('#shipOrderButton').attr("action","/bol/"+data.orderNumber);
                    var tableData = '';
                      $.each(data.lines, function(i, item) {
                          var rowNumber = i+1;
                          tableData += '<tr><th scope="row">'+rowNumber+'</th><td>'+item.LineNumber+'</td><td class="lineStatus">'+item.MaximumLineStatus+'</td><td>'+item.Product.ItemSkuNumber+'</td></tr>';
                      });


                      $('.table > tbody').html(tableData);
                  }
               });
               // e.preventDefault();
            }

    $("#shipOrderButton").submit(function(e) {
              e.preventDefault(); // prevent actual form submit
              var form = $(this);
              var url = form.attr('action'); //get submit url [replace url here if desired]
              $.ajax({
                   url: url,
                   success: function(data){
                       $('#actionResponse').val(data);
                   }
              });
          });

          $("#lineBackButton").submit(function(e) {
                        e.preventDefault(); // prevent actual form submit
                        var form = $(this);
                        var url = form.attr('action'); //get submit url [replace url here if desired]
                        $.ajax({
                             type: "POST",
                                        url: url,
                                        contentType: "application/json",
                                        data: '{"messageHeader":{"versionDetail":{"majorVersion":1, "minorVersion":0 }, "clientDetail":{"clientName":"orderrelease", "clientId":"orderrelease", "requestId":"TPRMIXORDRRRR", "requestedTimeStamp":"2022-08-22T00:50:38-0500"} }, "orderId":"TPRMIXORDR11", "crmOrderNumber":"GQPTQMPG9", "shipNode":"21", "market":"US", "orderDate":"2022-08-01T10:55:45-0400", "lineItems":[{"lineNumber":"2", "itemSkuNumber":"8617540020002", "quantity":"1"} ] }', // serializes form input
                                        success: function(data){
                                            $('#actionResponse').val(data.responseStatus.serviceStatus.statusDescription);
                                        }
                        });
                    });
  </script>
</html>