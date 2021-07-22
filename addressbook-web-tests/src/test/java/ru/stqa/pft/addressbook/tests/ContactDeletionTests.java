package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            List<ContactData> before = app.contact().list();
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                    .withMiddlename("Antonovich").withGroupname("test1"), true);
            List<ContactData> after = app.contact().list();
            Assert.assertEquals(after.size(), before.size() + 1);
        }
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().select(index);
        app.contact().deleteSelectedContacts();
        app.contact().confirmDeletion();
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
