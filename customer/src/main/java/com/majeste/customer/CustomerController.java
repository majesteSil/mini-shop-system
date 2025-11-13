package com.majeste.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping({"api/customers"})
public record CustomerController(CustomerService customerService)
{

  @GetMapping
  public List<Customer> getAllCustomers()
  {
    return customerService.findAll();
  }

  @GetMapping({"/{id}"})
  public Customer getById(@PathVariable int id)
  {
    return customerService.findById(id);
  }

  @PostMapping
  public void createCustomer(@RequestBody Customer customer)
  {
    log.info("new customer created {}", customer);
    customerService.createCustomer(customer);
  }

  @PutMapping({"/id"})
  public void updateCustomer(@RequestBody Customer customer, @PathVariable int id)
  {
    log.info("updated customer {}",
             customerService.findById(id)
                            .getFirstname() + " " + customerService.findById(id)
                                                                   .getLastname());
    customerService.updateCustomer(id, customer);
  }

  @DeleteMapping({"/id"})
  public void deleteCustomer(@PathVariable int id)
  {
    log.info("deleted customer {}",
             customerService.findById(id)
                            .getFirstname());
    customerService.deleteCustomer(id);

  }
}
