package br.com.josehamilton.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long yearBirth;
    private LocalDate dateRegistration;
    private List<Address> address;

    @PrePersist
    public void prePersist() {
        this.dateRegistration = LocalDate.now();
    }

}
