package com.example.HM.Controller;

import com.example.HM.Dao.PaymentDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao;
    private AuthenticatedUser authenticatedUser;

    public PaymentController(PaymentDao paymentDao, AuthenticatedUser authenticatedUser) {
        this.paymentDao = paymentDao;
        this.authenticatedUser = authenticatedUser;
    }

    public long generatedTransactionId(){
        long transactionId = (long)(Math.random()*1000000000);
        return transactionId;
    }

    @GetMapping(path={"/make_payment"})
    public String makePayment(){
        return "payment";
    }

    @PostMapping(path={"/make_payment"})
    public String makePaymentPost(@RequestParam("amount") int amount,
                                  @RequestParam("paymentfor") String paymentFor, @AuthenticationPrincipal UserDetails userDetails, Model model){
        System.out.println(amount);
        System.out.println(paymentFor);
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentFor(paymentFor);
        payment.setUserId(authenticatedUser.getAuthenticatedUser(userDetails).getId());
        payment.setTransactionId(generatedTransactionId());
        int status =  paymentDao.AddPayment(payment);
        String response;
        if(status==1){
            System.out.println("Payment added successfully");
            response = "Payment added successfully";
        }
        else{
            System.out.println("Payment already Made");
            response = "Payment already Made";
        }
        model.addAttribute("alertMessage", response);
        return "message";
    }
}
