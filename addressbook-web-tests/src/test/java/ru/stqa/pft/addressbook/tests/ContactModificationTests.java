package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePredictions() {
        app.navigate().homePage();
        if (app.contact().all().size() == 0) {
            Contacts before = app.contact().all();
            app.navigate().addContactPage();
            app.contact().create(new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                    .withMiddlename("Antonovich").withGroupname("test1"), true);
            assertThat(app.contact().count(), equalTo(before.size()+1));
        }
    }

    @Test
    public void testContactModification() {
        app.navigate().homePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Arnold").withMiddlename("Swartz").withLastname("S.").withGroupname(null);
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
