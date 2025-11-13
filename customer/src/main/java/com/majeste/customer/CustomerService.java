package com.majeste.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository)
{

  public List<Customer> findAll()
  {
    return customerRepository.findAll();
  }

  public Customer findById(int id)
  {
    return customerRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
  }

  public Customer createCustomer(Customer customer)
  {
    // simple Beispiel-Validierung
    if (customerRepository.existsByEmail(customer.getEmail()))
    {
      throw new RuntimeException("Email already used");
    }
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(int id, Customer update)
  {
    Customer existing = findById(id);
    existing.setFirstname(update.getFirstname());
    existing.setLastname(update.getLastname());
    existing.setEmail(update.getEmail());
    return customerRepository.save(existing);
  }

  public void deleteCustomer(int id)
  {
    customerRepository.deleteById(id);
  }
}
