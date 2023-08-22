package com.gap.AzureBot.controller;

import com.gap.AzureBot.client.OrderPlaceClient;
import com.gap.AzureBot.exception.AzureBotCustomException;
import com.gap.AzureBot.model.CreateOrderRequest;
import com.gap.AzureBot.model.CreateOrderScenarioRequest;
import com.gap.AzureBot.model.OrderForm;
import com.gap.AzureBot.model.orderplace.OrderPlacementResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;


@Controller
@Slf4j
public class OrderController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/orderStatusPage")
    public String orderStatusPage() {
        return "order_status";
    }

    @Autowired
    private OrderPlaceClient orderPlaceClient;

    @RequestMapping("/register")
    public String showForm(Model model) {
        OrderForm orderForm = new OrderForm();
        model.addAttribute("orderForm", orderForm);

        return "register_form";
    }

    @RequestMapping("/upload")
    public String showFormUpload(Model model) {
        OrderForm orderForm = new OrderForm();
        model.addAttribute("orderForm", orderForm);

        return "upload_xml";
    }


    @PostMapping(value = "/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OrderPlacementResponse placeOrder(@RequestBody @ModelAttribute("staticScenarios")
                                                 CreateOrderRequest createOrderRequest) throws AzureBotCustomException,
            FileNotFoundException {
        log.info("^^^^^^^^^^ request from front end : {}", createOrderRequest.toString());
        return orderPlaceClient.placeOrder(createOrderRequest.getOrderDetails(), createOrderRequest.getTemplateName(),
                createOrderRequest.getTenderType(), createOrderRequest.getMarketCode(),null);
    }
    @RequestMapping("/mainPage")
    public String mainPageDisplay(){
        return "main_page";
    }

    @PostMapping(value="/createOrderRequest",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OrderPlacementResponse createOrder(@ModelAttribute("createOrderRequestParams")
                                    CreateOrderScenarioRequest createOrderRequest, Model model)
            throws AzureBotCustomException, FileNotFoundException {
        log.info("Request from UI {}",createOrderRequest);
        return orderPlaceClient.placeOrder(null, "SalesOrder",
                createOrderRequest.getPaymentType(), createOrderRequest.getMarket(),createOrderRequest);
    }
}