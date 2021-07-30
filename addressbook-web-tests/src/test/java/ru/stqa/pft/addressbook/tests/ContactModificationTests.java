package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePredictions() {
        Contacts before = app.db().contacts();
        if (before.size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                    .withMiddlename("Antonovich").withGroupname("test1"), true);
            assertThat(app.contact().count(), equalTo(before.size() + 1));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/zel.jpg");
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Arnold").withMiddlename("Swartz").withLastname("S.").withGroupname(null)
                .withPhoto(photo);
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
