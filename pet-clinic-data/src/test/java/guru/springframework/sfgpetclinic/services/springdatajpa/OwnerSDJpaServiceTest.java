package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private static final String LAST_NAME = "Smith";

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        given(ownerRepository.findByLastName(eq(LAST_NAME))).willReturn(returnOwner);
        Owner smith = service.findByLastName(LAST_NAME);
        assertThat(smith.getLastName()).isEqualTo(LAST_NAME);
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        given(ownerRepository.findAll()).willReturn(owners);
        Set<Owner> result = service.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findById() {
        given(ownerRepository.findById(eq(1L))).willReturn(Optional.of(returnOwner));
        Owner owner = service.findById(1L);
        assertThat(owner).isNotNull();
    }

    @Test
    void findByIdNotFound() {
        given(ownerRepository.findById(eq(1L))).willReturn(Optional.empty());
        Owner owner = service.findById(1L);
        assertThat(owner).isNull();
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        given(ownerRepository.save(eq(ownerToSave))).willReturn(returnOwner);
        Owner savedOwner = service.save(ownerToSave);
        assertThat(savedOwner).isNotNull();
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        verify(ownerRepository).delete(eq(returnOwner));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(eq(1L));
    }
}