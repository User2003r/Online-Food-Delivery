package com.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
