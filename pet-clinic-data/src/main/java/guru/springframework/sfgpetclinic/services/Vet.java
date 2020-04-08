package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.services.Vet;

import java.util.Set;

public interface Vet {

    Vet findByLastName(Long name);
    Vet saveVet(Vet vet);
    Set<Vet> findAll();
}
