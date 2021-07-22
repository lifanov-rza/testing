package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        app.goTo().addContactPage();
        ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                .withMiddlename("Antonovich").withGroupname("123");
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        before.add(contact);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
