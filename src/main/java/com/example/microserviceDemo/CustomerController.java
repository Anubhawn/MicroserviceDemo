package com.example.microserviceDemo;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
//
//@EnableSwagger2

@RestController
@RequestMapping(value ="/")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private final CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    public void redirect(HttpServletResponse response) throws IOException {
//        LOGGER.trace("Entering method redirect");
//        LOGGER.debug("Entering Swagger site");
//        response.sendRedirect("/swagger-ui.html");
//    }

    @GetMapping("/customer")
    public List<Customer> getCustomers(){
        LOGGER.info("Got all customers successfully");
        return customerRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer id){
        if(customerRepository.existsById(id)) {
           Customer customer= customerRepository.findById(id).get();
            LOGGER.info("Customer was retrived successfully");
            return customer;
        }
        LOGGER.error("Customer is not found");
        return null;
    }

//    record newCustomerRequest(
//            String name,
//            String email,
//            String password,
//            String phone
//    ){
//
//    }
    @PostMapping("/customer")

    public Customer addCustomer(@Valid @RequestBody Customer request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setPhone(request.getPhone());
        LOGGER.info("Customer added successfully");
        return customerRepository.save(customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer id){


        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            LOGGER.info("Customer deleted successfully");
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        }

        LOGGER.error("Customer is not found");

        return new ResponseEntity<>("Customer is not found", HttpStatus.NOT_FOUND);

    }
//    record updateCustomerRequest(
//            String name,
//            String email,
//            String password,
//            String phone
//    ){}
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Integer id,
                                                 @Valid @RequestBody Customer update ){
        if(id == null || update == null){
            throw new NullPointerException("the data entered was NULL");
        }
        if(customerRepository.existsById(id)){
            Customer customer= customerRepository.findById(id).get();
            customer.setName(update.getName());
            customer.setEmail(update.getEmail());
            customer.setPassword(update.getPassword());
            customer.setPhone(update.getPhone());
            customerRepository.save(customer);
            LOGGER.info("Customer updated successfully");

            return customerRepository.save(customer);
        }
        LOGGER.error("Customer is not found");
        return null;

    }

}
