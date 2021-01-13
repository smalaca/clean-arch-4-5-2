package com.smalaca.rentalapplication.query.apartment;

import org.assertj.core.api.Assertions;

import java.util.Map;
import java.util.function.Consumer;

class ApartmentReadModelAssertion {
    private final ApartmentReadModel actual;

    private ApartmentReadModelAssertion(ApartmentReadModel actual) {
        this.actual = actual;
    }

    static ApartmentReadModelAssertion assertThat(ApartmentReadModel actual) {
        return new ApartmentReadModelAssertion(actual);
    }

    ApartmentReadModelAssertion hasIdEqualsTo(String expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected);
        return this;
    }

    ApartmentReadModelAssertion hasOwnerIdEqualsTo(String expected) {
        Assertions.assertThat(actual.getOwnerId()).isEqualTo(expected);
        return this;
    }

    ApartmentReadModelAssertion hasDescriptionEqualsTo(String expected) {
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected);
        return this;
    }

    ApartmentReadModelAssertion hasAddressEqualsTo(String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {
        Assertions.assertThat(actual.getStreet()).isEqualTo(street);
        Assertions.assertThat(actual.getPostalCode()).isEqualTo(postalCode);
        Assertions.assertThat(actual.getHouseNumber()).isEqualTo(houseNumber);
        Assertions.assertThat(actual.getApartmentNumber()).isEqualTo(apartmentNumber);
        Assertions.assertThat(actual.getCity()).isEqualTo(city);
        Assertions.assertThat(actual.getCountry()).isEqualTo(country);
        return this;
    }

    ApartmentReadModelAssertion hasSpacesEqualsTo(Map<String, Double> expected) {
        Assertions.assertThat(actual.getSpaces()).hasSize(expected.size());

        expected.forEach((name, squareMeter) -> {
                Assertions.assertThat(actual.getSpaces()).anySatisfy(hasSpaceThat(name, squareMeter));
        });

        return this;
    }

    private Consumer<SpaceReadModel> hasSpaceThat(String name, Double squareMeter) {
        return spaceReadModel -> {
            Assertions.assertThat(spaceReadModel.getName()).isEqualTo(name);
            Assertions.assertThat(spaceReadModel.getSize()).isEqualTo(squareMeter);
        };
    }
}
