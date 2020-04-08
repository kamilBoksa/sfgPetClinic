package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findById(Long id);
    Owner findByLastName(Long name);
    Owner saveOwner(Owner owner);
    Set<Owner> findAll();
}
