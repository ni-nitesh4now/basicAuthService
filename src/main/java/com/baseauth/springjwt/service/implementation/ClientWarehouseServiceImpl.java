package com.baseauth.springjwt.service.implementation;

import com.baseauth.springjwt.entity.Client;
import com.baseauth.springjwt.entity.ClientWarehouse;
import com.baseauth.springjwt.entity.Customer;
import com.baseauth.springjwt.payload.request.ClientWarehouseRequest;
import com.baseauth.springjwt.payload.response.ClientWarehouseResponse;
import com.baseauth.springjwt.repository.ClientRepository;
import com.baseauth.springjwt.repository.ClientWarehouseRepository;
import com.baseauth.springjwt.service.ClientWarehouseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientWarehouseServiceImpl implements ClientWarehouseService {
    @Autowired
    private ClientWarehouseRepository  clientWarehouseRepository;
    @Autowired
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public ClientWarehouseResponse createWarehouse(ClientWarehouseRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client with ID " + request.getClientId() + " not found"));

        ClientWarehouse warehouse = ClientWarehouse.builder()
                .client(client)
                .warehouseName(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pinCode(request.getPinCode())
                .geolocation(request.getGeolocation())
                .contactPerson(request.getContactPerson())
                .contactPhone(request.getContactPhone())
                .build();

        return ClientWarehouseResponse.getDTO( clientWarehouseRepository.save(warehouse));
    }

    @Override
    @Transactional
    public ClientWarehouseResponse updateWarehouse(Long id, ClientWarehouseRequest request) {
        ClientWarehouse warehouse =  clientWarehouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse with ID " + id + " not found"));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client with ID " + request.getClientId() + " not found"));

        warehouse.setClient(client);
        warehouse.setWarehouseName(request.getName());
        warehouse.setAddress(request.getAddress());
        warehouse.setCity(request.getCity());
        warehouse.setState(request.getState());
        warehouse.setPinCode(request.getPinCode());
        warehouse.setGeolocation(request.getGeolocation());
        warehouse.setContactPerson(request.getContactPerson());
        warehouse.setContactPhone(request.getContactPhone());

        return ClientWarehouseResponse.getDTO( clientWarehouseRepository.save(warehouse));
    }

    @Override
    public ClientWarehouseResponse getWarehouse(Long warehouseId, Long clientId) {
        Optional<ClientWarehouse> optionalWarehouse = Optional.empty();

        if (warehouseId != null && clientId != null) {
            optionalWarehouse = Optional.ofNullable( clientWarehouseRepository.findByClient_IdAndId(clientId, warehouseId));
        } else if (warehouseId != null) {
            optionalWarehouse =  clientWarehouseRepository.findById(warehouseId);
        }

        return optionalWarehouse
                .map(ClientWarehouseResponse::getDTO)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found with provided criteria"));
    }

    @Override
    public List<ClientWarehouseResponse> getAllWarehouses(Long clientId) {
        List<ClientWarehouse> warehouses;

        if (clientId != null) {
            warehouses =  clientWarehouseRepository.findByClient_Id(clientId);
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof Customer customer) {
                warehouses =  clientWarehouseRepository.findByClient_Id(customer.getClient().getId());
            } else {
                warehouses =  clientWarehouseRepository.findAll();
            }
        }

        return warehouses.stream()
                .map(ClientWarehouseResponse::getDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String deleteWarehouse(Long id) {
        ClientWarehouse warehouse =  clientWarehouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse with ID " + id + " not found"));
        warehouse.setIsActive(false); // soft delete
         clientWarehouseRepository.save(warehouse);
        return "Successfully deleted";
    }
}
