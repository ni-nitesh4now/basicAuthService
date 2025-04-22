package com.baseauth.springjwt.service.implementation;

import com.baseauth.springjwt.entity.Client;
import com.baseauth.springjwt.entity.ClientContracts;
import com.baseauth.springjwt.entity.Customer;
import com.baseauth.springjwt.payload.request.ClientContractRequest;
import com.baseauth.springjwt.payload.response.ClientContractResponse;
import com.baseauth.springjwt.repository.ClientContractRepository;
import com.baseauth.springjwt.repository.ClientRepository;
import com.baseauth.springjwt.repository.RoutePricingRepository;
import com.baseauth.springjwt.service.ClientContractService;
import com.baseauth.springjwt.service.LoggedInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientContractServiceImpl implements ClientContractService {

    @Autowired
    private ClientContractRepository clientContractRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LoggedInUserService loggedInUserService;
    @Autowired
    private RoutePricingRepository routePricingRepository;

    @Override
    public ClientContractResponse getContract(Long clientId, Long contractId) {
        ClientContracts contractOpt = new ClientContracts();

        if (contractId != null && clientId != null) {
            contractOpt = clientContractRepository.findByIdAndClientId(contractId, clientId)
                    .orElseThrow(() -> new RuntimeException("Contract not found"));

        } else if (contractId != null) {
            contractOpt = clientContractRepository.findById(contractId)
                    .orElseThrow(() -> new RuntimeException("Contract not found"));

        } else if (clientId != null) {
            contractOpt = clientContractRepository.findByClientId(clientId)
                    .orElseThrow(() -> new RuntimeException("Contract not found"));

        }

        if (contractOpt == null) {
            Object loggedInUser = loggedInUserService.getLoggedInUser();

            if (loggedInUser instanceof Customer) {
                Long loggedInClientId = ((Customer) loggedInUser).getClient().getId();
                contractOpt = clientContractRepository.findByClientId(loggedInClientId)
                        .orElseThrow(() -> new RuntimeException("Contract not found"));
            } else {
                throw new RuntimeException("Unauthorized access or contract not found for internal user");
            }
        }

        return ClientContractResponse.getDTO(contractOpt);
    }

    @Override
    public ClientContractResponse createContract(ClientContractRequest clientContractRequest) {
        ClientContracts contract = mapToEntity(clientContractRequest);
        contract = clientContractRepository.save(contract);
        return ClientContractResponse.getDTO(contract);
    }

    @Override
    public ClientContractResponse updateContract(Long clientId, Long contractId, ClientContractRequest clientContractRequest) {
        Optional<ClientContracts> existingContractOpt = clientContractRepository.findByIdAndClientId(contractId, clientId);
        if (existingContractOpt.isPresent()) {
            ClientContracts existingContract = existingContractOpt.get();
            existingContract.setContractStartDate(clientContractRequest.getContractStartDate());
            existingContract.setContractEndDate(clientContractRequest.getContractEndDate());
            existingContract.setCreditPeriodDays(clientContractRequest.getCreditPeriodDays());
            existingContract.setPaymentTerms(clientContractRequest.getPaymentTerms());
            existingContract.setDocumentUrls(clientContractRequest.getDocumentUrls());
            existingContract = clientContractRepository.save(existingContract);
            return ClientContractResponse.getDTO(existingContract);
        } else {
            throw new RuntimeException("Contract not found");
        }
    }

    @Override
    public String deleteContract(Long clientId, Long contractId) {
        ClientContracts existingContractOpt = clientContractRepository.findByIdAndClientId(contractId, clientId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        existingContractOpt.setIsActive(false);
        clientContractRepository.save(existingContractOpt);
        return "Contract deleted successfully";
    }

    private ClientContracts mapToEntity(ClientContractRequest request) {
        ClientContracts contract = new ClientContracts();
        Optional<Client> clientOpt = clientRepository.findById(request.getClientId());
        if (clientOpt.isPresent()) {
            contract.setClient(clientOpt.get());
        } else {
            throw new RuntimeException("Client not found");
        }
        contract.setContractStartDate(request.getContractStartDate());
        contract.setRoutePricing(routePricingRepository.findById(request.getRoutePricingId()).orElse(null));
        contract.setContractEndDate(request.getContractEndDate());
        contract.setCreditPeriodDays(request.getCreditPeriodDays());
        contract.setPaymentTerms(request.getPaymentTerms());
        contract.setDocumentUrls(request.getDocumentUrls());
        return contract;
    }
}