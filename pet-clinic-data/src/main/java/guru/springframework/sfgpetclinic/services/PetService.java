package guru.springframework.sfgpetclinic.services;


import guru.springframework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findByLastName(Long name);

    Pet savePet(Pet pet);

    Set<Pet> findAll();
}
