package com.example.ordermicroservices.web;


import com.example.ordermicroservices.model.SingleOrder;
import com.example.ordermicroservices.payload.OrderRequest;
import com.example.ordermicroservices.services.MapValidationErrorService;
import com.example.ordermicroservices.services.OrderBookService;
import com.example.ordermicroservices.services.SingleOrderService;
import com.example.ordermicroservices.utility.UserRole;
import com.example.ordermicroservices.validator.CancelOrderValidator;
import com.example.ordermicroservices.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private SingleOrderService singleOrderService;

    @Autowired
    private OrderBookService orderBookService;

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private CancelOrderValidator cancelOrderValidator;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest, BindingResult result) {
        // validate the order
        orderValidator.validate(orderRequest,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        // add the order
        SingleOrder order = singleOrderService.saveOrder(orderRequest);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOrders")
    public ResponseEntity<?> pendingOrdersByUser(@Valid @RequestParam String username){
        // returns any pending orders by their username.
        List<SingleOrder> orders = singleOrderService.getOrdersByUsernamePending(username);
        if (orders.size() == 0) {
            return new ResponseEntity<>("No orders pending available", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders(@Valid @RequestParam String role){
        // returns any pending orders by their username.
        if (UserRole.getUserRoleCode(role) != UserRole.ADMIN) {
            return new ResponseEntity<>("Must be admin", HttpStatus.BAD_REQUEST);
        }

        List<SingleOrder> orders = singleOrderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/orders")
    public ResponseEntity<?> deleteOrderWithinTime(@Valid @RequestParam Long id){
        // deletes an order if it is within 2 hours of time.
        SingleOrder order = singleOrderService.getOrderById(id);
        Errors errors = new BeanPropertyBindingResult(order, "order");

        cancelOrderValidator.validate(order,errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>("You can only cancel an order within 2 hours of making it.", HttpStatus.BAD_REQUEST);
        }

        // if valid, delete the order
        orderBookService.deleteBooksByOrder(order);
        SingleOrder deletedOrder = singleOrderService.removeOrderById(order);

        return new ResponseEntity<>(deletedOrder, HttpStatus.OK);
    }
}
