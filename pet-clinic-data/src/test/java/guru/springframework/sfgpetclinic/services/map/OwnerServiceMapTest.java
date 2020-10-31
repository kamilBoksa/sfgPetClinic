package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    private final long ownerId = 1L;
    private final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder()
                .id(ownerId)
                .lastName(lastName)
                .build()
        );
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertThat(ownerSet.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertThat(owner.getId()).isEqualTo(ownerId);
    }

    @Test
    void saveExistingId() {
        long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);;

        assertThat(savedOwner.getId()).isEqualTo(id);
    }

    @Test
    void saveNoId() {
        Owner owner = ownerServiceMap.save(Owner.builder().build());
        assertThat(owner).isNotNull();
        assertThat(owner.getId()).isNotNull();
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertThat(ownerServiceMap.findAll().size()).isEqualTo(0);
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertThat(ownerServiceMap.findAll().size()).isEqualTo(0);
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(lastName);

        assertThat(smith).isNotNull();
        assertThat(smith.getId()).isEqualTo(ownerId);
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("foo");
        assertThat(smith).isNull();
    }
}