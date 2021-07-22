package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePredictions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            List<ContactData> before = app.contact().list();
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Oleg").withLastname("Ivanov")
                    .withMiddlename("Antonovich").withGroupname("test1"), true);
            List<ContactData> after = app.contact().list();
            Assert.assertEquals(after.size(), before.size() + 1);
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Arnold").withMiddlename("Swartz")
                .withLastname("S.").withGroupname(null);
        int index = before.size() - 1;
        app.contact().modify(contact, index);
        List<ContactData> after = app.contact().list();
        //Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }



}
