package com.kiraw.LoginKiraw.service.jpa;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
    @Autowired
    private ProviderRepository providerRepository;



    public void crear(Provider provider) {
        providerRepository.crear(provider);
    }

    public void crear2(Provider provider) {
        providerRepository.crearimg(provider);
    }

}
