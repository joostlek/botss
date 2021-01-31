package hu.indicium.battle.management.domain.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
public class ParticipantDetails implements Serializable {
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    public ParticipantDetails(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmailAddress(emailAddress);
        this.setPhoneNumber(phoneNumber);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
