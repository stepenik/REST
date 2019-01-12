/*
 * Copyright 2018-2019 Sergei Visotsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sergei.rest.service.v2;

import org.sergei.rest.dto.v2.CustomerDTOV2;
import org.sergei.rest.exceptions.ResourceNotFoundException;
import org.sergei.rest.model.Customer;
import org.sergei.rest.repository.CustomerRepository;
import org.sergei.rest.service.CustomerService;
import org.sergei.rest.util.ObjectMapperUtil;
import org.sergei.rest.service.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * V2 of customer service
 *
 * @author Sergei Visotsky
 */
@Service
public class CustomerServiceV2 extends CustomerService {

    public CustomerServiceV2(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    /**
     * Get all customers
     *
     * @return List of customer DTOs list
     */
    public List<CustomerDTOV2> findAllV2() {
        List<CustomerDTOV2> customerDTOList = new LinkedList<>();

        List<Customer> customers = customerRepository.findAll();

        customers.forEach(customer -> {
            CustomerDTOV2 customerDTOV2 = new CustomerDTOV2();
            customerDTOV2.setCustomerId(customer.getCustomerId());
            customerDTOV2.setFirstName(customer.getFirstName());
            customerDTOV2.setLastName(customer.getLastName());
            customerDTOV2.setAge(customer.getAge());

            customerDTOList.add(customerDTOV2);
        });

        return customerDTOList;
    }

    /**
     * Get all customers paginated
     *
     * @return List of customer DTOs list
     */
    public Page<CustomerDTOV2> findAllPaginatedV2(int page, int size) {
        Page<Customer> customers = customerRepository.findAll(PageRequest.of(page, size));
        return ObjectMapperUtil.mapAllPages(customers, CustomerDTOV2.class);
    }

    /**
     * Get customer by id
     *
     * @param customerId get customer number param from REST controller
     * @return Customer DTO response
     */
    public CustomerDTOV2 findOneV2(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Constants.CUSTOMER_NOT_FOUND)
                );
        return ObjectMapperUtil.map(customer, CustomerDTOV2.class);
    }
}
