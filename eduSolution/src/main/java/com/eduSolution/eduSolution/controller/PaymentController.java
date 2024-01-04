package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.PaymentIntentDTO;
import com.eduSolution.eduSolution.entity.Lesson;
import com.eduSolution.eduSolution.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

@RequestMapping("/stripe-controller")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestBody Map<String, Object> items) {
        // Tutaj możesz przetworzyć zamówienie, stworzyć sesję płatności w Stripe itp.
        // items to dane przesłane z frontu, na przykład lista produktów w koszyku

        // Przykład tworzenia sesji płatności w Stripe
        SessionCreateParams createParams = new SessionCreateParams.Builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setPriceData(new SessionCreateParams.LineItem.PriceData.Builder()
                                .setCurrency("pln")  // Ustaw walutę na polskie złote
                                .setProductData(new SessionCreateParams.LineItem.PriceData.ProductData.Builder()
                                        .setName("Produkt")
                                        .build())
                                .setUnitAmount(1000L)  // Kwota w groszach, 1000 groszy to 10 złotych
                                .build())
                        .setQuantity(1L)
                        .build())
                .setSuccessUrl("http://localhost:4200/success")  // URL po pomyślnej płatności
                .setCancelUrl("http://localhost:4200/cancel")    // URL po anulowaniu płatności
                .build();

        try {
            Session session = Session.create(createParams);

            Map<String, Object> response = new HashMap<>();
            response.put("id", session.getId());

            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            // Obsługa błędu związana z API Stripe
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment (@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDTO);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm (@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirm(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel (@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
}
