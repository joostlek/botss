package hu.indicium.battle.management.domain.participant;

import hu.indicium.battle.management.domain.AssertionConcern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
public class ParticipantDetails extends AssertionConcern implements Serializable {
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
        this.assertArgumentNotEmpty(firstName, "First name must not be empty.");
        this.assertArgumentLength(firstName, 50, "First name must be shorter than 50 characters.");

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.assertArgumentNotEmpty(lastName, "Last name must not be empty.");
        this.assertArgumentLength(lastName, 50, "Last name must be shorter than 50 characters.");

        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.assertArgumentNotEmpty(emailAddress, "Emailaddress must not be empty.");
        this.assertArgumentLength(emailAddress, 50, "Emailaddress must be shorter than 50 characters.");

        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.assertArgumentIsValidByRegex(phoneNumber, "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", "Not a valid phone number.");
        this.assertArgumentNotEmpty(phoneNumber, "Phone number must not be empty.");

        this.phoneNumber = phoneNumber;
    }
}
