package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addContactPage();
        ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                .withMiddlename("Antonovich").withGroupname("123");
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
