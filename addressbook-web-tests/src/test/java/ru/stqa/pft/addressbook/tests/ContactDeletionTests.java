package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePredictions() {
        app.navigate().homePage();
        if (app.contact().all().size() == 0) {
            Contacts before = app.contact().all();
            app.navigate().addContactPage();
            app.contact().create(new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                    .withMiddlename("Antonovich").withGroupname("test1"), true);
            assertThat(app.contact().count(), equalTo(before.size() + 1));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.navigate().homePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
