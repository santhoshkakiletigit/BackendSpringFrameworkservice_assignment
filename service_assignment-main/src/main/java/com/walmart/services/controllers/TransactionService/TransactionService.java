package com.walmart.services.controllers.TransactionService;

import com.walmart.services.models.User;
import com.walmart.services.payload.request.Deposit;
import com.walmart.services.payload.response.MessageResponse;
import com.walmart.services.repository.UserRepository;
import com.walmart.services.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TransactionService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @PostMapping("/deposit")
  public ResponseEntity<MessageResponse> deposit(@RequestBody Deposit deposit) {
    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
    String c_user = authentication1.getName();
    Optional<User> optuser = userDetailsService.getUserByUsername(c_user);
    if (deposit.getAmount().intValue() > 0) {
      if (optuser.isPresent()) {
        User user = optuser.get();
        userDetailsService.deposit(user, deposit.getAmount());
        return ResponseEntity.ok().body(new MessageResponse("Deposit Successful !!"));
      } else {
        return ResponseEntity.ok().body(new MessageResponse("Error: Login Required"));
      }
    }
    else {
      return ResponseEntity.ok().body(new MessageResponse("Error: Positive value only"));
    }
  }

  @PostMapping("/withdraw")
  public ResponseEntity<MessageResponse> withdraw(@RequestBody Deposit deposit) {
    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
    String c_user = authentication1.getName();
    Optional<User> optuser = userDetailsService.getUserByUsername(c_user);
    if (deposit.getAmount().intValue() > 0) {
    if (optuser.isPresent()) {
      User user = optuser.get();
      userDetailsService.withdraw(user, deposit.getAmount());
      return ResponseEntity.ok().body(new MessageResponse("Withdraw successful !!"));
    } else {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Login Required"));
    }
  }
else {
    return ResponseEntity.ok().body(new MessageResponse("Error: Positive value only"));
  }
}
}
