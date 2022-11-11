package com.example.electronicshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderController {
  /*  private final JwtUtils jwtUtils;
    private final OrderService orderService;

    @GetMapping(path = "/admin/manage/orders")
    public ResponseEntity<?> findAll (@RequestParam(defaultValue = "") String state,
                                      @PageableDefault(size = 5, sort = "lastModifiedDate, DESC") @ParameterObject Pageable pageable){
        return orderService.findAll(state, pageable);
    }

    @GetMapping(path = "/admin/manage/orders/{orderId}")
    public ResponseEntity<?> findOrderById (@PathVariable String orderId){
        return orderService.findOrderById(orderId);
    }

    @GetMapping(path = "/admin/manage/orders/stats")
    public ResponseEntity<?> getStats (@RequestParam(value = "from", required = false) String from,
                                       @RequestParam(value = "to", required = false) String to,
                                       @RequestParam("type") String type){
        return orderService.getOrderStatistical(from, to, type);
    }

    @GetMapping(path = "/orders/{orderId}")
    public ResponseEntity<?> userFindOrderById (@PathVariable String orderId,
                                                HttpServletRequest request){
        User user = jwtUtils.getUserFromJWT(jwtUtils.getJwtFromHeader(request));
        if (!user.getId().isBlank())
            return orderService.findOrderById(orderId, user.getId());
        throw new AppException(HttpStatus.FORBIDDEN.value(), "You don't have permission! Token is invalid");
    }

    @PutMapping(path = "/orders/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder (@PathVariable String orderId,
                                          HttpServletRequest request){
        User user = jwtUtils.getUserFromJWT(jwtUtils.getJwtFromHeader(request));
        return orderService.cancelOrder(orderId, user.getId());
    }*/
}
