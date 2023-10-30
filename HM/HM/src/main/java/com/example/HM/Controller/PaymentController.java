package com.example.HM.Controller;

import com.example.HM.Dao.PaymentDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.models.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao;
    private AuthenticatedUser authenticatedUser;

    public PaymentController(PaymentDao paymentDao, AuthenticatedUser authenticatedUser) {
        this.paymentDao = paymentDao;
        this.authenticatedUser = authenticatedUser;
    }

    @GetMapping(path={"/make_payment"})
    public String makePayment(){
        return "payment";
    }


    @PostMapping(path={"/make_payment"})
    public String makePaymentPost(@RequestParam("amount") int amount,
                                  @RequestParam("paymentfor") String paymentFor, @AuthenticationPrincipal UserDetails userDetails, Model model,
                                  HttpSession session){
//        System.out.println(amount);
//        System.out.println(paymentFor);
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentFor(paymentFor);
        payment.setUserId(authenticatedUser.getAuthenticatedUser(userDetails).getId());
//        payment.setTransactionId("Rohith"+generatedTransactionId()");
        int status =  paymentDao.AddPayment(payment);
        String response;
        if(status==1){

            session.setAttribute("paymentFor",paymentFor);
            model.addAttribute("amount",amount);
            return "Payment";
        }
        else{
//            System.out.println("Payment already Made");
            response = "Payment already Made";
            model.addAttribute("alertMessage", response);
            return "message";
        }
    }



    @PostMapping(path={"/payment"})
    @ResponseBody
    public String paymentchecking(@RequestParam("amount") String data,HttpSession session) throws Exception{
//        System.out.println("Payment checking Succesfull");
        int amount = Integer.parseInt(data);
        session.setAttribute("amount",amount);
//        System.out.println(amount);
        RazorpayClient client = new RazorpayClient("rzp_test_sQ8dXiMJeBHGZW", "S0nAJGwlFXbzmxUXOQUYD3ve");
        JSONObject options = new JSONObject();
        options.put("amount", amount*100);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        Order order = client.orders.create(options);
//        pay_Mu3ZVr2TfAoEFv
//        System.out.println(order);
        return order.toString();
//        return "redirect:/home";
    }

    @GetMapping(path={"/payment/success/{payment_id}"})
    @ResponseBody
    public String payment(@PathVariable("payment_id") String payment_id, Model model,HttpSession session, @AuthenticationPrincipal UserDetails userDetails
    ){
//        System.out.println("Payment checking Succesfull");
//        System.out.println(payment_id);
//        System.out.println(session.getAttribute("paymentFor"));
        model.addAttribute("payment_id", payment_id);
        Payment payment = new Payment();
        payment.setTransactionId(payment_id);
        payment.setPaymentFor((String) session.getAttribute("paymentFor"));
        payment.setAmount((int) session.getAttribute("amount"));
        payment.setUserId(authenticatedUser.getAuthenticatedUser(userDetails).getId());
        paymentDao.AddPayment1(payment);
        return "payment_success";
    }

    @GetMapping(path={"/payment_preview"})
    public String paymentPreview(){
        return "paymentpreview";
    }
}
