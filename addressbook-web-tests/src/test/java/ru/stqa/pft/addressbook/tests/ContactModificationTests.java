package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePredictions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            Set<ContactData> before = app.contact().all();
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                    .withMiddlename("Antonovich").withGroupname("test1"), true);
            Set<ContactData> after = app.contact().all();
            Assert.assertEquals(after.size(), before.size() + 1);
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withFirstname("Arnold").withMiddlename("Swartz")
                .withLastname("S.").withGroupname(null);
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        //Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(after, before);
    }



}
